package com.tuacy.room.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tuacy.room.database.entities.Student;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface StudentDao {

//    @Query("SELECT * FROM student_list")
//    Flowable<List<Student>> queryAll();
//
//    @Insert
//    void insert(Student student);
}
