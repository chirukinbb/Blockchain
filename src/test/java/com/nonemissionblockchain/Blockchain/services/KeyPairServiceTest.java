package com.nonemissionblockchain.Blockchain.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class KeyPairServiceTest {

    @Test
    public void testSignAndVerifyJson() {
        // Создаем тестовую JSON-строку
        String jsonString = "Руддщ";

        // Получаем открытый и закрытый ключи из сервиса KeyPairService
        KeyPairService keyPairService = new KeyPairService();
        PublicKey publicKey = keyPairService.getPublicKey();
        PrivateKey privateKey = keyPairService.getPrivateKey();

        // Преобразуем JSON-строку в URL-кодированные данные

        // Подписываем данные с помощью закрытого ключа
        byte[] signature = KeyPairService.signWithPrivateKey(jsonString, privateKey);

        // Проверяем подпись с помощью открытого ключа
        boolean isVerified = KeyPairService.verifyWithPublicKey(jsonString, signature, publicKey);

        // Проверяем, успешна ли верификация
        assertTrue(isVerified, "Signature verification failed");
    }
}

