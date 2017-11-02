package com.mobiledev.countrylist.ui;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiledev.countrylist.HomeActivity;
import com.mobiledev.countrylist.R;
import com.mobiledev.countrylist.model.CountriesModel;
import com.mobiledev.countrylist.network.RestApi;
import com.mobiledev.countrylist.utils.MyApp;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Manu on 11/1/2017.
 */

public class FragmentHome extends Fragment implements CountryListAdapter.OnItemSelectionListener {

    @Inject
    Retrofit retrofit;
    private View rootView;
    private RecyclerView recyclerView;
    HomeActivity homeActivity;
    ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(rootView!=null){
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        homeActivity = (HomeActivity) getActivity();
        ((MyApp) getActivity().getApplication()).getNetComponent().inject(this);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner

        getAllCountries();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        homeActivity.updateTitle(getString(R.string.home));
    }

    private void getAllCountries() {
        progressDialog.show();
        Call<List<CountriesModel>> countries = retrofit.create(RestApi.class).getAll();
        //Enque the call
        countries.enqueue(new Callback<List<CountriesModel>>() {
            @Override
            public void onResponse(Call<List<CountriesModel>> call, Response<List<CountriesModel>> response) {
                if (response.body().size() > 0) {
                    CountryListAdapter adapter = new CountryListAdapter(getActivity(), response.body(), 0, FragmentHome.this);
                    recyclerView.setAdapter(adapter);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<CountriesModel>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onItemClick(Object obj) {
        FragmentDetails fragmentDetails = new FragmentDetails();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragmentDetails);
        fragmentDetails.getData(obj);
        ft.addToBackStack("DETAILS");
        ft.commit();
    }

}