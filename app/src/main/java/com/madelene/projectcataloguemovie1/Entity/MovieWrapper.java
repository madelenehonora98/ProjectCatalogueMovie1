package com.madelene.projectcataloguemovie1.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MovieWrapper implements Parcelable {

    private int page;
    private int total_results;
    private int total_pages;
    private ArrayList<Movie> results;

    public MovieWrapper() {
    }

    protected MovieWrapper(Parcel in) {
        page = in.readInt();
        total_results = in.readInt();
        total_pages = in.readInt();
    }

    public static final Creator<MovieWrapper> CREATOR = new Creator<MovieWrapper>() {
        @Override
        public MovieWrapper createFromParcel(Parcel in) {
            return new MovieWrapper(in);
        }

        @Override
        public MovieWrapper[] newArray(int size) {
            return new MovieWrapper[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(page);
        parcel.writeInt(total_results);
        parcel.writeInt(total_pages);
    }
}
