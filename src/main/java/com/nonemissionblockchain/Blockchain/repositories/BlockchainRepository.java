package com.nonemissionblockchain.Blockchain.repositories;

import com.nonemissionblockchain.Blockchain.contracts.BlockRepository;
import com.nonemissionblockchain.Blockchain.models.Block;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BlockchainRepository {

    final private BlockRepository blockRepository;

    public BlockchainRepository(BlockRepository blockRepository)
    {
        this.blockRepository = blockRepository;
    }

    public List<Block> blocks()
    {
        return this.blockRepository.findAll();
    }

    public Optional<Block> block(String id)
    {
        return blockRepository.findById(id);
    }

    public Double balance(String address)
    {
        return 1.1;
    }
}
