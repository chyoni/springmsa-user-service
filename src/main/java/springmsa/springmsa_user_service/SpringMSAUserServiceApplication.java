package springmsa.springmsa_user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringMSAUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMSAUserServiceApplication.class, args);
    }

}
