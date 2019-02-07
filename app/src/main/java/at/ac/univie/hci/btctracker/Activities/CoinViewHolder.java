package at.ac.univie.hci.btctracker.Activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import at.ac.univie.hci.btctracker.R;

/**
 * view holder for parent expanded list in euro
 *
 * Created by vanish on 11.05.18.
 */

public class CoinViewHolder extends GroupViewHolder {
    private ImageView coin_icon;
    private TextView toEuro;

    public CoinViewHolder(View itemView) {
        super(itemView);
        toEuro = itemView.findViewById(R.id.coin_names);
        coin_icon = itemView.findViewById(R.id.coin_icon);
    }

    public void setCoinName(ExpandableGroup group){
        toEuro.setText(group.getTitle());
    }

    public void setCoinIcon(ExpandableGroup group){
        switch (group.getTitle().substring(0, 3)) {
            case "BTC":coin_icon.setImageResource(R.drawable.btc);break;
            case "ETH":coin_icon.setImageResource(R.drawable.eth);break;
            case "XRP":coin_icon.setImageResource(R.drawable.xrp);break;
            case "EOS":coin_icon.setImageResource(R.drawable.eos);break;
            case "TRX":coin_icon.setImageResource(R.drawable.trx);break;
            case "NEO":coin_icon.setImageResource(R.drawable.neo);break;
            case "LTC":coin_icon.setImageResource(R.drawable.ltc);break;
            case "ETC":coin_icon.setImageResource(R.drawable.etc);break;
            case "BCH":coin_icon.setImageResource(R.drawable.bch);break;
            case "BNB":coin_icon.setImageResource(R.drawable.bnb);break;
        }

    }
}