package com.tuacy.room.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.RoomWarnings;

@Entity(tableName = "student_list", indices = {@Index(value = "id")})
public class Student {
    @ColumnInfo(name = "content_")
    public String content;

    @PrimaryKey(autoGenerate = true)
    public int id;

    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded(prefix = "user_")
    public User user;

    @ColumnInfo(name = "name_")
    public String name;

    @ColumnInfo(name = "age_")
    public int age;

    @Ignore
    public String ext_1;

    @ColumnInfo(name = "ext_2")
    public String ext_2;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getExt_1() {
        return ext_1;
    }

    public void setExt_1(String ext_1) {
        this.ext_1 = ext_1;
    }

    public String getExt_2() {
        return ext_2;
    }

    public void setExt_2(String ext_2) {
        this.ext_2 = ext_2;
    }

    @Override
    public String toString() {
        return "Student{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", ext_1='" + ext_1 + '\'' +
                ", ext_2='" + ext_2 + '\'' +
                '}';
    }
}
