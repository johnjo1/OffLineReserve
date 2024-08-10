package org.chulgang.hrd.wallethistory.model.repository;

import org.chulgang.hrd.wallethistory.domain.WalletHistory;

import java.util.Optional;

public interface WalletHistoryRepository {
    Optional<WalletHistory> findLatestWalletHistoryByUserId(Long userId);

    Integer findCurrentAmountByUserId(Long userId);

    void save(WalletHistory walletHistory);

}
