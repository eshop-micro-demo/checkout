package com.kubewarrior.checkout;

import com.kubewarrior.checkout.domain.Product;
import com.kubewarrior.checkout.domain.Userorder;
import com.kubewarrior.checkout.repository.UserorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CheckoutController {

    @Autowired
    private UserorderRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "/checkoutOrder", consumes = "application/json")
    public ResponseEntity<String> checkoutOrder(@RequestBody Userorder userorder) {

        Product product = null;
        String response;
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
            return new ResponseEntity<>("Order checkout done!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Order could not be checked out due to insufficient stock.", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/orders")
    public List<Userorder> checkoutOrder() {

        return (List<Userorder>) repository.findAll();
    }
}
