package at.ac.univie.hci.btctracker.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import at.ac.univie.hci.btctracker.Data.Transaction;
import at.ac.univie.hci.btctracker.Manager.Manager;
import at.ac.univie.hci.btctracker.R;

public class PortfolioActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    TextView profitView;
    ArrayList<Transaction> listTransaction;

    private ImageButton addBtn;

    private Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);


        manager = new Manager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listTransaction = manager.getTransactionDAO().getTransactionList();
        /**
         *  Summe in fiat money from Amount of Cryptocurrency
         */

        
        String profit = getIntent().getStringExtra("profit");
        profitView = (TextView) findViewById(R.id.profitView);
        if (profit!=null)
            profitView.setText(profit + " €");
        else profitView.setText("0 €");

        profitView.setOnClickListener(new View.OnClickListener() {
            boolean flag;
            @Override public void onClick(View v) {
                if (!flag) {
                    if (getIntent().getStringExtra("profit")==null)
                        profitView.setText("0 $");
                    else profitView.setText(manager.roundDouble(Double.parseDouble(getIntent().getStringExtra("profit"))*1.2,2 ) + " $");
                    flag = true;
                } else {
                    if (getIntent().getStringExtra("profit")==null)
                        profitView.setText("0 €");
                    else profitView.setText(manager.roundDouble(Double.parseDouble(getIntent().getStringExtra("profit")), 2) + " €");
                    flag = false;
                }
            }
        });


        if (listTransaction.size() == 0) {
            TextView textView = (TextView) findViewById(R.id.textView3);
            textView.setText("No Transactions yet");
        }
        adapterPortfolio adapter = new adapterPortfolio(this, listTransaction);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setTitle("Portfolio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /**
         * Add Transaktion Button
         */
        addBtn = findViewById(R.id.addId);
        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PortfolioActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });


    }


    /**
     * Creating a menu
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
            case R.id.home:
                //Intent home = new Intent(PortfolioActivity.this, TrackerActivity.class);
                //startActivity(home);
                this.finish();
                return true;
            case R.id.wiki:
                Intent wiki = new Intent(PortfolioActivity.this, WikiActivity.class);
                startActivity(wiki);
                return true;
            case R.id.telegram:
                intentTelegram(this);
                return true;
            case R.id.help:
                startActivity(new Intent(PortfolioActivity.this, HelpActivity.class));
                return true;
            case R.id.about:
                startActivity(new Intent(PortfolioActivity.this, PopAboutActivity.class));
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
    void intentTelegram(Context context) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://t.me/www_Bitcoin_com"));
        final String appName = "org.telegram.messenger";
        try {
            if (manager.isAppAvailable(this.getApplicationContext(), appName)) {
                i.setPackage(appName);
                this.startActivity(i);
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error. Please read log info", Toast.LENGTH_LONG).show();
        }
    }


}
