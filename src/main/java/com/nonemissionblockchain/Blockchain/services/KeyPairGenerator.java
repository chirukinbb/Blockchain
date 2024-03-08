package com.nonemissionblockchain.Blockchain.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.security.*;
import java.util.Base64;

@Component
public class KeyPairGenerator {
    @JsonProperty("publicKey")
    public final String publicKey;

    @JsonProperty("privateKey")
    public final String privateKey;

    public KeyPairGenerator() throws NoSuchAlgorithmException {
        java.security.KeyPairGenerator keyGen = java.security.KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        KeyPair keyPair = keyGen.generateKeyPair();
        this.publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        this.privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }
}
