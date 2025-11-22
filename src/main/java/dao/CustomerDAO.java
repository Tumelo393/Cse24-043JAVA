package dao;

import model.Customer;
import java.sql.*;
import java.util.*;

public class CustomerDAO {
    public static void save(Customer c) throws Exception {
        try(Connection conn = Database.getConnection()) {
            String sql = "INSERT OR REPLACE INTO customers(id, firstName, lastName, address, phone, isEmployed, employer) VALUES(?,?,?,?,?,?,?)";
            try(PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getId());
                ps.setString(2, c.getFirstName());
                ps.setString(3, c.getLastName());
                ps.setString(4, "");
                ps.setString(5, "");
                ps.setInt(6, c.isEmployed() ? 1 : 0);
                ps.setString(7, c.getEmployer());
                ps.executeUpdate();
            }
        }
    }

    public static Optional<Customer> findById(String id) throws Exception {
        try(Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM customers WHERE id = ?";
            try(PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()){
                        Customer c = new Customer(rs.getString("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("isEmployed")==1, rs.getString("employer"));
                        return Optional.of(c);
                    }
                }
            }
        }
        return Optional.empty();
    }
}
