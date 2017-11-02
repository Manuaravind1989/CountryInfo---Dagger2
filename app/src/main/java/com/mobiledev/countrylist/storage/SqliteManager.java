package com.mobiledev.countrylist.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobiledev.countrylist.model.CountriesModel;

import java.util.ArrayList;

/**
 * Created by Manu on 10/31/2017.
 */

public class SqliteManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "countrylist";
    private static final String TABLE_NAME = "countrytable";
    private static final String NAME = "name";
    private static final String CAPITAL = "capital";
    private static final String FLAG = "flag";
    private static final String POPULATION = "population";
    private static final String AREA = "area";
    private static final String CURRENCY = "currency";
    Context c;

    public SqliteManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POPULAR_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + NAME + " TEXT," + CAPITAL + " TEXT,"
                + FLAG + " TEXT," + POPULATION + " TEXT,"
                + AREA + " TEXT," + CURRENCY + " TEXT" + ")";
        db.execSQL(CREATE_POPULAR_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public void addCountryInfo(CountriesModel resultDetailModelsEntity) {

        SQLiteDatabase db = this.getWritableDatabase();

        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE name=" + resultDetailModelsEntity.getName();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.getCount() > 0) {
            //PID Found
            //   updation will do later
        } else {
            insertToDb(resultDetailModelsEntity);
        }
    }

    public void insertToDb(CountriesModel resultDetailModelsEntity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, resultDetailModelsEntity.getName());
        contentValues.put(CAPITAL, resultDetailModelsEntity.getCapital());
        contentValues.put(FLAG, resultDetailModelsEntity.getFlag());
        contentValues.put(POPULATION, resultDetailModelsEntity.getPopulation()+"");
        contentValues.put(AREA, resultDetailModelsEntity.getArea()+"");
        String currency = "";
        for (CountriesModel.CurrenciesEntity currenciesEntity : resultDetailModelsEntity.getCurrencies()) {
            currency = currency + ":" + currenciesEntity.getName() + "  " + currenciesEntity.getCode() + "  " + currenciesEntity.getSymbol();
        }
        contentValues.put(CURRENCY, currency);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updateCountry(CountriesModel resultDetailModelsEntity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //   contentValues.put(ID, resultDetailModelsEntity.getId());
        contentValues.put(NAME, resultDetailModelsEntity.getName());
        contentValues.put(CAPITAL, resultDetailModelsEntity.getCapital());
        contentValues.put(FLAG, resultDetailModelsEntity.getFlag());
        contentValues.put(POPULATION, resultDetailModelsEntity.getPopulation()+"");
        contentValues.put(AREA, resultDetailModelsEntity.getArea()+"");
        String currency = "";
        for (CountriesModel.CurrenciesEntity currenciesEntity : resultDetailModelsEntity.getCurrencies()) {
            currency = currency + ":" + currenciesEntity.getName() + "  " + currenciesEntity.getCode() + "  " + currenciesEntity.getSymbol();
        }
        contentValues.put(CURRENCY, currency);
        db.update(TABLE_NAME, contentValues, NAME + " = ?",
                new String[]{String.valueOf(resultDetailModelsEntity.getName())});

        db.close();
    }

    public ArrayList<CountriesModel> getFavouriteList() {
        ArrayList<CountriesModel> _ArrayList = new ArrayList<CountriesModel>();
        String countQuery =  "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CountriesModel item = new CountriesModel();
                item.setName(cursor.getString(cursor
                        .getColumnIndex(NAME)));
                item.setCapital(cursor.getString(cursor
                        .getColumnIndex(CAPITAL)));
                item.setFlag(cursor.getString(cursor
                        .getColumnIndex(FLAG)));
                item.setPopulation(Integer.valueOf(cursor.getString(cursor
                        .getColumnIndex(POPULATION))));
                item.setArea(Double.valueOf(cursor.getString(cursor
                        .getColumnIndex(AREA))));
//                List<CountriesModel.CurrenciesEntity> currencyList = new ArrayList<>();
//                String currencyString = cursor.getString(cursor
//                        .getColumnIndex(CURRENCY));
//                String[] currencyArray = currencyString.split(":");
//                item.setCurrencies(currencyList);

                // adding to todo list
                _ArrayList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("ARRAY LEBGTH ==>", "" + _ArrayList.size());
        return _ArrayList;
    }



}