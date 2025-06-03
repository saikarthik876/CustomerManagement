package com.Assessment.CustomerManagement.service;

import com.Assessment.CustomerManagement.entity.Customer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class CustomerCalculationService {

    public String calculateTier(Customer customer) {
        BigDecimal spend = customer.getAnnualSpend();
        LocalDateTime lastPurchaseDate = customer.getLastPurchaseDate();

        if (spend == null || lastPurchaseDate == null) {
            return "Unknown";
        }

        long monthsSinceLastPurchase = ChronoUnit.MONTHS.between(lastPurchaseDate, LocalDateTime.now());

        if (spend.compareTo(BigDecimal.valueOf(10000)) >= 0 && monthsSinceLastPurchase <= 6) {
            return "Platinum";
        } else if (spend.compareTo(BigDecimal.valueOf(1000)) >= 0 &&
                spend.compareTo(BigDecimal.valueOf(10000)) < 0 &&
                monthsSinceLastPurchase <= 12) {
            return "Gold";
        } else if (spend.compareTo(BigDecimal.valueOf(1000)) < 0) {
            return "Silver";
        }

        return "Unknown"; // No valid tier
    }
}