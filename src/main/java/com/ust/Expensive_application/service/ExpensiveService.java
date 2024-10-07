package com.ust.Expensive_application.service;

import com.ust.Expensive_application.dto.Expensivedto;
import com.ust.Expensive_application.exception.ExpensiveNotFoundException;
import com.ust.Expensive_application.model.Expensive;
import com.ust.Expensive_application.repo.Expensiverepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ExpensiveService {


    @Autowired
    private Expensiverepo repo;


    public Expensive addExpensive(Expensivedto dto) {
        Expensive expensive=new Expensive();
        expensive.setName(dto.getName());
        expensive.setTypetranscations(dto.getTypetranscations());
        expensive.setAmount(dto.getAmount());
        expensive.setDescription(dto.getDescription());
        return repo.save(expensive);

    }

    public Expensive getExpensive(UUID id) throws ExpensiveNotFoundException {
        Expensive expensive=repo.findById(id)
                .orElseThrow(()->new ExpensiveNotFoundException("Expensive not found with id: "+id));
        return expensive;
    }

    public double getBalanceByDate(LocalDate date) {
        List<Expensive> entries = repo.findByDate(date);
        double balance = 0;
        for (Expensive exp : entries) {
            if (exp.isExpense()) {
                balance -= exp.getAmount();
            } else {
                balance += exp.getAmount();
            }
        }
        return balance;
    }

    public double getExpenseByPaymentMethod(String typetranscations) {
        List<Expensive> entries = repo.findByPaymentMethod(typetranscations);
        double balance = 0;
        for (Expensive exp : entries) {
            if (exp.isExpense()) {
                balance -= exp.getAmount();
            } else {
                balance += exp.getAmount();
            }
        }
        return balance;
    }


}
