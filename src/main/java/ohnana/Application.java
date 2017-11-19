package ohnana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);

        Map<String, Object> server = new HashMap();
        server.put("SERVER_PORT", System.getenv("PORT"));

        app.setDefaultProperties(server);
        app.run(args);
    }

}