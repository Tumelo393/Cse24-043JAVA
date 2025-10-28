package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Initializes SQLite DB and inserts sample dataset (10 records) if not present.
 */
public class Database {
    private static final String URL = "jdbc:sqlite:banking.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL);
    }

    public static void init() {
        try(Connection conn = getConnection(); Statement st = conn.createStatement()){
            st.execute("CREATE TABLE IF NOT EXISTS customers (id TEXT PRIMARY KEY, firstName TEXT, lastName TEXT, address TEXT, phone TEXT, isEmployed INTEGER, employer TEXT)");
            st.execute("CREATE TABLE IF NOT EXISTS accounts (accountNumber TEXT PRIMARY KEY, customerId TEXT, type TEXT, balance REAL, branch TEXT, employer TEXT)");
            st.execute("CREATE TABLE IF NOT EXISTS transactions (id TEXT PRIMARY KEY, accountNumber TEXT, timestamp TEXT, amount REAL, type TEXT)");
            
         
            
            
            // Check if sample data exists (count customers)
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) as cnt FROM customers");
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int cnt = rs.getInt("cnt");
                    if (cnt == 0) {
                        insertSampleData(conn);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertSampleData(Connection conn) {
        try {
            conn.setAutoCommit(false);
            
            
            // Insert 5 customers
            String ic = "INSERT INTO customers(id, firstName, lastName, address, phone, isEmployed, employer) VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(ic)) {
                ps.setString(1, "001"); ps.setString(2, "Tumelo"); ps.setString(3, "Mothusi");
                ps.setString(4, "Tlokweng"); ps.setString(5, "76498128"); ps.setInt(6, 1); ps.setString(7, "Mothusi Pty Ltd"); ps.executeUpdate();

                ps.setString(1, "002"); ps.setString(2, "Phomolo"); ps.setString(3, "Mothusi");
                ps.setString(4, "Palapye"); ps.setString(5, "72358074"); ps.setInt(6, 0); ps.setString(7, "Mothusi Pty Ltd"); ps.executeUpdate();

                ps.setString(1, "003"); ps.setString(2, "Teko"); ps.setString(3, "Ramasu");
                ps.setString(4, "Serowe"); ps.setString(5, "78906543"); ps.setInt(6, 1); ps.setString(7, "Ramasu Inc"); ps.executeUpdate();

                ps.setString(1, "004"); ps.setString(2, "Percy"); ps.setString(3, "Motungerwa");
                ps.setString(4, "Village"); ps.setString(5, "76893412"); ps.setInt(6, 0); ps.setString(7, ""); ps.executeUpdate();

                ps.setString(1, "005"); ps.setString(2, "Kingsley"); ps.setString(3, "Mothologi");
                ps.setString(4, "Block 6"); ps.setString(5, "75092385"); ps.setInt(6, 1); ps.setString(7, "Mothologi Records"); ps.executeUpdate();
            }

            // Insert accounts for those customers (total 10 accounts)
            String ia = "INSERT INTO accounts(accountNumber, customerId, type, balance, branch, employer) VALUES(?,?,?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(ia)) {
                ps.setString(1, "ACC1001"); ps.setString(2, "001"); ps.setString(3, "CHEQUE"); ps.setDouble(4, 1200.00); ps.setString(5, "Gaborone"); ps.setString(6, "Mothusi Pty Ltd"); ps.executeUpdate();
                ps.setString(1, "ACC1002"); ps.setString(2, "001"); ps.setString(3, "SAVINGS"); ps.setDouble(4, 500.00); ps.setString(5, "Gaborone"); ps.setString(6, ""); ps.executeUpdate();

                ps.setString(1, "ACC2001"); ps.setString(2, "002"); ps.setString(3, "SAVINGS"); ps.setDouble(4, 150.00); ps.setString(5, "Francistown"); ps.setString(6, "Mothusi Pty Ltd"); ps.executeUpdate();
                ps.setString(1, "ACC2002"); ps.setString(2, "002"); ps.setString(3, "INVESTMENT"); ps.setDouble(4, 600.00); ps.setString(5, "Francistown"); ps.setString(6, ""); ps.executeUpdate();

                ps.setString(1, "ACC3001"); ps.setString(2, "003"); ps.setString(3, "CHEQUE"); ps.setDouble(4, 2500.00); ps.setString(5, "Gaborone"); ps.setString(6, "Ramasu Inc"); ps.executeUpdate();
                ps.setString(1, "ACC3002"); ps.setString(2, "003"); ps.setString(3, "INVESTMENT"); ps.setDouble(4, 5000.00); ps.setString(5, "Gaborone"); ps.setString(6, ""); ps.executeUpdate();

                ps.setString(1, "ACC4001"); ps.setString(2, "004"); ps.setString(3, "SAVINGS"); ps.setDouble(4, 75.00); ps.setString(5, "Molepolole"); ps.setString(6, ""); ps.executeUpdate();
                ps.setString(1, "ACC4002"); ps.setString(2, "004"); ps.setString(3, "SAVINGS"); ps.setDouble(4, 40.00); ps.setString(5, "Molepolole"); ps.setString(6, ""); ps.executeUpdate();

                ps.setString(1, "ACC5001"); ps.setString(2, "005"); ps.setString(3, "CHEQUE"); ps.setDouble(4, 800.00); ps.setString(5, "Lobatse"); ps.setString(6, "Mothologi Records"); ps.executeUpdate();
                ps.setString(1, "ACC5002"); ps.setString(2, "005"); ps.setString(3, "INVESTMENT"); ps.setDouble(4, 2000.00); ps.setString(5, "Lobatse"); ps.setString(6, ""); ps.executeUpdate();
            }

            // No initial transactions for simplicity
            conn.commit();
            conn.setAutoCommit(true);
        } catch(Exception ex) {
            try { conn.rollback(); } catch(Exception e2) {}
            ex.printStackTrace();
        }
    }
}
