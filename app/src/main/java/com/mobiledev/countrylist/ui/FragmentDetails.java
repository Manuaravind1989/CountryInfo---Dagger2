package com.mobiledev.countrylist.ui;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.mobiledev.countrylist.HomeActivity;
import com.mobiledev.countrylist.R;
import com.mobiledev.countrylist.model.CountriesModel;
import com.mobiledev.countrylist.utils.GlideApp;
import com.mobiledev.countrylist.utils.SvgSoftwareLayerSetter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Manu on 11/1/2017.
 */

public class FragmentDetails extends Fragment {

    private View rootView;
    CountriesModel countriesModel;
    private ImageView imageViewFlag;
    HomeActivity homeActivity;
    private TextView nameTV, capitalTV, populationTV, areaTV, currencyTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_details,container,false);
        imageViewFlag = rootView.findViewById(R.id.imageViewflag);
        nameTV =  rootView.findViewById(R.id.nameTV);
        capitalTV =  rootView.findViewById(R.id.capitalTV);
        populationTV =  rootView.findViewById(R.id.populationTV);
        areaTV =  rootView.findViewById(R.id.areaTV);
        currencyTV =  rootView.findViewById(R.id.currencyTV);
        nameTV.setText(countriesModel.getName());
        capitalTV.setText("Capital: "+countriesModel.getCapital());
        populationTV.setText("Population:"+countriesModel.getPopulation()+"");
        areaTV.setText("Area: "+countriesModel.getArea()+"");
        currencyTV.setText("Currency: \n");
        for(CountriesModel.CurrenciesEntity currency: countriesModel.getCurrencies()) {
            currencyTV.setText(currencyTV.getText().toString()+"\n"+currency.getName()+ "  "+currency.getCode()+"  "+currency.getSymbol());
        }
        homeActivity = (HomeActivity)getActivity();
        // Load SVG images from server
        RequestBuilder<PictureDrawable> requestBuilder = GlideApp.with(getActivity())
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());

        Uri uri = Uri.parse(countriesModel.getFlag());
        requestBuilder.load(uri).into(imageViewFlag);
        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        homeActivity.updateTitle(getString(R.string.details));
    }

    public void getData(Object obj){
        countriesModel = (CountriesModel)obj;
    }
}
