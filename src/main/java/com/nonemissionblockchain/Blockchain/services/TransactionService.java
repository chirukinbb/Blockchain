package com.nonemissionblockchain.Blockchain.services;

import com.nonemissionblockchain.Blockchain.contracts.TransactionRepository;
import com.nonemissionblockchain.Blockchain.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }
}
