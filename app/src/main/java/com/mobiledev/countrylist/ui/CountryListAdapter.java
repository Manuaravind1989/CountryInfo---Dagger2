package com.mobiledev.countrylist.ui;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestBuilder;
import com.mobiledev.countrylist.R;
import com.mobiledev.countrylist.model.CountriesModel;
import com.mobiledev.countrylist.utils.GlideApp;
import com.mobiledev.countrylist.utils.SvgSoftwareLayerSetter;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Manu on 11/1/2017.
 */

public class CountryListAdapter extends RecyclerView.Adapter<CountryViewHolder> {
    private List<CountriesModel> mValues;
    private Context context;
    private int type;
    OnItemSelectionListener onItemSelectionListener;

    public CountryListAdapter(Context context, List<CountriesModel> items, int type, OnItemSelectionListener onItemSelectionListener) {
        this.context = context;
        mValues = items;
        this.type = type;
        this.onItemSelectionListener = onItemSelectionListener;
    }



    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, final int position) {
        holder.titleTV.setText(mValues.get(position).getName());
        holder.capitalTV.setText(mValues.get(position).getCapital());
        RequestBuilder<PictureDrawable>  requestBuilder = GlideApp.with(context)
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());

        Uri uri = Uri.parse(mValues.get(position).getFlag());
        requestBuilder.load(uri).into(holder.simpleDraweeView);

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemSelectionListener.onItemClick(mValues.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface  OnItemSelectionListener{
        void onItemClick(Object obj);
    }
}
