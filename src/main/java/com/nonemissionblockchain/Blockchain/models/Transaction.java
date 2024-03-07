package com.nonemissionblockchain.Blockchain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    private String sender;
    private String recipient;
    private double amount;
    private long timestamp;
    
    public Transaction(String sender, String recipient, double amount, long timestamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
