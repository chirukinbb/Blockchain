package com.nonemissionblockchain.Blockchain.DTO;

import com.nonemissionblockchain.Blockchain.models.Transaction;

public class TransactionDTO {
    public Transaction transaction;
    public byte[] signature;
    public String nickname;

    public TransactionDTO(Transaction transaction, byte[] signature, String nickname) {
        this.transaction = transaction;
        this.signature = signature;
        this.nickname = nickname;
    }
}
