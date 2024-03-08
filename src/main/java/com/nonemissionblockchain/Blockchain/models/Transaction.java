package com.nonemissionblockchain.Blockchain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        this.id = calculateHash();
    }
    // Метод для вычисления хэша транзакции
    private String calculateHash() {
        String data = sender + recipient + Double.toString(amount) + Long.toString(timestamp);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes());

            // Преобразование массива байт в строку шестнадцатеричных символов
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Алгоритм хеширования не найден: " + e.getMessage());
            return null;
        }
    }
}
