package com.nonemissionblockchain.Blockchain.controllers;

import com.nonemissionblockchain.Blockchain.repositories.BlockchainRepository;
import com.nonemissionblockchain.Blockchain.services.KeyPairGenerator;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class WalletController {
    final private BlockchainRepository blockchainRepository;
    final private KeyPairGenerator keyPairGenerator;
    public WalletController(BlockchainRepository blockchainRepository,KeyPairGenerator keyPairGenerator)
    {
        this.blockchainRepository = blockchainRepository;
        this.keyPairGenerator = keyPairGenerator;
    }
    @GetMapping("/get-new-keys")
    public KeyPairGenerator keysGenerator() {
        return this.keyPairGenerator;
    }
    @PostMapping("/get-balance")
    public Double getBalance(@RequestParam String address) {
        return this.blockchainRepository.balance(address);
    }
}
