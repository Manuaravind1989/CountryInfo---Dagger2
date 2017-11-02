package com.mobiledev.countrylist.network;


import com.mobiledev.countrylist.model.CountriesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Manu on 10/31/2017.
 */

public interface RestApi {



    @GET("all?")
    Call<List<CountriesModel>> getAll();


}
