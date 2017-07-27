package com.monitise.alkin.model;

public class TransferRequest {
    private String fromIBAN;
    private String toIBAN;
    private String amount;

    public TransferRequest(String fromIBAN, String toIBAN, String amount) {
        this.fromIBAN = fromIBAN;
        this.toIBAN = toIBAN;
        this.amount = amount;
    }

    public TransferRequest() {

    }

    public String getFromIBAN() {
        return fromIBAN;
    }

    public String getToIBAN() {
        return toIBAN;
    }

    public String getAmount() {
        return amount;
    }

    public void setFromIBAN(String fromIBAN) {
        this.fromIBAN = fromIBAN;
    }

    public void setToIBAN(String toIBAN) {
        this.toIBAN = toIBAN;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
