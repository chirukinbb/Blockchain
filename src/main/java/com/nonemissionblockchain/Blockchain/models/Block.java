package com.nonemissionblockchain.Blockchain.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blocks")
public class Block {
    @Id
    private String id;
    private String previousHash; // Хэш предыдущего блока
    private long timestamp; // Временная метка создания блока
    private List<String> transactions; // Список id транзакций в блоке
    private int nonce; // Значение nonce, используемое для поиска правильного хэша блока

    public Block(String previousHash, long timestamp, List<String> transactions, int nonce, String hash) {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.nonce = nonce;
    }
}

