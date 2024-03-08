package com.nonemissionblockchain.Blockchain.services;

import com.nonemissionblockchain.Blockchain.contracts.BlockRepository;
import com.nonemissionblockchain.Blockchain.models.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlockService {
    private final BlockRepository blockRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public Block saveBlock(Block block) {
        return blockRepository.save(block);
    }
}
