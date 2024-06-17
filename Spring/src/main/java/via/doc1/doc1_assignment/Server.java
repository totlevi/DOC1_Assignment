package via.doc1.doc1_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "via.doc1.doc1_assignment")
@Import(WebConfig.class)
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
