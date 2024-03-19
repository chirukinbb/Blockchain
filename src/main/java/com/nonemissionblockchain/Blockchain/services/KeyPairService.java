package com.nonemissionblockchain.Blockchain.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

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

    public static PrivateKey privateKeyFromString(String privateKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);

        // Создаем объект PKCS8EncodedKeySpec из декодированных байтов
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        // Получаем приватный ключ из спецификации
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey publicKeyFromString(String publicKeyString) throws Exception {
        // Декодируем строку из шестнадцатеричного формата
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);

        // Создаем объект X509EncodedKeySpec из декодированных байтов
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);

        // Получаем публичный ключ из спецификации
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePublic(keySpec);
    }

    public static byte[] signatureFromString(String sign) {
        // Декодируем строку из Base64 в байтовый массив
        return Base64.getDecoder().decode(sign);
    }
}
