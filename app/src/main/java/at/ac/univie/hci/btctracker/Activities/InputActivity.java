package at.ac.univie.hci.btctracker.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import at.ac.univie.hci.btctracker.Data.Currency;
import at.ac.univie.hci.btctracker.Data.Transaction;
import at.ac.univie.hci.btctracker.Manager.Manager;
import at.ac.univie.hci.btctracker.R;

public class InputActivity extends AppCompatActivity {


    private Manager manager;

    /**
     * Initialise variables from Activity Input
     */

    private Spinner spinnerCurrency;
    private Spinner spinnerBought;
    private EditText amount;
    private RadioButton buy;
    private RadioButton sell;
    private EditText exchange;
    private Button submit;
    private Button cancel;
    private ImageView calender;
    private EditText txtDate;

    private String action;
    private double amountOfTransaction;
    private String currencyId;
    private double priceInBtc;
    private Double priceInUsd;
    private Double priceInEur;
    private int mYear, mMonth, mDay;
    private String date;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = new Manager(this);

        amount = findViewById(R.id.price);
        buy = findViewById(R.id.radioBuy);
        sell = findViewById(R.id.radioSell);
        exchange = findViewById(R.id.exchange);

        spinnerCurrency = findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.crypto_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerCurrency.setAdapter(adapter);

        spinnerBought = findViewById(R.id.bought);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.bought_currency, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerBought.setAdapter(adapter2);
        final RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup);

        /**
         * Display Calender
         */
        txtDate = (EditText) findViewById(R.id.date);
        calender = findViewById(R.id.calender);
        calender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(InputActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        /**
         * Submit Transaktion Button
         */
        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /**
                 * Validate User Input
                 * and Add new Transaction
                 */
                if (amount.getText().toString().isEmpty() || Double.parseDouble(amount.getText().toString()) < 0.0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                    alertDialog.setTitle(" ");
                    alertDialog.setMessage("Invalid 'Amount' Input. Please try again");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                } else if (radiogroup.getCheckedRadioButtonId() == -1) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                    alertDialog.setTitle(" ");
                    alertDialog.setMessage("Please select one Option");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                } else if (exchange.getText().toString().isEmpty() || Double.parseDouble(exchange.getText().toString()) < 0.0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                    alertDialog.setTitle(" ");
                    alertDialog.setMessage("Invalid 'Price' Input. Please try again");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                } else {
                    /**
                     * Getting User Input
                     */
                    currencyId = String.valueOf(spinnerCurrency.getItemAtPosition(spinnerCurrency.getSelectedItemPosition()));

                    //price in btc or usd or eur
                    final String priceIn = spinnerBought.getItemAtPosition(spinnerBought.getSelectedItemPosition()).toString();


                    amountOfTransaction = Double.parseDouble(amount.getText().toString());

                    //try {
                    if (priceIn.equals("BTC")) {
                        priceInBtc = new Double(exchange.getText().toString());
                        priceInEur = 0.0;
                        priceInUsd = 0.0;
                    } else if (priceIn.equals("USD")) {
                        priceInBtc = 0.0;
                        priceInEur = 0.0;
                        priceInUsd = new Double(exchange.getText().toString());
                    } else {
                        priceInBtc = 0.0;
                        priceInEur = new Double(exchange.getText().toString());
                        priceInUsd = 0.0;
                    }
                    //} catch (NumberFormatException e) {
                    // your default value
                    //}
                    int selectedId = radiogroup.getCheckedRadioButtonId();


                    RadioButton radioButton = (RadioButton) findViewById(selectedId);

                    action = radioButton.getText().toString();

                    /**
                     * Image reference
                     */
                    Integer image;
                    if (currencyId.equals("BCH")) {
                        image = R.drawable.bch;
                    } else if (currencyId.equals("BNB")) {
                        image = R.drawable.bnb;
                    } else if (currencyId.equals("BTC")) {
                        image = R.drawable.btc;
                    } else if (currencyId.equals("EOS")) {
                        image = R.drawable.eos;
                    } else if (currencyId.equals("ETC")) {
                        image = R.drawable.etc;
                    } else if (currencyId.equals("ETH")) {
                        image = R.drawable.eth;
                    } else if (currencyId.equals("LTC")) {
                        image = R.drawable.ltc;
                    } else if (currencyId.equals("NEO")) {
                        image = R.drawable.neo;
                    } else if (currencyId.equals("TRX")) {
                        image = R.drawable.trx;
                    } else {
                        image = R.drawable.xrp;
                    }

                    date = txtDate.getText().toString();

                    if (action.equals("Sell")) {
                        getProfitFromAllTransactions();
                        if (currencyId.equals("BCH") && amountBCH == 0 || currencyId.equals("BCH") && amountOfTransaction > amountBNB) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("BNB") && amountBNB == 0 || currencyId.equals("BNB") && amountOfTransaction > amountBNB) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("BTC") && amountBTC == 0 || currencyId.equals("BTC") && amountOfTransaction > amountBTC) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("EOS") && amountEOS == 0 || currencyId.equals("EOS") && amountOfTransaction > amountEOS) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("ETC") && amountETC == 0 || currencyId.equals("ETC") && amountOfTransaction > amountETC) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("ETH") && amountETH == 0 || currencyId.equals("ETH") && amountOfTransaction > amountETH) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("LTC") && amountLTC == 0 || currencyId.equals("LTC") && amountOfTransaction > amountLTC) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("NEO") && amountNEO == 0 || currencyId.equals("NEO") && amountOfTransaction > amountNEO) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("TRX") && amountTRX == 0 || currencyId.equals("TRX") && amountOfTransaction > amountTRX) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (currencyId.equals("XRP") && amountXRP == 0 || currencyId.equals("XRP") && amountOfTransaction > amountXRP) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InputActivity.this);
                            alertDialog.setTitle(" ");
                            alertDialog.setMessage("You can not sell more or less than you own");
                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else {
                            manager.addTransaction(currencyId, priceInBtc, priceInUsd, priceInEur, amountOfTransaction, action, image, date);
                            //Intent intent = new Intent(InputActivity.this, PortfolioActivity.class);
                            Intent intent1 = new Intent(InputActivity.this, TrackerActivity.class);

                            // startActivity(intent);
                            startActivity(intent1);
                        }
                    } else {
                        manager.addTransaction(currencyId, priceInBtc, priceInUsd, priceInEur, amountOfTransaction, action, image, date);
                        //Intent intent = new Intent(InputActivity.this, PortfolioActivity.class);
                        Intent intent1 = new Intent(InputActivity.this, TrackerActivity.class);
                        // startActivity(intent);
                        startActivity(intent1);
                    }
                }
            }
        });

        /**
         * Cancel Button
         */
        cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, PortfolioActivity.class);
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
//            case R.id.transactionMenu:
//                Intent myIntent = new Intent(InputActivity.this, PortfolioActivity.class);
//                startActivity(myIntent);
//                return true;
            case R.id.home:
                this.finish();
                return true;
            case R.id.wiki:
                Intent wiki = new Intent(InputActivity.this, WikiActivity.class);
                startActivity(wiki);
                return true;
            case R.id.telegram:
                intentTelegram(this);
                return true;
            case R.id.help:
                startActivity(new Intent(InputActivity.this, HelpActivity.class));
                return true;
            case R.id.about:
                startActivity(new Intent(InputActivity.this, PopAboutActivity.class));
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

    public void getProfitFromAllTransactions() {


        for (Transaction t : manager.getTransactionDAO().getTransactionList()) {
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
    }

}


