package com.monitise.alkin.model;

public class TransferRequest {
    private String fromIBAN;
    private String toIBAN;
    private String amount;

    public String getFromIBAN() {
        return fromIBAN;
    }

    public String getToIBAN() {
        return toIBAN;
    }

    public String getAmount() {
        return amount;
    }
}
