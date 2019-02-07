package at.ac.univie.hci.btctracker.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.Currency;
import java.util.List;

import at.ac.univie.hci.btctracker.Data.Courses;
import at.ac.univie.hci.btctracker.R;

/**
 * adapter to show expanded list of courses
 *
 * Created by vanish on 11.05.18.
 */

public class CoinAdapter extends ExpandableRecyclerViewAdapter<CoinViewHolder, CourseViewHolder> {
//    private Context mCtx;

    public CoinAdapter(List<? extends ExpandableGroup> coins) {
        super(coins);
//        this.mCtx = mCtx;
    }

    @Override
    public CoinViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_coins, parent, false);
        return new CoinViewHolder(view);
    }

    @Override
    public CourseViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_currency, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(CourseViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Courses course = (Courses)group.getItems().get(childIndex);
        holder.setCourseName(course);
    }

    @Override
    public void onBindGroupViewHolder(CoinViewHolder holder, int flatPosition, ExpandableGroup coins) {
//        coin_icon.setImageDrawable(mCtx.getResources().getDrawable(0));
        holder.setCoinName(coins);
        holder.setCoinIcon(coins);
    }
}
