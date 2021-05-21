package com.kubewarrior.checkout;

import com.kubewarrior.checkout.domain.Userorder;
import com.kubewarrior.checkout.repository.UserorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CheckoutController {

    @Autowired
    private UserorderRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/checkoutOrder")
    public ResponseEntity<Userorder> checkoutOrder(@RequestBody Userorder userorder) {

        return new ResponseEntity<>(repository.save(userorder), HttpStatus.OK);

    }

    @GetMapping("/orders")
    public List<Userorder> checkoutOrder() {
        //call Store API
        System.out.println(restTemplate.getForObject("http://localhost:8081/products/1", String.class));
        return (List<Userorder>) repository.findAll();
    }
}
