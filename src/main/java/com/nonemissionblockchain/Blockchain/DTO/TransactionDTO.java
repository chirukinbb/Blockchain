package com.nonemissionblockchain.Blockchain.DTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class TransactionDTO implements Serializable {
    public String sender;
    public String recipient;
    public double amount;

    static public TransactionDTO jsonInit(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, TransactionDTO.class);
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES * 3 + sender.length() + recipient.length());
        buffer.put(sender.getBytes());
        buffer.put(recipient.getBytes());
        buffer.putDouble(amount);
        return buffer.array();
    }
}
