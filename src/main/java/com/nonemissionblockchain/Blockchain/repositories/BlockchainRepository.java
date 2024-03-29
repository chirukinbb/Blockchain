package com.nonemissionblockchain.Blockchain.repositories;

import com.nonemissionblockchain.Blockchain.contracts.BlockRepository;
import com.nonemissionblockchain.Blockchain.contracts.TransactionRepository;
import com.nonemissionblockchain.Blockchain.contracts.UserRepository;
import com.nonemissionblockchain.Blockchain.models.Block;
import com.nonemissionblockchain.Blockchain.models.Transaction;
import com.nonemissionblockchain.Blockchain.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BlockchainRepository {

    final private BlockRepository blockRepository;
    final private TransactionRepository transactionRepository;
    final private UserRepository userRepository;

    public BlockchainRepository(BlockRepository blockRepository, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.blockRepository = blockRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<Block> blocks() {
        return this.blockRepository.findAll();
    }

    public List<Transaction> block(String id) throws Exception {
        Optional<Block> block = blockRepository.findById(id);

        if (block != null) {
            return transactionRepository.findAllById(block.get().getTransactions());
        } else throw new Exception("Block not exists");
    }

    public Double getBalance(String address) {
        User user = userRepository.findByNickname(address);

        return (user == null) ? 0 : user.getBalance();
    }

    public String getPublicKey(String nickname) {
        User user = this.userRepository.findByNickname(nickname);

        if (user != null) {
            return user.getPublicKey();
        } else {
            // Обработка случая, когда пользователь не найден
            return null; // или бросить исключение или вернуть какой-то стандартный ключ
        }
    }

    public Double balanceForAddressInBlockchain(String address) {
        double balance = (double) 0;
        List<Transaction> senders = this.transactionRepository.findBySender(address);
        List<Transaction> recipients = this.transactionRepository.findByRecipient(address);

        for (Transaction transaction :
                senders) {
            balance -= transaction.getAmount();
        }

        for (Transaction transaction :
                recipients) {
            balance += transaction.getAmount();
        }

        return balance;
    }
}
