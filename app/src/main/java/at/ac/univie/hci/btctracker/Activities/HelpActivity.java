package at.ac.univie.hci.btctracker.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import at.ac.univie.hci.btctracker.Manager.Manager;
import at.ac.univie.hci.btctracker.R;

/**
 * activity to show all available functions of app
 *
 * Created by vanish on 10.05.18.
 */

public class HelpActivity extends AppCompatActivity {
    private Manager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        manager = new Manager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
//                Intent myIntent = new Intent(HelpActivity.this, PortfolioActivity.class);
//                startActivity(myIntent);
//                return true;
            case R.id.home:
                this.finish();
                return true;
            case R.id.wiki:
                Intent wiki = new Intent(HelpActivity.this, WikiActivity.class);
                startActivity(wiki);
                return true;
            case R.id.telegram:
                intentTelegram(this);
                return true;
            case R.id.help:
                startActivity(new Intent(HelpActivity.this, HelpActivity.class));
                return true;
            case R.id.about:
                startActivity(new Intent(HelpActivity.this, PopAboutActivity.class));
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




