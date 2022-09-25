package com.example.AppCiisa.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.AppCiisa.models.BmiEntity;
import com.example.AppCiisa.utils.Converters;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BmiDao_Impl implements BmiDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BmiEntity> __insertionAdapterOfBmiEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public BmiDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBmiEntity = new EntityInsertionAdapter<BmiEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `bmi` (`id`,`date`,`weight`,`calculatedBmi`,`user_id`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BmiEntity value) {
        stmt.bindLong(1, value.getId());
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindDouble(3, value.getWeight());
        stmt.bindDouble(4, value.getCalculatedBmi());
        stmt.bindLong(5, value.getUserId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM bmi WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final BmiEntity bmi) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfBmiEntity.insertAndReturnId(bmi);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public List<BmiEntity> findAll(final long userId) {
    final String _sql = "SELECT id, date, weight, calculatedBmi, user_id FROM bmi WHERE user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfCalculatedBmi = CursorUtil.getColumnIndexOrThrow(_cursor, "calculatedBmi");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final List<BmiEntity> _result = new ArrayList<BmiEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BmiEntity _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = Converters.fromTimestamp(_tmp);
        final double _tmpWeight;
        _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
        final double _tmpCalculatedBmi;
        _tmpCalculatedBmi = _cursor.getDouble(_cursorIndexOfCalculatedBmi);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item = new BmiEntity(_tmpId,_tmpDate,_tmpWeight,_tmpCalculatedBmi,_tmpUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<BmiEntity> findByRange(final Date from, final Date to, final long userId) {
    final String _sql = "SELECT id, date, weight, calculatedBmi, user_id FROM bmi WHERE user_id = ? AND date BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    final Long _tmp;
    _tmp = Converters.dateToTimestamp(from);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 3;
    final Long _tmp_1;
    _tmp_1 = Converters.dateToTimestamp(to);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfCalculatedBmi = CursorUtil.getColumnIndexOrThrow(_cursor, "calculatedBmi");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final List<BmiEntity> _result = new ArrayList<BmiEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BmiEntity _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final Date _tmpDate;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = Converters.fromTimestamp(_tmp_2);
        final double _tmpWeight;
        _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
        final double _tmpCalculatedBmi;
        _tmpCalculatedBmi = _cursor.getDouble(_cursorIndexOfCalculatedBmi);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item = new BmiEntity(_tmpId,_tmpDate,_tmpWeight,_tmpCalculatedBmi,_tmpUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
