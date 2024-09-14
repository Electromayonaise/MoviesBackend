package icesi.movies.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import icesi.movies.backend.model.Customer;
import icesi.movies.backend.repositories.CustomerRepository;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping
    public ResponseEntity<Object> login(@RequestParam String email, @RequestParam String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            // Authentication successful
            String role = customer.getRole().name();
            Long userId = customer.getId(); // Get user ID
            return new ResponseEntity<>(Map.of("role", role, "userId", userId), HttpStatus.OK); // Include userId in response
        } else {
            // Authentication failed
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}

