package at.ac.univie.hci.btctracker.Data.DAO;

import java.util.ArrayList;

import at.ac.univie.hci.btctracker.Data.Transaction;

/**
 * Created by rabizo on 23.04.2018.
 */

public interface TransactionDAO {

    public ArrayList<Transaction> getTransactionList();

    public Transaction getTransactionById(int id);

    public void addTransaction(Transaction transaction);

    public void deleteAllTransactions();

    public void deleteTransaction(int id);
}
