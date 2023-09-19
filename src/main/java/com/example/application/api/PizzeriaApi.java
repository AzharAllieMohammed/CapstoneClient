package com.example.application.api;

import com.example.application.domain.Customer;
import com.example.application.domain.Pizzeria;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 PizzeriaApi.java
 Author: Timothy Lombard (220154856)
 Date: 18st September (last updated) 2023
 */
@RestController
public class PizzeriaApi {
    static RestTemplate restTemplate = new RestTemplate();
//    @Autowired
//    private RestTemplate restTemplate2;
    private static String urlPizzeria = "http://localhost:8080/pizzeria";

    @GetMapping("/api/ping")
    public String ping() {
        System.out.println("Received ping request");
        return "pong";
    }

    public Pizzeria readPizzeria(String id){
        Pizzeria p = restTemplate.getForObject(urlPizzeria + "/read/" + id, Pizzeria.class);
        System.out.println(p.toString());
    return p;
    }

//    public Pizzeria readPizzeria(String id){
//        Pizzeria p = restTemplate.getForObject(urlPizzeria + "/read/" + id, Pizzeria.class);
//        System.out.println(p.toString());
//        return p;
//    }

    public void get() {
        Pizzeria o = new Pizzeria();
        HttpEntity<Pizzeria> request = new HttpEntity<>(new Pizzeria());
        Pizzeria customer = restTemplate.postForObject(urlPizzeria, request, Pizzeria.class);
        System.out.println(customer.toString());
    }
    public void delete(String id) {
        String entityUrl = urlPizzeria + "/" + id;
        restTemplate.delete(entityUrl);
    }
    public Set<Pizzeria> getAllPizzeria() {
        String apiUrl = urlPizzeria + "/getAll";
        ResponseEntity<Pizzeria[]> response1 = restTemplate.getForEntity(apiUrl, Pizzeria[].class);

        if (response1.getStatusCode().is2xxSuccessful()) {
            Pizzeria[] pizzerias = response1.getBody();
            return new HashSet<>(Arrays.asList(pizzerias));
        } else {

            return Collections.emptySet();
        }
    }
}
