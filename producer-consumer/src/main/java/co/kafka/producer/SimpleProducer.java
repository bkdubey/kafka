package co.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


public class SimpleProducer
{
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.10:9101");
        props.put("acks", "1");  //"0" -No ack, "1" only Leader ,"all" ALL
        props.put("retries", 0);  // "0" doesn't re try ; positive value will retry
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 10; i++)
        {
            try
            {
                ProducerRecord<String, String> record = new ProducerRecord<>("kafka_topic3p3r", "mykey" + i, "my message from java " + i);
                producer.send(record);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            Thread.sleep(500);
        }

        producer.close();
        System.out.println("message published");
    }
}