package shridharsukhani.com.headlines;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.soloader.SoLoader;

public class HeadlinesApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
        Fresco.initialize(this);
    }
}
