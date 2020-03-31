package be.pxl.auctions.model;

import java.time.LocalDate;

public class Bid {
    private long id;
    private double amount;
    private LocalDate date;
    private Auction auction;
    private User user;

    public Bid() {
    }

    public Bid(User user, LocalDate date, double amount) {
        this.user = user;
        this.date = date;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
