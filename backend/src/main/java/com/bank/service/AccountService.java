package com.bank.service;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String holderName, Double initialDeposit) {
        Account account = new Account(holderName, initialDeposit);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Integer accountNumber) {
        return accountRepository.findById(accountNumber).orElse(null);
    }

    public Account deposit(Integer accountNumber, Double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return accountRepository.save(account);
        }
        return null;
    }

    public Account withdraw(Integer accountNumber, Double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            if (amount <= account.getBalance()) {
                account.setBalance(account.getBalance() - amount);
                return accountRepository.save(account);
            } else {
                throw new RuntimeException("Insufficient funds.");
            }
        }
        return null;
    }

    public Account applyLoan(Integer accountNumber, Double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.setLoanAmount(account.getLoanAmount() + amount);
            account.setBalance(account.getBalance() + amount);
            return accountRepository.save(account);
        }
        return null;
    }

    public Account repayLoan(Integer accountNumber, Double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            if (amount > account.getBalance()) {
                throw new RuntimeException("Insufficient balance to repay loan.");
            } else if (amount > account.getLoanAmount()) {
                // Repaying more than loan amount - adjust to exact loan amount
                account.setBalance(account.getBalance() - account.getLoanAmount());
                account.setLoanAmount(0.0);
                return accountRepository.save(account);
            } else {
                account.setBalance(account.getBalance() - amount);
                account.setLoanAmount(account.getLoanAmount() - amount);
                return accountRepository.save(account);
            }
        }
        return null;
    }

    public boolean closeAccount(Integer accountNumber) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            accountRepository.delete(account);
            return true;
        }
        return false;
    }
}
