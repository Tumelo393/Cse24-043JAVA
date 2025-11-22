package model;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private LocalDateTime timestamp;
    private double amount;
    private String type;

    public Transaction(String id, double amount, String type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    // getters
    public String getId(){return id;}
    public LocalDateTime getTimestamp(){return timestamp;}
    public double getAmount(){return amount;}
    public String getType(){return type;}
}
