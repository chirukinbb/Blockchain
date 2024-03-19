package com.nonemissionblockchain.Blockchain.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.security.PublicKey;
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
        byte[] signature = KeyPairService.signWithPrivateKey(jsonString, KeyPairService.privateKeyFromString(privateKeyString));
        String sign = Base64.getEncoder().encodeToString(signature);
        // Проверяем подпись с помощью открытого ключа
        boolean isVerified = KeyPairService.verifyWithPublicKey(jsonString, KeyPairService.signatureFromString(sign), KeyPairService.publicKeyFromString(publicKeyString));

        // Проверяем, успешна ли верификация
        assertTrue(isVerified, "Signature verification failed");
    }
}

