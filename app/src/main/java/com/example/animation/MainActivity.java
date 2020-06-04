package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    List<NewsItem> mData;
    FloatingActionButton fabSwitcher;
    boolean isDark = false;
    ConstraintLayout rootLayout;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        fabSwitcher = findViewById(R.id.fab_switcher);
        rootLayout = findViewById(R.id.rootLayout);
        searchEditText = findViewById(R.id.search_editText);
        recyclerView = findViewById(R.id.news_rv);
        mData = new ArrayList<>();

        isDark = getDarkState();
        if (isDark){
            searchEditText.setBackgroundResource(R.drawable.search_dark_input);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.black));
        }
        else {
            searchEditText.setBackgroundResource(R.drawable.search_input);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }

        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));
        mData.add(new NewsItem("one plus ", "In this guide, we will learn about functions in Python. A function is a block of code that contains one or more Python statements and used for performing a specific task.", "ddd", R.drawable.user));

        newsAdapter = new NewsAdapter(this, mData, isDark);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fabSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDark = !isDark;
                if (isDark){
                    searchEditText.setBackgroundColor(getResources().getColor(R.color.black));
                    rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
                }
                else {
                    searchEditText.setBackgroundColor(getResources().getColor(R.color.white));
                    rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
                }
                newsAdapter = new NewsAdapter(getApplicationContext(), mData, isDark);
                recyclerView.setAdapter(newsAdapter);

                saveDarkMode(isDark);
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newsAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void saveDarkMode(boolean isDark) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isDark", isDark);
        editor.commit();

    }
    private boolean getDarkState(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        boolean isDark = preferences.getBoolean("IsDark", false);
        return isDark;
    }
}
