package ejercicio;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class JmsProductor {

    public static void main(String[] args) throws Exception {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("ropa", "Camisa"));
        productos.add(new Producto("electrodoméstico", "Microondas"));
        productos.add(new Producto("ropa", "Pantalones"));

        Gson gson = new Gson();
        String url = "tcp://localhost:61616";
        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactory(url, null);
        Connection connection = cf.createConnection("admin", "admin");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Crear colas para proveedores
        Queue ropaQueue = session.createQueue("proveedorAQueue");
        Queue electrodomesticoQueue = session.createQueue("proveedorBQueue");

        MessageProducer ropaProducer = session.createProducer(ropaQueue);
        MessageProducer electrodomesticoProducer = session.createProducer(electrodomesticoQueue);
        ropaProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        electrodomesticoProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        for (Producto producto : productos) {
            String json = gson.toJson(producto);
            Message msg = session.createTextMessage(json);
            if (producto.getTipo().equals("ropa")) {
                System.out.println("Enviando a Proveedor A: " + json);
                ropaProducer.send(msg);
            } else if (producto.getTipo().equals("electrodoméstico")) {
                System.out.println("Enviando a Proveedor B: " + json);
                electrodomesticoProducer.send(msg);
            }
        }

        session.close();
        connection.close();
    }
}