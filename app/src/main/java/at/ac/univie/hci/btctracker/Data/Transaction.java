package at.ac.univie.hci.btctracker.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rabizo on 23.04.2018.
 */

public class  Transaction implements Serializable{

    private int transactionId;
    private String currencyId;
    private Double priceInBtc;
    private Double priceInUsd;
    private Double priceInEur;
    private int image;
    private String date;
    private Double amountOfTransaction;
    private String buy; //if transaction is sell than buy = false;


    public Transaction(int transactionId, String currencyId, Double priceInBtc, Double priceInUsd, Double priceInEur,
                       Double amountOfTransaction, String buy, int image, String date) {
        this.transactionId = transactionId;
        this.currencyId = currencyId;
        this.priceInBtc = priceInBtc;
        this.priceInUsd = priceInUsd;
        this.priceInEur = priceInEur;
        this.amountOfTransaction = amountOfTransaction;
        this.buy = buy;
        this.image = image;
        this.date = date;

    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Double getPriceInBtc() {
        return priceInBtc;
    }

    public void setPriceInBtc(Double priceInBtc) {
        this.priceInBtc = priceInBtc;
    }

    public Double getPriceInUsd() {
        return priceInUsd;
    }

    public void setPriceInUsd(Double priceInUsd) {
        this.priceInUsd = priceInUsd;
    }

    public Double getPriceInEur() {
        return priceInEur;
    }

    public void setPriceInEur(Double priceInEur) {
        this.priceInEur = priceInEur;
    }

    public Double getAmountOfTransaction() {
        return amountOfTransaction;
    }

    public void setAmountOfTransaction(Double amountOfTransaction) {
        this.amountOfTransaction = amountOfTransaction;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        //Date date1=df.format(date);
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + this.transactionId +
                ", currencyId='" + this.currencyId + '\'' +
                ", priceInBtc=" + this.priceInBtc +
                ", priceInUsd=" + this.priceInUsd +
                ", priceInEur=" + this.priceInEur +
                ", amountOfTransaction=" + this.amountOfTransaction +
                ", buy=" + this.buy +
                '}';
    }
}
