package at.ac.univie.hci.btctracker.Data;

/**
 *for expanded child list in usd and btc
 *
 * Created by vanish on 11.05.18.
 */

public class Courses {
    private String course;

    public Courses(String curCourse) {
        this.course = curCourse;
    }

    public String getName() {
        return course;
    }

    public void setName(String curCourse) {
        this.course = curCourse;
    }
}
