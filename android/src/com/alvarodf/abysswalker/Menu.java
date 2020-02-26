package com.alvarodf.abysswalker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @since February 26th, 2020
 */
public class Menu extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     * @since February 26th, 2020
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);

    }

    /**
     *
     * @param view
     * @since February 26th, 2020
     */
    public void play(View view) {

        Intent i = new Intent(this, AndroidLauncher.class);
        this.startActivity(i);

    }

}
