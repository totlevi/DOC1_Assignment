package via.doc1.doc1_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "via.doc1.doc1_assignment")
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
