package com.monitise.alkin.model;

public class TransferResponse extends BaseResponse{
    private long newBalance;

    public long getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(long newBalance) {
        this.newBalance = newBalance;
    }
}
