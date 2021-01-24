
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.swing.*;
import java.net.URI;

public class SportCalendar {

    public static void main(String[] args) {

        ResourceConfig rc = new ResourceConfig().packages("com.sportradar.intern.rest");
        HttpServer server = JdkHttpServerFactory.createHttpServer(
                URI.create("http://localhost:8080/api"), rc);
        JOptionPane.showMessageDialog(null, "Sportradar Calendar endpoints are running! Click OK to stop.");
        server.stop(0);
    }
}