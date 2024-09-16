package ejercicioDoble;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;

import com.google.gson.Gson;

public class AppHistorial {

    public static void main(String[] args) throws Exception {
        String topicName = "zapatillasTopic"; 
        String url = "tcp://localhost:61616";
        
        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
        Connection connection = cf.createConnection("admin", "admin");
        connection.start();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageConsumer consumer = session.createConsumer(topic);
        
        // Define las colas para los distintos tipos de zapatillas
        Queue colaCorrer = session.createQueue("colaCorrer");
        Queue colaVestir = session.createQueue("colaVestir");

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String json = textMessage.getText();
                    System.out.println("Recibido: " + json);
                    
                    Gson gson = new Gson();
                    Zapatilla zapatilla = gson.fromJson(json, Zapatilla.class);
                    
                    // Clasifica y envía a la cola correspondiente
                    MessageProducer producer = session.createProducer(zapatilla.getTipo().equals("Correr") ? colaCorrer : colaVestir);
                    producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                    producer.send(session.createTextMessage(json));
                    
                    System.out.println("Enviado a cola " + (zapatilla.getTipo().equals("Correr") ? "Correr" : "Vestir") + ": " + json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        // Mantener la aplicación en ejecución para seguir recibiendo mensajes
        while (true) {}
    }
}