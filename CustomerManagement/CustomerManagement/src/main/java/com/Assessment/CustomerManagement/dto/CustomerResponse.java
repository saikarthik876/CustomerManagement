package com.Assessment.CustomerManagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerResponse {
    private UUID id;
    private String name;
    private String email;
    private BigDecimal annualSpend;
    private LocalDateTime lastPurchaseDate;
    private String tier;

    public CustomerResponse() {
    }

    public CustomerResponse(UUID id, String name, String email, BigDecimal annualSpend, LocalDateTime lastPurchaseDate, String tier) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.annualSpend = annualSpend;
        this.lastPurchaseDate = lastPurchaseDate;
        this.tier = tier;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAnnualSpend() {
        return annualSpend;
    }

    public void setAnnualSpend(BigDecimal annualSpend) {
        this.annualSpend = annualSpend;
    }

    public LocalDateTime getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public void setLastPurchaseDate(LocalDateTime lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
    public static class Builder {
        private UUID id;
        private String name;
        private String email;
        private BigDecimal annualSpend;
        private LocalDateTime lastPurchaseDate;
        private String tier;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder annualSpend(BigDecimal annualSpend) {
            this.annualSpend = annualSpend;
            return this;
        }

        public Builder lastPurchaseDate(LocalDateTime lastPurchaseDate) {
            this.lastPurchaseDate = lastPurchaseDate;
            return this;
        }

        public Builder tier(String tier) {
            this.tier = tier;
            return this;
        }

        public CustomerResponse builder() {
            return new CustomerResponse(id, name, email, annualSpend, lastPurchaseDate, tier);
        }
    }
}
