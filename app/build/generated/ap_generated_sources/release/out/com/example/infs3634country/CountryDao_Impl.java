package com.example.infs3634country;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CountryDao_Impl implements CountryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Country> __insertionAdapterOfCountry;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTable;

  public CountryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCountry = new EntityInsertionAdapter<Country>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Country` (`name`,`region`,`capital`,`population`,`flag`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Country value) {
        if (value.name == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.name);
        }
        if (value.region == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.region);
        }
        if (value.capital == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.capital);
        }
        if (value.population == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.population);
        }
        if (value.flag == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.flag);
        }
      }
    };
    this.__preparedStmtOfDeleteTable = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Country";
        return _query;
      }
    };
  }

  @Override
  public void insert(final List<Country> countries) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCountry.insert(countries);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTable() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTable.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTable.release(_stmt);
    }
  }

  @Override
  public List<Country> getCountry() {
    final String _sql = "SELECT * FROM Country";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfRegion = CursorUtil.getColumnIndexOrThrow(_cursor, "region");
      final int _cursorIndexOfCapital = CursorUtil.getColumnIndexOrThrow(_cursor, "capital");
      final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "population");
      final int _cursorIndexOfFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "flag");
      final List<Country> _result = new ArrayList<Country>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Country _item;
        _item = new Country();
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.region = _cursor.getString(_cursorIndexOfRegion);
        _item.capital = _cursor.getString(_cursorIndexOfCapital);
        _item.population = _cursor.getString(_cursorIndexOfPopulation);
        _item.flag = _cursor.getString(_cursorIndexOfFlag);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
