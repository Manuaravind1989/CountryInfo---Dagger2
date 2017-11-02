package com.mobiledev.countrylist.dagger.components;


import com.mobiledev.countrylist.HomeActivity;
import com.mobiledev.countrylist.dagger.module.AppModule;
import com.mobiledev.countrylist.dagger.module.NetModule;
import com.mobiledev.countrylist.ui.FragmentHome;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Manu on 10/31/2017.
         */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(HomeActivity activity);
    void inject(FragmentHome fragment);
}