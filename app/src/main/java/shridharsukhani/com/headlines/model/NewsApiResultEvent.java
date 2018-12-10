package shridharsukhani.com.headlines.model;

import com.facebook.litho.annotations.Event;

@Event
public class NewsApiResultEvent {
    public NewsApiResult result;

    public NewsApiResultEvent(NewsApiResult result) {
        this.result = result;
    }
}

