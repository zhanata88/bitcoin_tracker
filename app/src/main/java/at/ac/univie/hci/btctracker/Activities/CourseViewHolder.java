package at.ac.univie.hci.btctracker.Activities;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import at.ac.univie.hci.btctracker.Data.Courses;
import at.ac.univie.hci.btctracker.Data.Currency;
import at.ac.univie.hci.btctracker.R;

/**
 * view holder for childs of expanded list in usd and btc
 *
 * Created by vanish on 11.05.18.
 */

public class CourseViewHolder extends ChildViewHolder {

    private TextView course;

    public CourseViewHolder(View itemView) {
        super(itemView);
        course = itemView.findViewById(R.id.item_courses);
    }

    public void setCourseName(Courses courses){
        course.setText(courses.getName());
    }
}
