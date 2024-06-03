package cn.cestc;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableGracefulResponse
public class WorkboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkboardApplication.class, args);
    }

}
