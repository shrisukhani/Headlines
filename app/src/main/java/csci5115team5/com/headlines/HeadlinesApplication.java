package csci5115team5.com.headlines;

import android.app.Application;

import com.facebook.soloader.SoLoader;

public class HeadlinesApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }
}
