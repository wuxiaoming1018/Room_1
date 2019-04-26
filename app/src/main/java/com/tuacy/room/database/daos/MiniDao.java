package com.tuacy.room.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tuacy.room.database.entities.MiniProgram;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MiniDao {

    @Insert
    List<Long> insert(MiniProgram... miniPrograms);

    @Query("select * from mini_program")
    Flowable<List<MiniProgram>> query();

    @Query("delete from mini_program where url =:url")
    int deleteByUrl(String url);

    @Delete
    int deleteAll(MiniProgram miniProgram);
}
