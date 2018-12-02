package csci5115team5.com.headlines.model.network;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import csci5115team5.com.headlines.model.NewsApiResult;
import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class NewsApiServiceTest {
    private final String QUERY = "query";
    private final int PAGE_NO = 1;
    private final String FROM = "2018-11-01";
    private final String TO = "2018-12-01";

    @Mock Call<NewsApiResult> resultCall;
    @Mock NewsApiRetrofit newsApiRetrofit;
    @InjectMocks NewsApiService newsApiService;

    private InOrder inOrder;

    @Before
    public void setUp() throws Exception {
        when(newsApiRetrofit.getStoriesForQuery(anyString())).thenReturn(resultCall);
        when(newsApiRetrofit.getStoriesForQuery(anyString(), anyInt()))
                .thenReturn(resultCall);
        when(newsApiRetrofit.getStoriesForQueryBetweenDates(
                anyString(), anyString(), anyString())).thenReturn(resultCall);
        when(newsApiRetrofit.getStoriesForQueryBetweenDates(
                anyString(), anyString(), anyString(), anyInt())).thenReturn(resultCall);
        when(newsApiRetrofit.getStoriesForQueryFromDate(
                anyString(), anyString())).thenReturn(resultCall);
        when(newsApiRetrofit.getStoriesForQueryFromDate(
                anyString(), anyString(), anyInt())).thenReturn(resultCall);
        when(newsApiRetrofit.getStoriesForQueryToDate(
                anyString(), anyString())).thenReturn(resultCall);
        when(newsApiRetrofit.getStoriesForQueryToDate(
                anyString(), anyString(), anyInt())).thenReturn(resultCall);

        inOrder = inOrder(newsApiRetrofit, resultCall);
    }

    @Test
    public void test_getStoriesForQuery() throws Exception {
        newsApiService.getStoriesForQuery(QUERY);
        newsApiService.getStoriesForQuery(QUERY, PAGE_NO);

        inOrder.verify(newsApiRetrofit, times(1)).getStoriesForQuery(QUERY);
        inOrder.verify(resultCall, times(1)).enqueue(any(Callback.class));
        inOrder.verify(newsApiRetrofit, times(1)).getStoriesForQuery(QUERY, PAGE_NO);
        inOrder.verify(resultCall, times(1)).enqueue(any(Callback.class));
    }


    @Test
    public void test_getStoriesForQueryFromDate() throws Exception {
        newsApiService.getStoriesForQueryFromDate(QUERY, FROM);
        newsApiService.getStoriesForQueryFromDate(QUERY, FROM, PAGE_NO);

        inOrder.verify(newsApiRetrofit, times(1))
                .getStoriesForQueryFromDate(QUERY, FROM);
        inOrder.verify(resultCall, times(1)).enqueue(any(Callback.class));
        inOrder.verify(newsApiRetrofit, times(1))
                .getStoriesForQueryFromDate(QUERY, FROM, PAGE_NO);
        inOrder.verify(resultCall, times(1)).enqueue(any(Callback.class));
    }

    @Test
    public void test_getStoriesForQueryToDate() throws Exception {
        newsApiService.getStoriesForQueryToDate(QUERY, TO);
        newsApiService.getStoriesForQueryToDate(QUERY, TO, PAGE_NO);

        inOrder.verify(newsApiRetrofit, times(1)).getStoriesForQueryToDate(QUERY, TO);
        inOrder.verify(resultCall, times(1)).enqueue(any(Callback.class));
        inOrder.verify(newsApiRetrofit, times(1))
                .getStoriesForQueryToDate(QUERY, TO, PAGE_NO);
        inOrder.verify(resultCall, times(1)).enqueue(any(Callback.class));
    }

    @Test
    public void test_getStoriesForQueryBetweenDates() throws Exception {
        newsApiService.getStoriesForQueryBetweenDates(QUERY, FROM, TO);
        newsApiService.getStoriesForQueryBetweenDates(QUERY, FROM, TO, PAGE_NO);

        inOrder.verify(newsApiRetrofit, times(1))
                .getStoriesForQueryBetweenDates(QUERY, FROM, TO);
        inOrder.verify(resultCall, times(1)).enqueue(any(Callback.class));
        inOrder.verify(newsApiRetrofit, times(1))
                .getStoriesForQueryBetweenDates(QUERY, FROM, TO, PAGE_NO);
        inOrder.verify(resultCall, times(1)).enqueue(any(Callback.class));
    }
}