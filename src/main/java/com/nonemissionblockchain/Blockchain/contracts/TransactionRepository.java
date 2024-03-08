package com.nonemissionblockchain.Blockchain.contracts;

import com.nonemissionblockchain.Blockchain.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String>  {
}
