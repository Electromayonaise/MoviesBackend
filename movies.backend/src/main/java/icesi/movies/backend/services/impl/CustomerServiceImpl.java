package icesi.movies.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import icesi.movies.backend.model.Customer;
import icesi.movies.backend.repositories.CustomerRepository;
import icesi.movies.backend.services.interfaces.CustomerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import icesi.movies.backend.model.Role;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        if (customer.getRole() == null) {
            customer.setRole(Role.USER);
        } 
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
