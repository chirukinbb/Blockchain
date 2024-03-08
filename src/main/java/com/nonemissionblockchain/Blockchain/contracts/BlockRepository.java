package com.nonemissionblockchain.Blockchain.contracts;

import com.nonemissionblockchain.Blockchain.models.Block;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlockRepository extends MongoRepository<Block, String> {
}
