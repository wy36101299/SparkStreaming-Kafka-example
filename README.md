# SparkStreaming-Kafka-example

## build scala-archetype-simple
 ```
 mvn archetype:generate -B
 -DarchetypeGroupId=net.alchim31.maven -DarchetypeArtifactId=scala-archetype-simple -DarchetypeVersion=1.5
 -DgroupId=com.hpds -DartifactId=SparkStreaming-Kafka-example -Dversion=0.1-SNAPSHOT -Dpackage=com.hpds
 ```
## how to run
#####zookeeper cluster: bin/zookeeper-server-start.sh config/zookeeper.properties
#####kafka cluster : bin/kafka-server-start.sh config/server.properties
#####spark cluster: sbin/start-all.sh
```
create topic : bin/kafka-topics.sh --create \
               --replication-factor 3 \
               --partition 3 \
               --topic test_topic \
               --zookeeper ip1:2181,ip2:2181,ip3:2181 ...
```               
#####producer : java -cp SparkStreaming-Kafka-example-0.1-SNAPSHOT-jar-with-dependencies.jar com.hpds.ScalaProducerExample 10000 test_topic localhost:9092 (the port is depend on your kafka server.properties)
#####consumer : ./spark-submit --class com.hpds.ScalaConsumerExample --master spark://b10:7077 ~/yang/SparkStreaming-Kafka-example-0.1-SNAPSHOT-jar-with-dependencies.jar localhost:2181 test_topic test_topic 1

### reference
[scala-archetype-simple](https://github.com/davidB/scala-archetype-simple)  
[kafka-example-in-scala](https://github.com/smallnest/kafka-example-in-scala)  
[official example-SparkStreaming kafkaWordCount](https://github.com/apache/spark/blob/master/examples/src/main/scala/org/apache/spark/examples/streaming/KafkaWordCount.scala)