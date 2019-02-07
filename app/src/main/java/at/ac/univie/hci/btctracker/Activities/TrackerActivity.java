package at.ac.univie.hci.btctracker.Activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import at.ac.univie.hci.btctracker.Data.Coins;
import at.ac.univie.hci.btctracker.Data.Courses;
import at.ac.univie.hci.btctracker.Data.Currency;
import at.ac.univie.hci.btctracker.Manager.Manager;

import at.ac.univie.hci.btctracker.R;

public class TrackerActivity extends AppCompatActivity {

    private String url;
    private TextView timeStamp;
    private TextView profitView;
    private Button updateBtn;
    private Button portfolio;

    Manager manager;

    private RecyclerView mRecyclerView ;
    private CoinAdapter coinAdapter;
    private List<Coins> coins;
    ArrayList<Currency> currenciesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //test data
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new Manager(this);


        /**
         manager.deleteAllTransactions();
         manager.addTransaction("BTC", 1.0, 809.0*1.1, 809.0, 1.0, "Buy", R.drawable.btc, "2018.01.12");
         manager.addTransaction("LTC", 1.0, 300.0*1.1, 300.0, 1.0, "Buy", R.drawable.eos, "2018.01.12");
         manager.addTransaction("BTC", 1.0, 100.0*1.1, 100.0, 1.0, "Buy", R.drawable.btc, "2018.01.12");
         manager.addTransaction("LTC", 1.0, 300.0*1.1, 300.0, 1.0, "Buy", R.drawable.eos, "2018.01.12");
         manager.addTransaction("BTC", 1.0, 100.0*1.1, 100.0, 1.0, "Sell", R.drawable.btc, "2018.01.12");
         */


        // show hole amount of money
        requestData();


        // create test data for report popup
        String coinListString = "";
        for (String one : manager.getLastWeekCoins()) {
            coinListString += one +"\n";
        }
        // if smth created show it in popup
        if (coinListString != "" ){
            Intent intent = new Intent(TrackerActivity.this, PopReportActivity.class);
            intent.putExtra("List", coinListString);
            startActivity(intent);
        }

        updateBtn = findViewById(R.id.updateBtn);
        // update courses on button click
        updateBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // get data from api
                requestData();
            }
        });

        portfolio = findViewById(R.id.portfolioBtn);
        // go to portfolio activity
        portfolio.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent port = new Intent(TrackerActivity.this, PortfolioActivity.class);
                Double profit = manager.roundDouble(manager.getProfitFromAllTransactions(currenciesArrayList, "EUR"), 2);
                port.putExtra("profit", profit.toString());
                startActivity(port);
            }
        });

        // show amount of money in euro or in usd

        profitView = (TextView) findViewById(R.id.profitView);
         profitView.setOnClickListener(new View.OnClickListener() {
            boolean flag;
            @Override public void onClick(View v) {
                if (!flag) {
                    profitView.setText(manager.roundDouble(manager.getProfitFromAllTransactions(currenciesArrayList, "EUR")*1.2, 2 ) + " $");
                    flag = true;
                } else {
                    profitView.setText(manager.roundDouble(manager.getProfitFromAllTransactions(currenciesArrayList, "EUR"),2) + " €");
                    flag = false;
                }
            }
         });

    }


    /**
     * Does API Request and sets ListView with result
     */
    public void requestData() {
       // url = "https://api.coinmarketcap.com/v1/ticker/?convert=EUR&limit=10";
        url = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH,XRP,EOS,TRX,LTC,NEO,BNB,ETC,BCH&tsyms=USD,EUR,BTC";
        //url = "https://api.cryptonator.com/api/ticker/btc-usd";
       // url = "http://api.openweathermap.org/data/2.5/forecast?id=2761369&appid=fd4853c4e77b261bf1b695927bf8054a&units=metric&mode=json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @TargetApi(Build.VERSION_CODES.O)
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("TAG: ", response.toString());
                        // getting list with all data from JSON
                        currenciesArrayList = manager.getArrayListFromJson(response);
                        //manager.getProfitFromAllTransactionsEUR(currenciesArrayList);
                        profitView = (TextView) findViewById(R.id.profitView);


                        profitView.setText(manager.roundDouble(manager.getProfitFromAllTransactions(currenciesArrayList, "EUR"), 2) + " €");
                        // Einstellung der TextGroesse in der List im Layout
                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                                (TrackerActivity.this, android.R.layout.simple_list_item_1, manager.getPrintList(currenciesArrayList)){
                            @Override
                            public View getView(int position, View convertView, ViewGroup parent){
                                /// Get the Item from ListView
                                View view = super.getView(position, convertView, parent);

                                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                                // Set the text size 25 dip for ListView each item
                                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

                                // Return the view
                                return view;
                            }
                        };

                        // create list of courses for expanded list recycleview
                        coins = new ArrayList<Coins>(10);
                        for (int i = 0; i < 10; i++) {
                            List<Courses> curList = new ArrayList<>(2);
                            curList.add(new Courses(
                                      currenciesArrayList.get(i).getId() + " "
                                    + Double.toString(currenciesArrayList.get(i).getPriseInUsd())+" $"));
                            curList.add(new Courses(
                                    currenciesArrayList.get(i).getId() + " "
                                    + Double.toString(currenciesArrayList.get(i).getPriceInBitcoin())+" BTC"));
                            coins.add(new Coins(

                                     currenciesArrayList.get(i).getId() + " "
                                    + Double.toString(currenciesArrayList.get(i).getPriseInEur())+" € ",curList));
                        }
                        // paste data in custom adapter
                        coinAdapter = new CoinAdapter(coins);
                        mRecyclerView = findViewById(R.id.recycler_view);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(TrackerActivity.this));
                        mRecyclerView.setAdapter(coinAdapter);

                        // show last update time
                        timeStamp = findViewById(R.id.timeStamp);
                        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");
                        Date now = Calendar.getInstance(TimeZone.getDefault()).getTime();
                        String strDate = sdfDate.format(now);
                        timeStamp.setText(strDate);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("NOT WORKING " + url);
                error.getMessage();
            }
        });


        requestQueue.add(jsonObjReq);
    }

    /**
     * Creating menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    /**
     * Open a new Activity on Menu Click
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.transactionMenu:
//                Intent myIntent = new Intent(TrackerActivity.this, PortfolioActivity.class);
//                Double profit = manager.roundDouble(manager.getProfitFromAllTransactions(currenciesArrayList, "EUR"), 2);
//                myIntent.putExtra("profit", profit.toString());
//                startActivity(myIntent);
//                return true;
            case R.id.wiki:
                Intent wiki = new Intent(TrackerActivity.this, WikiActivity.class);
                startActivity(wiki);
                return true;
            case R.id.telegram:
                intentTelegram(this);
                return true;
            case R.id.help:
                startActivity(new Intent(TrackerActivity.this, HelpActivity.class));
                return true;
            case R.id.about:
                startActivity(new Intent(TrackerActivity.this, PopAboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * open telegram channel if it is installed
     *
     * @param context
     */
    void intentTelegram(Context context)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://t.me/www_Bitcoin_com"));
        final String appName = "org.telegram.messenger";
        try {
            if (manager.isAppAvailable(this.getApplicationContext(), appName)){
                i.setPackage(appName);
                this.startActivity(i);
            }
        } catch (Exception e) {
            Toast.makeText(context,"Error. Please read log info", Toast.LENGTH_LONG).show();
        }
    }

}




