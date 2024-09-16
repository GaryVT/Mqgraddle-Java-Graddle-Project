package ejercicioDoble;

import javax.jms.Connection; 
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;

import com.google.gson.Gson;

public class JmsProductorTopicArtemis {

    public static void main(String[] args) throws Exception {
        Zapatilla z1 = new Zapatilla("Correr", "Nike Air Zoom Pegasus");
        Zapatilla z2 = new Zapatilla("Vestir", "Oxford Classic");

        Gson gson = new Gson();
        String json1 = gson.toJson(z1);
        String json2 = gson.toJson(z2);
        
        String topicName = "zapatillasTopic"; 
        String url = "tcp://localhost:61616";
        
        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
        Connection connection = cf.createConnection("admin", "admin");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        
        Message msg1 = session.createTextMessage(json1);
        producer.send(msg1);
        System.out.println("Enviado: " + json1);
        
        Message msg2 = session.createTextMessage(json2);
        producer.send(msg2);
        System.out.println("Enviado: " + json2);
        
        session.close();
        connection.close();
    }
}