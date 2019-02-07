package at.ac.univie.hci.btctracker.Data.DAO;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import at.ac.univie.hci.btctracker.Data.Transaction;

/**
 * Created by rabizo on 23.04.2018.
 */

public class SerializedTransactionDAO extends Thread implements TransactionDAO {

    private String fileName;
    private ArrayList<Transaction> transactionArrayList;
    private Context context;
    private File file;






    public SerializedTransactionDAO(Context context){
        //this.fileName = "transactions";
        this.transactionArrayList = new ArrayList<Transaction>();
        this.context = context;
        this.file = new File(context.getFilesDir().getPath() + "transaction.txt");
    }

    public void setTransactionArrayList(ArrayList<Transaction> transactionArrayList) {
        this.transactionArrayList = transactionArrayList;
    }
    @Override
    public ArrayList<Transaction> getTransactionList() {
        deserializeTransactions();
        return this.transactionArrayList;
    }

    @Override
    public Transaction getTransactionById(int id) {

        for (Transaction t: getTransactionList()){
            if (id == t.getTransactionId()){
                return t;
            }
        }
        return null;
    }

    @Override
    public void addTransaction(Transaction transaction) {

            this.getTransactionList().add(transaction);
            serializeTransactions();

    }

    @Override
    public void deleteAllTransactions() {
        setTransactionArrayList(new ArrayList<Transaction>());
        serializeTransactions();
    }

    @Override
    public void deleteTransaction(int id) {
        if (getTransactionById(id)==null) try {
            throw new Exception("Transaction not found exception");
        } catch (Exception e) {

            e.printStackTrace();
        }
        Iterator itr = getTransactionList().iterator();

        while (itr.hasNext()) {
            Transaction t = (Transaction) itr.next();
            if (t.getTransactionId() == id) {
                itr.remove();
                break;
            }
        }


        serializeTransactions();
        System.out.println("Transaction with id " + id + " removed!");
    }


    public void serializeTransactions(){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(transactionArrayList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeTransactions(){
        ArrayList<Transaction> createResumeForm = null;
        try {
            FileInputStream fileInputStream = context.openFileInput(file.getName());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            createResumeForm = (ArrayList<Transaction>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            setTransactionArrayList(createResumeForm);

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
