package org.chulgang.hrd.wallethistory.model.repository;

import org.chulgang.hrd.util.DbConnection;
import org.chulgang.hrd.wallethistory.domain.WalletHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class WalletHistoryRepositoryImpl implements WalletHistoryRepository {
    private static final WalletHistoryRepositoryImpl INSTANCE = new WalletHistoryRepositoryImpl();

    public static WalletHistoryRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<WalletHistory> findLatestWalletHistoryByUserId(Long userId) {
        String selectLatestWalletSql = "SELECT * FROM WALLET_HISTORY WHERE USER_ID = ? ORDER BY CREATED_AT DESC FETCH FIRST ROW ONLY";
        Connection connection = DbConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectLatestWalletSql);
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                WalletHistory walletHistory = new WalletHistory();
                walletHistory.setId(rs.getLong("ID"));
                walletHistory.setUserId(rs.getLong("USER_ID"));
                walletHistory.setAddedAmount(rs.getInt("ADDED_AMOUNT"));
                walletHistory.setUsedAmount(rs.getInt("USED_AMOUNT"));
                walletHistory.setRefundedAmount(rs.getInt("REFUNDED_AMOUNT"));
                walletHistory.setCurrentAmount(rs.getInt("CURRENT_AMOUNT"));
                walletHistory.setCreatedAt(rs.getTimestamp("CREATED_AT").toLocalDateTime());
                return Optional.of(walletHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
        return Optional.empty();
    }

    @Override
    public Integer findCurrentAmountByUserId(Long userId) {
        String selectCurrentAmountSql = "SELECT CURRENT_AMOUNT FROM WALLET_HISTORY WHERE USER_ID = ? ORDER BY CREATED_AT DESC FETCH FIRST ROW ONLY";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectCurrentAmountSql);
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("CURRENT_AMOUNT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
        return 0;
    }

    @Override
    public void save(WalletHistory walletHistory) {
        String insertWalletSql = "INSERT INTO WALLET_HISTORY (ID, USER_ID, ADDED_AMOUNT, USED_AMOUNT, REFUNDED_AMOUNT, CURRENT_AMOUNT, CREATED_AT) VALUES (WALLET_HISTORY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, sysdate)";
        Connection connection = DbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(insertWalletSql);
            ps.setLong(1, walletHistory.getUserId());
            ps.setInt(2, walletHistory.getAddedAmount());
            ps.setInt(3, walletHistory.getUsedAmount());
            ps.setInt(4, walletHistory.getRefundedAmount());
            ps.setInt(5, walletHistory.getCurrentAmount());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
    }

}
