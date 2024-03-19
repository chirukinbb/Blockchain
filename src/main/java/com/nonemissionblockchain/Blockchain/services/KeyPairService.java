package com.nonemissionblockchain.Blockchain.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.security.*;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyPairService {

    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public KeyPairService() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();

            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key pair", e);
        }
    }

    static public boolean verifyWithPublicKey(String data, byte[] signature, PublicKey publicKey) {
        try {
            Signature verifySignature = Signature.getInstance("SHA256withRSA");
            verifySignature.initVerify(publicKey);
            verifySignature.update(data.getBytes());

            return verifySignature.verify(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException("Error verifying signature with public key", e);
        }
    }

    static public byte[] signWithPrivateKey(String data, PrivateKey privateKey) {
        try {
            //byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKeyString);
            //PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
            //KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            //PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(data.getBytes());

            return sign.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException("Error signing data with private key", e);
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
