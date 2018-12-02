package csci5115team5.com.headlines.model;

import com.facebook.litho.annotations.Event;

import csci5115team5.com.headlines.model.NewsApiResult;

@Event
public class NewsApiResultEvent {
    public NewsApiResult result;

    public NewsApiResultEvent(NewsApiResult result) {
        this.result = result;
    }
}

