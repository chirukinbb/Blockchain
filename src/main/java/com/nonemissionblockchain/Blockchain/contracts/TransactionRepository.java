package com.nonemissionblockchain.Blockchain.contracts;

import com.nonemissionblockchain.Blockchain.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByRecipient(String address);

    List<Transaction> findBySender(String address);
}
