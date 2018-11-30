package csci5115team5.com.headlines.model;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.net.URL;

import static com.google.common.truth.Truth.assertThat;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class NewsStoryTest {
    private NewsStory mStory;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String testJsonToSerialize = "{\"source\":{\"id\":null,\"name\":\"Express.co.uk\"},\"" +
                "author\":\"Nicholas Frakes\",\"title\":\"WORLD WAR 3 ALERT: Russia ‘lining up " +
                "tanks along our border’ warns Ukraine - Express.co.uk\",\"description\":\"THE " +
                "Ukranian President has sensationally claimed Russian military tanks are lining " +
                "up along his country's border, triggering another World War 3 alert.\",\"url\":\"" +
                "https://www.express.co.uk/news/world/1052330/world-war-3-russia-tanks-ukraine-" +
                "border-putin-news\",\"urlToImage\":\"https://cdn.images.express.co.uk/img/dynamic" +
                "/78/750x445/1052330.jpg\",\"publishedAt\":\"2018-11-30T00:03:00Z\",\"content\":null}";

        mStory = gson.fromJson(testJsonToSerialize, NewsStory.class);
    }

    @Test
    public void testAllExceptDayDateTime_when_gsonSerializesJson_then_allGettersReturnExpectedResults()
            throws Exception {
        String title = "WORLD WAR 3 ALERT: Russia ‘lining up tanks along our border’ warns Ukraine - Express.co.uk";
        URL url = new URL("https://www.express.co.uk/news/world/1052330/world-war-3-russia-tanks-ukraine-border-putin-news");
        URL imageUrl = new URL("https://cdn.images.express.co.uk/img/dynamic/78/750x445/1052330.jpg");
        String sourceName = "Express.co.uk";

        assertThat(mStory.getTitle()).isEqualTo(title);
        assertThat(mStory.getUrl()).isInstanceOf(URL.class);
        assertThat(mStory.getUrl()).isEqualTo(url);
        assertThat(mStory.getUrlToImage()).isInstanceOf(URL.class);
        assertThat(mStory.getUrlToImage()).isEqualTo(imageUrl);
        assertThat(mStory.getSource()).isInstanceOf(NewsStory.Source.class);
        assertThat(mStory.getSource().getName()).matches(sourceName);
    }

    @Test
    public void testFormat_whenGetFormattedDayDateTimeCalled() throws Exception {
        String formattedDate = "11-30-2018 | 00:03:00";

        assertThat(mStory.getFormattedDayDateTime()).isEqualTo(formattedDate);
    }
}