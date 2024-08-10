package org.chulgang.hrd.wallethistory.model.service;

import org.chulgang.hrd.wallethistory.domain.WalletHistory;

import java.util.Optional;

public interface WalletHistoryService {

    WalletHistory getLatestWalletHistoryByUserId(Long userId);

    Integer getCurrentAmountByUserId(Long userId);

    void chargeWallet(Long userId, Integer amount);

    void refundWallet(Long userId, Integer amount);

    void deductFromWallet(Long userId, Integer amount);

    void rechargeWallet(Long userId, int amount);

    Integer currentAmountByUser(Long userId);
}
