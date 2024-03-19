package com.nonemissionblockchain.Blockchain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    final private String nickname;
    final private String publicKey;

    private Double balance;

    public User(String nickname, String publicKey) {
        this.nickname = nickname;
        this.publicKey = publicKey;
        this.balance = (double) 0;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public Double getBalance() {
        return balance;
    }

    public void addAmount(Double amount) {
        this.balance += amount;
    }

    public void minusAmount(Double amount) {
        this.balance -= amount;
    }
}
