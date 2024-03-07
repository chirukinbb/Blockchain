package com.nonemissionblockchain.Blockchain.services;

import java.security.*;

public class KeyPairGenerator {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    public KeyPairGenerator() throws NoSuchAlgorithmException {
        java.security.KeyPairGenerator keyGen = java.security.KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        KeyPair keyPair = keyGen.generateKeyPair();
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
