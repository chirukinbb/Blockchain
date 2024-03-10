package com.nonemissionblockchain.Blockchain.controllers;

import com.nonemissionblockchain.Blockchain.contracts.UserRepository;
import com.nonemissionblockchain.Blockchain.models.User;
import com.nonemissionblockchain.Blockchain.repositories.BlockchainRepository;
import com.nonemissionblockchain.Blockchain.services.KeyPairService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WalletController {
    final private BlockchainRepository blockchainRepository;
    final private KeyPairService keyPairService;
    final private UserRepository userRepository;

    public WalletController(BlockchainRepository blockchainRepository, KeyPairService keyPairService, UserRepository userRepository) {
        this.blockchainRepository = blockchainRepository;
        this.keyPairService = keyPairService;
        this.userRepository = userRepository;
    }

    @PostMapping("/get-private-key")
    public String keysGenerator(@RequestParam String nickname) {
        String privateKey = this.keyPairService.getPrivateKey();
        String publicKey = this.keyPairService.getPublicKey();

        this.userRepository.save(new User(nickname, publicKey));

        return privateKey;
    }

    @PostMapping("/get-balance")
    public Double getBalance(@RequestParam String address) {
        return this.blockchainRepository.getBalance(address);
    }
}
