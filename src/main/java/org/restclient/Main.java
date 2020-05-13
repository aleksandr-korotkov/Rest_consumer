package org.restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
        RestClientSirvice bean = run.getBean(RestClientSirvice.class);
        bean.getAllCustomers();
        bean.getCustomerById(1l);
        bean.createCustomer("rest","client","4224242424");
        bean.updateCustomer(25l,"so" , "far","222222");
        bean.deleteCustomer(26l);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
