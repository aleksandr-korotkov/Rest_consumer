package org.restclient;

import org.apache.log4j.Logger;
import org.restclient.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestClientSirvice {
    private static Logger logger = Logger.getLogger(RestClientSirvice.class);
    private final String ROOT_URI = "http://localhost:8080/customers";

    RestTemplate restTemplate;

    @Autowired
    public RestClientSirvice(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer getCustomerById(Long id){
        Customer customer = restTemplate.getForObject(ROOT_URI + "/" + id, Customer.class);
        logger.info("request: getCustomerById. Response " + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getPhone() );
        return customer;
    }

    public List<Customer> getAllCustomers(){
        ResponseEntity<Customer[]> response = restTemplate.getForEntity(ROOT_URI, Customer[].class);
        Arrays.stream(response.getBody()).forEach(customer -> {
            logger.info("Request: getAllCustomers. Response " +
                    customer.getId() + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getPhone());
        });
        return Arrays.asList(response.getBody());
    }

    public Customer createCustomer(String firstName, String lastName,String phone) {
        ResponseEntity<Customer> response = restTemplate.postForEntity(ROOT_URI, new Customer(firstName,lastName,phone), Customer.class);
        logger.info("Request: createCustomer. Response " + response.getStatusCode());
        return response.getBody();
    }

    public void updateCustomer(Long id, String firstName, String lastName,String phone) {
        restTemplate.put(ROOT_URI + "/" + id,new Customer(firstName,lastName,phone));
        logger.info("Request: updateCustomer called.");
    }

    public void deleteCustomer(Long id) {
        System.out.println("delete");
        restTemplate.delete ( ROOT_URI + "/" + id);
        logger.info("Request: deleteCustomer called.");
    }

}
