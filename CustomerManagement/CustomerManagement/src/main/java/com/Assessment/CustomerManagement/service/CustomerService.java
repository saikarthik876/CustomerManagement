package com.Assessment.CustomerManagement.service;

import com.Assessment.CustomerManagement.dto.CustomerRequest;
import com.Assessment.CustomerManagement.dto.CustomerResponse;
import com.Assessment.CustomerManagement.entity.Customer;
import com.Assessment.CustomerManagement.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerCalculationService  calculationService;

    public CustomerService(CustomerRepository customerRepository, CustomerCalculationService calculationService) {
        this.customerRepository = customerRepository;
        this.calculationService = calculationService;
    }

    @Transactional
    public CustomerResponse createCustomer(@Valid CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAnnualSpend(request.getAnnualSpend());
        customer.setLastPurchaseDate(request.getLastPurchaseDate());
        customer = customerRepository.save(customer);
        return toResponse(customer);
    }

    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById() {
        throw new IllegalArgumentException("Customer ID must not be null");
    }

    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return toResponse(customer);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerResponse> getCustomerByName(String name) {
        return customerRepository.findByName(name).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerResponse> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).map(this::toResponse);
    }

    @Transactional
    public CustomerResponse updateCustomer(UUID id, @Valid CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAnnualSpend(request.getAnnualSpend());
        customer.setLastPurchaseDate(request.getLastPurchaseDate());
        customer = customerRepository.save(customer);
        return toResponse(customer);
    }

    @Transactional
    public void deleteCustomer(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    private CustomerResponse toResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setAnnualSpend(customer.getAnnualSpend());
        response.setLastPurchaseDate(customer.getLastPurchaseDate());
        response.setTier(calculationService.calculateTier(customer));
        return response;
    }
}

