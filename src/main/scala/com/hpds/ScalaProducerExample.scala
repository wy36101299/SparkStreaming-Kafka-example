package com.hpds

/**
 * Created by wy on 2015/10/13.
 */
import kafka.producer.ProducerConfig
import java.util.Properties
import scala.util.Random
import kafka.producer.Producer
import kafka.producer.KeyedMessage

object ScalaProducerExample {

  def main(args: Array[String]) {

    val events = args(0).toInt
    val topic = args(1)
    val brokers = args(2)
    val rnd = new Random()
    val props = new Properties()
    props.put("metadata.broker.list", brokers)
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    //props.put("partitioner.class", "com.colobu.kafka.SimplePartitioner")
    props.put("producer.type", "async")
    //props.put("request.required.acks", "1")

    val config = new ProducerConfig(props)
    val producer = new Producer[String, String](config)
    val t = System.currentTimeMillis()
    for (nEvents <- Range(0, events)) {
      val number = rnd.nextInt(10).toString;
      val data = new KeyedMessage[String, String](topic, null, number);
      println(number)
      producer.send(data);
      Thread.sleep(200)
    }
    System.out.println("sent per second: " + events * 1000 / (System.currentTimeMillis() - t));
    producer.close();
  }
}