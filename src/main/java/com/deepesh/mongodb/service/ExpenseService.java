package com.deepesh.mongodb.service;

import com.deepesh.mongodb.model.Expense;
import com.deepesh.mongodb.repos.ExpenseRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepos expenseRepos;

    public ExpenseService(ExpenseRepos expenseRepos) {
        this.expenseRepos = expenseRepos;
    }


    public void addExpense (Expense expense){
        expenseRepos.insert(expense);
    }
    public void updateExpense (Expense expense){
        Expense savedExpense =expenseRepos.findById(expense.getId())
                .orElseThrow(()->new RuntimeException(
                        String.format("Cannot find expense by Id %s",
                                expense.getId())
                ));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory((expense.getExpenseCategory()));
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepos.save(expense);


    }

    public List<Expense> getAllExpense (){
        return expenseRepos.findAll();
    }
    public Expense getExpenseByName (String name){
        return expenseRepos.findByName(name).orElseThrow(()-> new RuntimeException(
                String.format("couldn\'t find by name %s", name)
        ));
    }
    public void deleteExpense (String id){
        expenseRepos.deleteById(id);
    }

}
