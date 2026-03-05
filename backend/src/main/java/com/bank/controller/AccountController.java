package com.bank.controller;

import com.bank.model.Account;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, Object> request) {
        String holderName = (String) request.get("holderName");
        Double initialDeposit = ((Number) request.get("initialDeposit")).doubleValue();
        Account account = accountService.createAccount(holderName, initialDeposit);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable Integer accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Integer accountNumber, @RequestBody Map<String, Double> request) {
        Account account = accountService.deposit(accountNumber, request.get("amount"));
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Integer accountNumber, @RequestBody Map<String, Double> request) {
        try {
            Account account = accountService.withdraw(accountNumber, request.get("amount"));
            return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/{accountNumber}/loan")
    public ResponseEntity<Account> applyLoan(@PathVariable Integer accountNumber, @RequestBody Map<String, Double> request) {
        Account account = accountService.applyLoan(accountNumber, request.get("amount"));
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{accountNumber}/repay")
    public ResponseEntity<?> repayLoan(@PathVariable Integer accountNumber, @RequestBody Map<String, Double> request) {
        try {
            Account account = accountService.repayLoan(accountNumber, request.get("amount"));
            return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> closeAccount(@PathVariable Integer accountNumber) {
        boolean deleted = accountService.closeAccount(accountNumber);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
