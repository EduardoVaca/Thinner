package com.example.eduardovaca.thinner;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by eduardovaca on 07/10/15.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here

        Parse.initialize(this, "MpfbhzgP7ceDtcCCWjlc2tlZ3d9qA7EeGg5y0PkG", "UseJCvMg8g63CP6yRk5Z013Uu1T9L7VnMzmrQ2XO");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}
