package org.chulgang.hrd.wallethistory.model.service;

import org.chulgang.hrd.wallethistory.domain.WalletHistory;
import org.chulgang.hrd.wallethistory.model.repository.WalletHistoryRepository;
import org.chulgang.hrd.wallethistory.model.repository.WalletHistoryRepositoryImpl;

import java.time.LocalDateTime;

public class WalletHistoryServiceImpl implements WalletHistoryService {
    private static final WalletHistoryServiceImpl INSTANCE = new WalletHistoryServiceImpl(WalletHistoryRepositoryImpl.getInstance());
    private final WalletHistoryRepository walletHistoryRepository;

    public WalletHistoryServiceImpl(WalletHistoryRepository walletHistoryRepository) {
        this.walletHistoryRepository = walletHistoryRepository;
    }

    public static WalletHistoryServiceImpl getInstance() {return INSTANCE; }

    @Override
    public WalletHistory getLatestWalletHistoryByUserId(Long userId) {
        return walletHistoryRepository.findLatestWalletHistoryByUserId(userId)
                .orElseGet(() -> {
                    WalletHistory defaultHistory = new WalletHistory();
                    defaultHistory.setUserId(userId);
                    defaultHistory.setAddedAmount(0);
                    defaultHistory.setUsedAmount(0);
                    defaultHistory.setRefundedAmount(0);
                    defaultHistory.setCurrentAmount(0);
                    defaultHistory.setCreatedAt(LocalDateTime.now());
                    return defaultHistory;
                });
    }

    @Override
    public Integer getCurrentAmountByUserId(Long userId) {
        return walletHistoryRepository.findCurrentAmountByUserId(userId);
    }

    @Override
    public void chargeWallet(Long userId, Integer amount) {
        Integer currentAmount = walletHistoryRepository.findCurrentAmountByUserId(userId);
        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setUserId(userId);
        walletHistory.setAddedAmount(amount);
        walletHistory.setCurrentAmount(currentAmount + amount);
        walletHistory.setCreatedAt(LocalDateTime.now());
        walletHistoryRepository.save(walletHistory);
    }

    @Override
    public void refundWallet(Long userId, Integer amount) {
        Integer currentAmount = walletHistoryRepository.findCurrentAmountByUserId(userId);
        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setUserId(userId);
        walletHistory.setUsedAmount(0);
        walletHistory.setAddedAmount(0);
        walletHistory.setRefundedAmount(amount);
        walletHistory.setCurrentAmount(currentAmount + amount);
        walletHistory.setCreatedAt(LocalDateTime.now());
        walletHistoryRepository.save(walletHistory);
    }

    @Override
    public void deductFromWallet(Long userId, Integer amount) {
        Integer currentAmount = walletHistoryRepository.findCurrentAmountByUserId(userId);
        if (currentAmount >= amount) {
            WalletHistory walletHistory = new WalletHistory();
            walletHistory.setUserId(userId);
            walletHistory.setUsedAmount(amount);
            walletHistory.setRefundedAmount(0);
            walletHistory.setAddedAmount(0);
            walletHistory.setCurrentAmount(currentAmount - amount);
            walletHistory.setCreatedAt(LocalDateTime.now());
            walletHistoryRepository.save(walletHistory);
        } else {
            throw new IllegalArgumentException("금액이 충분하지 않습니다.");
        }
    }

    @Override
    public void rechargeWallet(Long userId, int amount) {
        WalletHistory latestHistory = getLatestWalletHistoryByUserId(userId);

        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setUserId(userId);
        walletHistory.setAddedAmount(amount);
        walletHistory.setRefundedAmount(0);
        walletHistory.setUsedAmount(0);
        walletHistory.setCurrentAmount(latestHistory.getCurrentAmount() + amount);
        walletHistory.setCreatedAt(LocalDateTime.now());

        walletHistoryRepository.save(walletHistory);
    }

    @Override
    public Integer currentAmountByUser(Long userId) {
        return walletHistoryRepository.findCurrentAmountByUserId(userId);
    }
}
