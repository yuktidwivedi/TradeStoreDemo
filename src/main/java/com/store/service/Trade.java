package com.store.service;
import java.time.LocalDate;

public class Trade {
    private String tradeId;
    private int version;
    private String counterPartyId;
    private String bookId;
    private LocalDate maturityDate;
    private LocalDate createdDate;
    private boolean expired;

    // Constructors, getters, setters, and other methods can be added here

    // Example constructor (you can customize as needed)
    public Trade(String tradeId, int version, String counterPartyId, String bookId,
                 LocalDate maturityDate, LocalDate createdDate, boolean expired) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.expired = expired;
    }

    public Trade(String t1, int version, String cpId, LocalDate localDate) {
        this.tradeId = t1;
        this.version = version;
        this.counterPartyId = cpId;
        this.maturityDate = localDate;

    }

    // Getters and setters for each field (you can generate them automatically)

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCounterPartyId() {
        return counterPartyId;
    }

    public void setCounterPartyId(String counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isExpired() {
        updateExpireFlag();
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void updateExpireFlag() {
        if (LocalDate.now().isAfter(maturityDate)) {
            expired = true;
        }
    }
}
