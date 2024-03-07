package com.nonemissionblockchain.Blockchain.controllers;

import com.nonemissionblockchain.Blockchain.services.KeyPairGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class WalletController {
    @GetMapping("/get-new-keys")
    public KeyPairGenerator keysGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator wallet = new KeyPairGenerator();

        return wallet;
    }
}
