package com.tuacy.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tuacy.room.app.BaseActivity;
import com.tuacy.room.database.entities.Book;
import com.tuacy.room.database.entities.MiniProgram;
import com.tuacy.room.database.entities.User;

import java.util.Date;
import java.util.List;

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
                //				user.setAge("28");
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
                book.setName("Android入门到精通");
                book.setTime(new Date());
                book.setUserId(1L);
                List<Long> ids = mAppDatabase.bookDao().insert(book);
                if (ids != null) {
                    for (Long id : ids) {
                        Log.d("tuacy", "id = " + id);
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
                                        Log.d("tuacy", book.toString());
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
            miniProgram1.setId(10);
            miniProgram1.setName("吴小明");
            miniProgram1.setTime(new Date());
            miniProgram1.setUrl("http://google.com-1");
            MiniProgram miniProgram2 = new MiniProgram();
            miniProgram2.setIcon("www.baidu.com-2");
            miniProgram2.setDesc("详细描述2");
            miniProgram2.setId(102);
            miniProgram2.setName("吴小明2");
            miniProgram2.setTime(new Date());
            miniProgram2.setUrl("http://google.com-2");

            mAppDatabase.miniDao().insert(miniProgram1, miniProgram2);
        });

        findViewById(R.id.button_get_mini).setOnClickListener(v -> {
            mAppDatabase.miniDao()
                    .query()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(miniPrograms -> {
                        if (miniPrograms != null) {
                            for (MiniProgram miniProgram : miniPrograms) {
                                Log.d("tuacy", miniProgram.toString());
                            }
                        }
                    });
        });
    }
}
