package com.tuacy.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tuacy.room.app.BaseActivity;
import com.tuacy.room.database.AppDatabase;
import com.tuacy.room.database.entities.Book;
import com.tuacy.room.database.entities.MiniProgram;
import com.tuacy.room.database.entities.Student;
import com.tuacy.room.database.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_add_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setPhone("18988195061");
                user.setAddress("珠海");
                user.setName("tuacy");
                User user2 = new User();
                user2.setPhone("18988195061-0");
                user2.setAddress("珠海-0");
                user2.setName("tuacy-0");
                List<Long> ids = mAppDatabase.userDao().insert(user, user2);
                if (ids != null) {
                    for (Long id : ids) {
                        Log.d("tuacy", "id = " + id);
                    }
                }
            }
        });

        findViewById(R.id.button_add_user).setOnLongClickListener(v -> {
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setAddress(String.format("中国第%s大城市", i + 1 + ""));
                user.setAge(20 + i);
                user.setName("吴小明" + i);
                user.setPhone("4158785" + i);
                users.add(user);
            }
            List<Long> longs = mAppDatabase.userDao().insertAll(users);
            if (longs != null) {
                for (Long aLong : longs) {
                    Log.d("add_list_book", aLong + "");
                }
            }
            return false;
        });

        findViewById(R.id.button_get_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppDatabase.userDao()
                        .loadUser()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<User>>() {
                            @Override
                            public void accept(List<User> entities) {
                                if (entities != null) {
                                    for (User user : entities) {
                                        Log.d("tuacy", user.toString());
                                    }
                                }

                            }
                        });
            }
        });

        findViewById(R.id.button_add_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("Android入门到精通" + book.getUid());
                book.setUserId(1L);
                book.setTime(new Date());
                Book book2 = new Book();
                book2.setName("Android入门到精通" + book.getUid());
                book2.setTime(new Date());
                book2.setUserId(4L);
                List<Long> ids = mAppDatabase.bookDao().insert(book, book2);
                if (ids != null) {
                    for (Long id : ids) {
                        Log.d("add_book", "id = " + id);
                    }
                }
            }
        });


        findViewById(R.id.button_get_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppDatabase.bookDao()
                        .load()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Book>>() {
                            @Override
                            public void accept(List<Book> entities) {
                                if (entities != null) {
                                    for (Book book : entities) {
                                        Log.d("get_book", book.toString());
                                    }
                                }

                            }
                        });
            }
        });

        findViewById(R.id.button_add_mini).setOnClickListener(v -> {
            MiniProgram miniProgram1 = new MiniProgram();
            miniProgram1.setIcon("www.baidu.com-1");
            miniProgram1.setDesc("详细描述1");
//            miniProgram1.setId(10);
            miniProgram1.setName("吴小明");
            miniProgram1.setTime(new Date());
            miniProgram1.setUrl("http://google.com-1");
            MiniProgram miniProgram2 = new MiniProgram();
            miniProgram2.setIcon("www.baidu.com-2");
            miniProgram2.setDesc("详细描述2");
//            miniProgram2.setId(102);
            miniProgram2.setName("吴小明2");
            miniProgram2.setTime(new Date());
            miniProgram2.setUrl("http://google.com-2");

//            mAppDatabase.miniDao().insert(miniProgram1, miniProgram2);
        });

        findViewById(R.id.button_get_mini).setOnClickListener(v -> {
//            mAppDatabase.miniDao()
//                    .query()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(miniPrograms -> {
//                        if (miniPrograms != null) {
//                            for (MiniProgram miniProgram : miniPrograms) {
//                                Log.d("query", miniProgram.toString());
//                            }
//                        }
//                    });
        });

        findViewById(R.id.button_delete_mini).setOnClickListener(v -> {
//            int i = mAppDatabase.miniDao().deleteByUrl("http://google.com-1");
            Log.d("delete_no", 8 + "");
        });
    }

    public void addStudent(View view) {
        Student student = new Student();
        student.content = "吴小明的简介";
        student.age = 27;
        student.ext_1 = "预留字段1";
        student.ext_2 = "预留字段2";
        student.id = 2;
        student.name = "吴小明";
        User user2 = new User();
        user2.setPhone("18988195061-0");
        user2.setAddress("珠海-0");
        user2.setName("tuacy-0");
        student.user = user2;

//        mAppDatabase.studentDao().insert(student);
    }

    public void queryStudent(View view) {
       /* mAppDatabase.studentDao()
                .queryAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(List<Student> students) throws Exception {
                        if (students != null) {
                            for (Student student : students) {
                                Log.d("query_studen", student.toString());
                            }
                        }
                    }
                });*/
    }

    public void deleteByIdBook(View view) {
        int aLong = mAppDatabase.bookDao().deleteById(1);
        Log.d("deleteByIdBook", aLong + "");
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<Book>>() {
//                    @Override
//                    public void accept(List<Book> books) throws Exception {
//                        if (books != null) {
//                            for (Book book : books) {
//                                Log.d("deleteByIdBook", book.toString());
//                            }
//                        }
//                    }
//                });
    }

    public void deleteByItemBook(View view) {
        Book book2 = new Book();
        book2.setName("Android入门到精通" + book2.getUid());
        book2.setTime(new Date());
        book2.setUserId(4L);
        int aLong = mAppDatabase.bookDao().deleteByItem(book2);
        Log.d("deleteByItemBook:", aLong + "");
    }
}
