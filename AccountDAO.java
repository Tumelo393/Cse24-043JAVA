package dao;

import model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDAO {
    public static void saveTransaction(String accountNumber, Transaction t) throws Exception {
        try(Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO transactions(id, accountNumber, timestamp, amount, type) VALUES(?,?,?,?,?)";
            try(PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, t.getId());
                ps.setString(2, accountNumber);
                ps.setString(3, t.getTimestamp().toString());
                ps.setDouble(4, t.getAmount());
                ps.setString(5, t.getType());
                ps.executeUpdate();
            }
        }
    }
}
