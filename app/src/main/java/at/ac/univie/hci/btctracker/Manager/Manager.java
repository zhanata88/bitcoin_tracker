package at.ac.univie.hci.btctracker.Manager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.ac.univie.hci.btctracker.Data.Currency;
import at.ac.univie.hci.btctracker.Data.DAO.SerializedTransactionDAO;
import at.ac.univie.hci.btctracker.Data.DAO.TransactionDAO;
import at.ac.univie.hci.btctracker.Data.Portfolio;
import at.ac.univie.hci.btctracker.Data.Transaction;

/**
 * Created by rabizo on 24.04.18.
 */

public class Manager {

    private TransactionDAO transactionDAO;


    public Manager(Context context){
        this.transactionDAO = new SerializedTransactionDAO(context);
    }

    /**
     * Generates new id for a Transaction, so that user does not have to give an id to each transaction
     * @return
     */
    public int generateTransactionId(){
        if (getTransactionDAO().getTransactionList().size() == 0){
            return 1;
        }
        else {
            return getTransactionDAO().getTransactionList().get(getTransactionDAO().getTransactionList().size()-1).getTransactionId() + 1;
        }
    }

    /**
     * Adds a transaction to a List as well as to a File
     * @param currencyId
     * @param priceInBtc
     * @param priceInUsd
     * @param priceInEur
     * @param amountOfTransaction
     * @param buy
     */
    public void addTransaction(String currencyId, Double priceInBtc, Double priceInUsd,
                               Double priceInEur, Double amountOfTransaction, String buy, int image, String date) {
        Transaction transaction = new Transaction(generateTransactionId(), currencyId, priceInBtc, priceInUsd, priceInEur,
                amountOfTransaction, buy, image, date);
       getTransactionDAO().addTransaction(transaction);
    }

    /**
     * Deletes transaction from List
     * @param transactionId
     */
    public void deleteTransaction(int transactionId){
        getTransactionDAO().deleteTransaction(transactionId);

    }

    /**
     * Deletes all transactions from List
     */
    public void deleteAllTransactions(){
        getTransactionDAO().deleteAllTransactions();
    }


    public TransactionDAO getTransactionDAO(){
        return this.transactionDAO;
    }

    public void setTransactionDAO(TransactionDAO transactionDAO){
        this.transactionDAO = transactionDAO;
    }

    public ArrayList<Transaction> TransactionList() {

        return transactionDAO.getTransactionList();
    }


    /**
     * Function for testing, if all transactions are saved
     * @return
     */
    public String SysOutPrintAllTransactions (){
        String tr = "";
        for (Transaction t: transactionDAO.getTransactionList()){
            tr+=t.toString() + " ";
        }
        return tr;
    }

    /**
     * Parses JSON to an Array of currencies
     * @param response
     * @return List of Currencies
     */

    double btc = 0;
    public ArrayList<Currency> getArrayListFromJson(JSONObject response){
        Currency btc = new Currency();
        Currency eth = new Currency();
        Currency xrp = new Currency();
        Currency eos = new Currency();
        Currency trx = new Currency();
        Currency neo = new Currency();
        Currency ltc = new Currency();
        Currency etc = new Currency();
        Currency bch = new Currency();
        Currency bnb = new Currency();
        ArrayList<Currency> currencyArrayList = new ArrayList<Currency>();

        //Parsing
        try {
            JSONObject btcObj = response.getJSONObject("BTC");
            btc.setId("BTC");
            btc.setPriseInUsd(btcObj.getDouble("USD"));
            btc.setPriseInEur(btcObj.getDouble("EUR"));
            btc.setPriceInBitcoin(btcObj.getDouble("BTC"));
            currencyArrayList.add(btc);

            JSONObject ethObj = response.getJSONObject("ETH");
            eth.setId("ETH");
            eth.setPriseInUsd(ethObj.getDouble("USD"));
            eth.setPriseInEur(ethObj.getDouble("EUR"));
            eth.setPriceInBitcoin(ethObj.getDouble("BTC"));
            currencyArrayList.add(eth);

            JSONObject xrpObj = response.getJSONObject("XRP");
            xrp.setId("XRP");
            xrp.setPriseInUsd(xrpObj.getDouble("USD"));
            xrp.setPriseInEur(xrpObj.getDouble("EUR"));
            xrp.setPriceInBitcoin(xrpObj.getDouble("BTC"));
            currencyArrayList.add(xrp);

            JSONObject eosObj = response.getJSONObject("EOS");
            eos.setId("EOS");
            eos.setPriseInUsd(eosObj.getDouble("USD"));
            eos.setPriseInEur(eosObj.getDouble("EUR"));
            eos.setPriceInBitcoin(eosObj.getDouble("BTC"));
            currencyArrayList.add(eos);

            JSONObject trxObj = response.getJSONObject("TRX");
            trx.setId("TRX");
            trx.setPriseInUsd(trxObj.getDouble("USD"));
            trx.setPriseInEur(trxObj.getDouble("EUR"));
            trx.setPriceInBitcoin(trxObj.getDouble("BTC"));
            currencyArrayList.add(trx);

            JSONObject neoObj = response.getJSONObject("NEO");
            neo.setId("NEO");
            neo.setPriseInUsd(neoObj.getDouble("USD"));
            neo.setPriseInEur(neoObj.getDouble("EUR"));
            neo.setPriceInBitcoin(neoObj.getDouble("BTC"));
            currencyArrayList.add(neo);

            JSONObject ltcObj = response.getJSONObject("LTC");
            ltc.setId("LTC");
            ltc.setPriseInUsd(ltcObj.getDouble("USD"));
            ltc.setPriseInEur(ltcObj.getDouble("EUR"));
            ltc.setPriceInBitcoin(ltcObj.getDouble("BTC"));
            currencyArrayList.add(ltc);

            JSONObject etcObj = response.getJSONObject("ETC");
            etc.setId("ETC");
            etc.setPriseInUsd(etcObj.getDouble("USD"));
            etc.setPriseInEur(etcObj.getDouble("EUR"));
            etc.setPriceInBitcoin(etcObj.getDouble("BTC"));
            currencyArrayList.add(etc);

            JSONObject bchObj = response.getJSONObject("BCH");
            bch.setId("BCH");
            bch.setPriseInUsd(bchObj.getDouble("USD"));
            bch.setPriseInEur(bchObj.getDouble("EUR"));
            bch.setPriceInBitcoin(bchObj.getDouble("BTC"));
            currencyArrayList.add(bch);

            JSONObject bnbObj = response.getJSONObject("BNB");
            bnb.setId("BNB");
            bnb.setPriseInUsd(bnbObj.getDouble("USD"));
            bnb.setPriseInEur(bnbObj.getDouble("EUR"));
            bnb.setPriceInBitcoin(bnbObj.getDouble("BTC"));
            currencyArrayList.add(bnb);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currencyArrayList;
    }

    /**
     * Summs the Profit EUR from all Transactions
     * @return Profit
     */
    ArrayList<Double> coins = new ArrayList<>();


    public Double getProfitFromAllTransactions(ArrayList<Currency> currencyArrayList, String currencyType) {
        Double profit = 0.0;
        double amountBTC = 0;
        double amountEOS = 0;
        double amountETH = 0;
        double amountLTC = 0;
        double amountTRX = 0;
        double amountXRP = 0;
        double amountNEO = 0;
        double amountBNB = 0;
        double amountETC = 0;
        double amountBCH = 0;

        for (Transaction t: getTransactionDAO().getTransactionList()){
            if (t.getCurrencyId().equals("BTC") && t.getBuy().equals("Buy"))
                amountBTC += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("BTC") && t.getBuy().equals("Sell"))
                amountBTC -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("EOS") && t.getBuy().equals("Buy"))
                amountEOS += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("EOS") && t.getBuy().equals("Sell"))
                amountEOS -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("ETH") && t.getBuy().equals("Buy"))
                amountETH += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("ETH") && t.getBuy().equals("Sell"))
                amountETH -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("LTC") && t.getBuy().equals("Buy"))
                amountLTC += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("LTC") && t.getBuy().equals("Sell"))
                amountLTC -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("LTC") && t.getBuy().equals("Buy"))
                amountLTC += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("LTC") && t.getBuy().equals("Sell"))
                amountLTC -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("TRX") && t.getBuy().equals("Buy"))
                amountTRX += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("TRX") && t.getBuy().equals("Sell"))
                amountTRX -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("XRP") && t.getBuy().equals("Buy"))
                amountXRP += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("XRP") && t.getBuy().equals("Sell"))
                amountXRP -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("NEO") && t.getBuy().equals("Buy"))
                amountNEO += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("NEO") && t.getBuy().equals("Sell"))
                amountNEO -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("BNB") && t.getBuy().equals("Buy"))
                amountBNB += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("BNB") && t.getBuy().equals("Sell"))
                amountBNB -= t.getAmountOfTransaction();

            else if (t.getCurrencyId().equals("BCH") && t.getBuy().equals("Buy"))
                amountBCH += t.getAmountOfTransaction();
            else if (t.getCurrencyId().equals("BCH") && t.getBuy().equals("Sell"))
                amountBCH -= t.getAmountOfTransaction();

        }
        coins.add(amountBTC);
        coins.add(amountETH);
        coins.add(amountXRP);
        coins.add(amountEOS);
        coins.add(amountTRX);
        coins.add(amountNEO);
        coins.add(amountLTC);
        coins.add(amountETC);
        coins.add(amountBCH);
        coins.add(amountBNB);

        for (int i = 0; i < currencyArrayList.size(); i++) {
            if (currencyType.equals("USD"))
                profit += coins.get(i) * currencyArrayList.get(i).getPriseInUsd();
            else
                profit += coins.get(i) * currencyArrayList.get(i).getPriseInEur();


        }
        return profit;
        //profit=amountLTC*1;
        //rounds result to 2 points

    }

    /**
     * Summs the Profit USD from all Transactions
     * @return Profit
     */


    int countUSD=0;
    public Double getProfitFromAllTransactionsUSD(){
        Double profit = 0.0;

        for (Transaction t: getTransactionDAO().getTransactionList()){
            profit += t.getPriceInUsd();
            countUSD++;
        }

        //rounds result to 2 points
        return roundDouble(profit, 2);
    }


    /**
     * Function for rounding of double values
     * @param value
     * @param places
     * @return rounded double
     */
    public static double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * returns a String array with information, needed in Layout in the ListView
     * @param currencyArrayList
     * @return String array
     */
    public String[] getPrintList(ArrayList<Currency> currencyArrayList){
        try {
            String [] list = {
                currencyArrayList.get(0).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(0).getPriseInUsd()
                        + "  EUR " + currencyArrayList.get(0).getPriseInEur() + "  BTC: " + currencyArrayList.get(0).getPriceInBitcoin(),
                    currencyArrayList.get(1).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(1).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(1).getPriseInEur() + "  BTC: " + currencyArrayList.get(1).getPriceInBitcoin(),
                    currencyArrayList.get(2).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(2).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(2).getPriseInEur() + "  BTC: " + currencyArrayList.get(2).getPriceInBitcoin(),
                    currencyArrayList.get(3).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(3).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(3).getPriseInEur() + "  BTC: " + currencyArrayList.get(3).getPriceInBitcoin(),
                    currencyArrayList.get(4).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(4).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(4).getPriseInEur() + "  BTC: " + currencyArrayList.get(4).getPriceInBitcoin(),
                    currencyArrayList.get(5).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(5).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(5).getPriseInEur() + "  BTC: " + currencyArrayList.get(5).getPriceInBitcoin(),
                    currencyArrayList.get(6).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(6).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(6).getPriseInEur() + "  BTC: " + currencyArrayList.get(6).getPriceInBitcoin(),
                    currencyArrayList.get(7).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(7).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(7).getPriseInEur() + "  BTC: " + currencyArrayList.get(7).getPriceInBitcoin(),
                    currencyArrayList.get(8).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(8).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(8).getPriseInEur() + "  BTC: " + currencyArrayList.get(8).getPriceInBitcoin(),
                    currencyArrayList.get(9).getId().toUpperCase() + " : " + " USD " + currencyArrayList.get(9).getPriseInUsd()
                            + "  EUR " + currencyArrayList.get(9).getPriseInEur() + "  BTC: " + currencyArrayList.get(9).getPriceInBitcoin()
            };

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * to genrate test data for report popup
     *
     * @return
     */
    public Set<String> getLastWeekCoins(){
        // we suppose to check if the coin changed the course everyday +10%
        // stable course changing is a good tip for users
        // here we have no api, so just some random list
        List<String> coins =
                Arrays.asList("Bitcoin", "Ripple", "Ethereum",
                        "Bitcoin Cash", "EOS", "Litecoin",
                        "Cardano", "Stellar", "IOTA", "TRON");
        Set<String> lastWeekCoins = new HashSet<>();
        int random = getRandomInteger(3,0);
        for (int i= 0; i < random; i++) {
            lastWeekCoins.add(coins.get(getRandomInteger(9,0)));
        }
        return lastWeekCoins;
    }

    /**
     * random number generator
     *
     * @param maximum
     * @param minimum
     * @return
     */
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

    /**
     *
     * @param context
     * @param appName
     * @return
     */
    public static boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "Telegram is not installed", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
