package com.nonemissionblockchain.Blockchain.controllers;

import com.nonemissionblockchain.Blockchain.models.Transaction;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BlockchainController {
    private List<Transaction> transactions;

    @GetMapping("/addTransaction")
    public void addTransaction(Request request){

    }
}
