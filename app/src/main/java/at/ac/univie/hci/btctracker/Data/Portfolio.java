package at.ac.univie.hci.btctracker.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rabizo on 23.04.2018.
 */

public class Portfolio implements Serializable{

    private Double profitInUsd;
    private Double profitInEur;
    private Double profitInBtc;

    private ArrayList<Transaction> transactionsList;

    public Double getProfitInUsd() {
        return profitInUsd;
    }

    public void setProfitInUsd(Double profitInUsd) {
        this.profitInUsd = profitInUsd;
    }

    public Double getProfitInEur() {
        return profitInEur;
    }

    public void setProfitInEur(Double profitInEur) {
        this.profitInEur = profitInEur;
    }

    public Double getProfitInBtc() {
        return profitInBtc;
    }

    public void setProfitInBtc(Double profitInBtc) {
        this.profitInBtc = profitInBtc;
    }

    public ArrayList<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(ArrayList<Transaction> transactionsList) {

        this.transactionsList = transactionsList;
    }




}

