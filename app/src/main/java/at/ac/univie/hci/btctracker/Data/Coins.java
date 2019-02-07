package at.ac.univie.hci.btctracker.Data;
import android.widget.ImageView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import at.ac.univie.hci.btctracker.R;

/**
 * for expanded parent list euro
 *
 * Created by vanish on 11.05.18.
 */

public class Coins extends ExpandableGroup{
    public Coins(String toEuro, List<Courses> items) {
        super(toEuro, items);
    }
}

