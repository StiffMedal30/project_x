package za.co.project_x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("za.co.project_x")
public class ProjectXApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectXApplication.class, args);
    }
}
