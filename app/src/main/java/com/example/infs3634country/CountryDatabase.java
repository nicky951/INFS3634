package com.example.infs3634country;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.infs3634country.Country;

@Database(entities =  {Country.class}, version =1)
public abstract class CountryDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();

    private static CountryDatabase instance;
    public static CountryDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, CountryDatabase.class, "countryDb")
                   .allowMainThreadQueries()   // <== IMPORTANT TO NOTE:
                    //     This is NOT correct to do in a completed app.
                    //     Next week we will fix it, but for now this
                    //     line is necessary for the app to work.
                    //     This line will basically allow the database
                    //     queries to freeze the app.
                    .build();
        }
        return instance;
    }
}
