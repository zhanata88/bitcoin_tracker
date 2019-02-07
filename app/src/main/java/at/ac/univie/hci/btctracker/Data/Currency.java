package at.ac.univie.hci.btctracker.Data;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by rabizo on 23.04.2018.
 */

public class Currency{

    private String id;
    private String name;
    private Double priseInUsd;
    private Double priseInEur;
    private Double priceInBitcoin;
    private Double dayVolume;
    private Double dayChange;
    private Double weekChange;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriseInUsd() {
        return priseInUsd;
    }

    public void setPriseInUsd(Double priseInUsd) {
        this.priseInUsd = priseInUsd;
    }

    public Double getPriseInEur() {
        return priseInEur;
    }

    public void setPriseInEur(Double priseInEur) {
        this.priseInEur = priseInEur;
    }

    public Double getPriceInBitcoin() {
        return priceInBitcoin;
    }

    public void setPriceInBitcoin(Double priceInBitcoin) {
        this.priceInBitcoin = priceInBitcoin;
    }

    public Double getDayVolume() {
        return dayVolume;
    }

    public void setDayVolume(Double dayVolume) {
        this.dayVolume = dayVolume;
    }

    public Double getDayChange() {
        return dayChange;
    }

    public void setDayChange(Double dayChange) {
        this.dayChange = dayChange;
    }

    public Double getWeekChange() {
        return weekChange;
    }

    public void setWeekChange(Double weekChange) {
        this.weekChange = weekChange;
    }

}
