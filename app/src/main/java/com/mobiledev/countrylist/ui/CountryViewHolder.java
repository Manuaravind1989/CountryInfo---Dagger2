package com.mobiledev.countrylist.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobiledev.countrylist.R;

/**
 * Created by Manu on 11/1/2017.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder {
    public final TextView titleTV;
    public final TextView capitalTV;
    public final ImageView simpleDraweeView;
    public final LinearLayout itemLayout;


    public CountryViewHolder(View view) {
        super(view);
        titleTV =  view.findViewById(R.id.titleTV);
        capitalTV =  view.findViewById(R.id.capitalTV);
        simpleDraweeView = view.findViewById(R.id.my_image_view);
        itemLayout = view.findViewById(R.id.movielistItemLayout);

    }
}

