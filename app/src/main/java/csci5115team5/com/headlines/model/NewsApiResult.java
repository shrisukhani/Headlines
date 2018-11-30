package csci5115team5.com.headlines.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResult {
    @SerializedName("status") private String mStatus;
    @SerializedName("totalResults") private int mTotalResults;
    @SerializedName("articles") private List<NewsStory> mArticles;

    public String getStatus() {
        return mStatus;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public List<NewsStory> getArticles() {
        return mArticles;
    }
}
