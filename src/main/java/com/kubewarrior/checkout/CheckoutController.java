package com.kubewarrior.checkout;

import com.kubewarrior.checkout.domain.Product;
import com.kubewarrior.checkout.domain.Userorder;
import com.kubewarrior.checkout.repository.UserorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
public class CheckoutController {

    @Autowired
    private UserorderRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping(path = "/checkoutOrder", consumes = "application/json")
    public ResponseEntity<String> checkoutOrder(@RequestBody List<Userorder> orders) {

    for (Userorder userorder : orders) {
        Product product = null;
        // String response;
        String url = "http://localhost:8081/products/" + userorder.getProductId();
        //call Store API
        try {
            product = (Product) restTemplate.getForObject(url, Product.class);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not connect with Stock service. Pls contact administrator", HttpStatus.EXPECTATION_FAILED);
        }
        System.out.println("Stock count: " + product.getStockCount());
        if (product.getStockCount() > 0) {
            repository.save(userorder);
            //after saving send msg on kafka topic
            kafkaTemplate.send("checout_topic", userorder);
        }
    }
    // How to report if some products could not be placed in the order due to zero stock!?
    return new ResponseEntity<>("Order checkout done!", HttpStatus.OK);
    // return new ResponseEntity<>("Order could not be checked out due to insufficient stock.", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/orders")
    public List<Userorder> checkoutOrder() {

        return (List<Userorder>) repository.findAll();
    }
}
