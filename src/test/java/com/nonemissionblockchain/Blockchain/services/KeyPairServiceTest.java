package com.nonemissionblockchain.Blockchain.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class KeyPairServiceTest {

    @Test
    public void testSignAndVerifyJson() throws Exception {
        // Создаем тестовую JSON-строку
        String jsonString = "Руддщ";

        // Получаем открытый и закрытый ключи из сервиса KeyPairService
        KeyPairService keyPairService = new KeyPairService();
        PublicKey publicKey = keyPairService.getPublicKey();
        PrivateKey privateKey = keyPairService.getPrivateKey();

        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        // Преобразуем JSON-строку в URL-кодированные данные

        // Подписываем данные с помощью закрытого ключа
        byte[] signature = KeyPairService.signWithPrivateKey(jsonString, privateKeyFromString(privateKeyString));
        String sign = Base64.getEncoder().encodeToString(signature);
        // Проверяем подпись с помощью открытого ключа
        boolean isVerified = KeyPairService.verifyWithPublicKey(jsonString, signatureFromString(sign), publicKeyFromString(publicKeyString));

        // Проверяем, успешна ли верификация
        assertTrue(isVerified, "Signature verification failed");
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

