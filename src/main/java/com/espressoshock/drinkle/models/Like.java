package com.espressoshock.drinkle.models;

import java.sql.Timestamp;

public class Like {
    private int likeId;
    private Timestamp ts;
    private Account account;


    public Like(int like_id, Account account) {
        this.likeId = like_id;
        this.account = account;
        ts = new Timestamp(System.currentTimeMillis());

    }

    public Like(int like_id, Timestamp ts, Account account) {
        this.likeId = like_id;
        this.ts = ts;
        this.account = account;
    }

    public Timestamp getTimestamp() {
        return ts;
    }

    public Account getAccount() {
        return account;
    }

}
