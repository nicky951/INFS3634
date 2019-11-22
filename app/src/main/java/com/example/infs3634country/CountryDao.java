package com.example.infs3634country;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//Database queries
@Dao
public interface CountryDao {

    @Query("SELECT * FROM Country")
    List<Country> getCountry();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Country> countries);

    @Query("DELETE FROM Country")
    public void deleteTable();

}
