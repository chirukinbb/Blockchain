package com.nonemissionblockchain.Blockchain.controllers;

import com.nonemissionblockchain.Blockchain.models.Block;
import com.nonemissionblockchain.Blockchain.repositories.BlockchainRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {
    private final BlockchainRepository blockchainRepository;

    public MainController(BlockchainRepository  blockchainRepository)
    {
        this.blockchainRepository = blockchainRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        return "home";
    }

    @GetMapping("/blocks")
    public String viewBlocks(Model model){
        List<Block> blocks = this.blockchainRepository.blocks();
        model.addAttribute("blocks",blocks);

        return "blocks";
    }

    @GetMapping("/block/{id}")
    public String viewBlock(@PathVariable String id,Model model) {
        model.addAttribute("block",blockchainRepository.block(id));

        return "block";
    }

    @GetMapping("/transaction/{id}")
    public String viewTransaction(@PathVariable String id,Model model) {
        model.addAttribute("transaction",blockchainRepository.block(id));

        return "transaction";
    }
}
