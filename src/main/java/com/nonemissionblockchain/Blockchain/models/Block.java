package com.nonemissionblockchain.Blockchain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Document(collection = "blocks")
public class Block {
    @Id
    private String id;
    private String previousHash; // Хэш предыдущего блока
    private long timestamp; // Временная метка создания блока
    private List<String> transactions; // Список id транзакций в блоке
    private int nonce; // Значение nonce, используемое для поиска правильного хэша блока

    public Block(String previousHash, List<String> transactions, int nonce) {
        this.previousHash = previousHash;
        this.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        this.transactions = transactions;
        this.nonce = nonce;
    }

    public String getId() {
        return id;
    }

    public int getNonce() {
        return nonce;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

