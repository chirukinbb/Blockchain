package com.nonemissionblockchain.Blockchain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nonemissionblockchain.Blockchain.DTO.TransactionDTO;
import com.nonemissionblockchain.Blockchain.contracts.TransactionRepository;
import com.nonemissionblockchain.Blockchain.models.Block;
import com.nonemissionblockchain.Blockchain.models.Transaction;
import com.nonemissionblockchain.Blockchain.repositories.BlockchainRepository;
import com.nonemissionblockchain.Blockchain.services.BlockService;
import com.nonemissionblockchain.Blockchain.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BlockchainController {
    private final List<String> transactions;
    final private BlockchainRepository blockchainRepository;
    final private TransactionService transactionService;
    final private TransactionRepository transactionRepository;
    final private BlockService blockService;

    public BlockchainController(BlockchainRepository blockchainRepository, TransactionService transactionService, BlockService blockService, TransactionRepository transactionRepository) {
        this.blockchainRepository = blockchainRepository;
        this.transactionService = transactionService;
        this.blockService = blockService;
        this.transactionRepository = transactionRepository;
        this.transactions = new ArrayList<>();
    }

    @PostMapping(value = "/add-transaction")
    public void addTransaction(@RequestParam String transaction, @RequestParam String signature) throws JsonProcessingException {
        TransactionDTO transactionDTO = TransactionDTO.jsonInit(transaction);
        String publicKey = this.blockchainRepository.getPublicKey(transactionDTO.getSender());

        System.out.println(transaction);

//        if (KeyPairService.verifyWithPublicKey(transaction, signature, publicKey)) {
//            System.out.println(transaction);
//            if (this.blockchainRepository.getBalance(transactionDTO.getSender()) > transactionDTO.getAmount()) {
//                Transaction modelTransaction = new Transaction(transactionDTO.getSender(), transactionDTO.getRecipient(), transactionDTO.getAmount());
//                this.transactionService.save(modelTransaction);
//                this.transactions.add(modelTransaction.getId());
//            }
//        }
    }

    @PostMapping(value = "/add-transaction2")
    public void addTransaction2(@RequestBody String transaction, @RequestParam String signature) throws JsonProcessingException {
//        System.out.println(transaction);
//        TransactionDTO transactionDTO = TransactionDTO.jsonInit(transaction);
//        String publicKey = this.blockchainRepository.getPublicKey(transactionDTO.getSender());
//
//        // Подписание данных
//        byte[] signedData = KeyPairService.signWithPrivateKey(transaction, signature);
//        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signedData)); // Вывод подписи для отладки
//
//        // Проверка подписи
//        boolean signatureValid = KeyPairService.verifyWithPublicKey(transaction, Base64.getEncoder().encodeToString(signedData), publicKey);
//        System.out.println("Signature Valid: " + signatureValid);
    }


    @GetMapping("/genesis")
    public void genesis() {
        Transaction transaction = this.transactionService.save(new Transaction("system", "genesys_block", 100000000));
        List<String> transactions = new ArrayList<>();
        transactions.add(transaction.getId());
        this.blockService.saveBlock(new Block("GenesisBlock", transactions, 0));
    }
}
