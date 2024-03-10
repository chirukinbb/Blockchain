package com.nonemissionblockchain.Blockchain.controllers;

import com.nonemissionblockchain.Blockchain.DTO.TransactionDTO;
import com.nonemissionblockchain.Blockchain.models.Block;
import com.nonemissionblockchain.Blockchain.models.Transaction;
import com.nonemissionblockchain.Blockchain.repositories.BlockchainRepository;
import com.nonemissionblockchain.Blockchain.services.BlockService;
import com.nonemissionblockchain.Blockchain.services.KeyPairService;
import com.nonemissionblockchain.Blockchain.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BlockchainController {
    private List<String> transactions;
    final private BlockchainRepository blockchainRepository;
    final private TransactionService transactionService;
    final private BlockService blockService;
    final private KeyPairService keyPairService;

    public BlockchainController(BlockchainRepository blockchainRepository, KeyPairService keyPairService, TransactionService transactionService, BlockService blockService) {
        this.blockchainRepository = blockchainRepository;
        this.transactionService = transactionService;
        this.blockService = blockService;
        this.keyPairService = keyPairService;
        this.transactions = new ArrayList<>();
    }

    @PostMapping("/add-transaction")
    public void addTransaction(@RequestParam TransactionDTO data) {
        String publicKey = this.blockchainRepository.getPublicKey(data.nickname);

        if (KeyPairService.verifyWithPublicKey(data.transaction.toByteArray(), data.signature, publicKey)) {
            System.out.println(data);
            if (this.blockchainRepository.getBalance(data.nickname) > 0) {
                this.transactionService.save(data.transaction);
            }
        }
    }

    @GetMapping("/genesis")
    public void genesis() {
        Transaction transaction = this.transactionService.save(new Transaction("system", "genesys_block", 100000000));
        List<String> transactions = new ArrayList<>();
        transactions.add(transaction.getId());

        this.blockService.saveBlock(new Block("GenesisBlock", transactions, 0));
    }
}
