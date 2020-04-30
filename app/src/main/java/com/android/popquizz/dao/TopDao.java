package com.android.popquizz.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.popquizz.entity.TopEntity;

import java.util.List;

@Dao
public interface TopDao {

    @Query("select * from top")
    List<TopEntity> getAllTops();

    @Query("select * from top ORDER BY score DESC lIMIT :number")
    List<TopEntity> getTops(int number);

    @Insert
    void insertTop(TopEntity top);

    @Insert
    void updateTop(TopEntity top);

    @Delete
    void deleteTop(TopEntity top);

}
