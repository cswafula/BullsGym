package com.example.charlie.bullsgym;

import android.app.Application;
import android.content.Context;

import com.example.charlie.bullsgym.helper.LocaleHelper;

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base,"en"));
    }
}
