package cn.cestc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WorkboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkboardApplication.class, args);
    }

}
