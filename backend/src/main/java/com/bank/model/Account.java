package com.bank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Integer accountNumber;
    
    @Column(name = "holder_name", nullable = false)
    private String holderName;
    
    @Column(nullable = false)
    private Double balance;
    
    @Column(name = "loan_amount")
    private Double loanAmount = 0.0;

    public Account() {}

    public Account(String holderName, Double balance) {
        this.holderName = holderName;
        this.balance = balance;
        this.loanAmount = 0.0;
    }

    public Integer getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Integer accountNumber) { this.accountNumber = accountNumber; }
    
    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    
    public Double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(Double loanAmount) { this.loanAmount = loanAmount; }
}
