package csci5115team5.com.headlines.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.net.URL;

public final class NewsStory {
    @SerializedName("title") private String mTitle;
    @SerializedName("description") private String mDescription;
    @SerializedName("url") private URL mUrl;
    @SerializedName("urlToImage") private URL mUrlToImg;
    @SerializedName("publishedAt") private String mDayDateTime;
    @SerializedName("source") private Source mSource;

    public static final class Source {
        @SerializedName("name") private String mName;
        @SerializedName("id") private String mId;

        public String getName() {
            return mName;
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public URL getUrl() {
        return mUrl;
    }

    public URL getUrlToImage() {
        return mUrlToImg;
    }

    public Source getSource() {
        return mSource;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getFormattedDayDateTime() {
        String[] splitByT = mDayDateTime.split("T");
        String[] date = TextUtils.split(splitByT[0], "-"); // 0 -> Yr, 1 -> Mth, 2-> Day
        String time = splitByT[1].substring(0, splitByT[1].length() - 1);
        return date[1] + "-" + date[2] + "-" + date[0] + " | " + time;
    }
}
