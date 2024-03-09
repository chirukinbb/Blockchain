package com.nonemissionblockchain.Blockchain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    final private String nickname;
    final private String publicKey;

    public User(String nickname, String publicKey) {
        this.nickname = nickname;
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
