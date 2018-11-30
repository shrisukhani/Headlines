package csci5115team5.com.headlines.model.network;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class NewsApiServiceFactoryTest {
    @Test
    public void test_when_getNewsApiServiceCalledMultipleTimes_then_sameInstanceReturned() throws Exception {
        NewsApiService firstService = NewsApiServiceFactory.getNewsApiService();

        // Check any random number of times upto 50 (also randomly chosen :P)
        for (int i = 0; i < Math.random()*50+1; i ++) {
            assertThat(NewsApiServiceFactory.getNewsApiService()).isSameAs(firstService);
        }
    }
}