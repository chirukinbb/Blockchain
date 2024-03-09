package com.nonemissionblockchain.Blockchain.contracts;

import com.nonemissionblockchain.Blockchain.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByNickname(String nickname);
}
