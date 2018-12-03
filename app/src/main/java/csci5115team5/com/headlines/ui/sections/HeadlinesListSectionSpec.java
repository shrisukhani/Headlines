package csci5115team5.com.headlines.ui.sections;

import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.facebook.litho.Column;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.State;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.LoadingEvent;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.SectionLifecycle;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnBindService;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.annotations.OnCreateService;
import com.facebook.litho.sections.annotations.OnRefresh;
import com.facebook.litho.sections.annotations.OnUnbindService;
import com.facebook.litho.sections.annotations.OnViewportChanged;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.EditText;
import com.facebook.litho.widget.Progress;
import com.facebook.litho.widget.RenderInfo;
import com.facebook.litho.widget.SetTextEvent;
import com.facebook.litho.widget.TextChangedEvent;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import csci5115team5.com.headlines.model.NewsApiResult;
import csci5115team5.com.headlines.model.NewsApiResultEvent;
import csci5115team5.com.headlines.model.NewsStory;
import csci5115team5.com.headlines.model.network.NewsApiService;
import csci5115team5.com.headlines.model.network.NewsApiServiceFactory;
import csci5115team5.com.headlines.ui.components.HeadlinesListItem;

import static com.facebook.yoga.YogaAlign.CENTER;

@GroupSectionSpec
public class HeadlinesListSectionSpec {
    private static final String TAG = HeadlinesListSectionSpec.class.getSimpleName();
    private static final int PAGE_SIZE = 20;

    @OnCreateChildren
    static Children onCreateChildren(
            SectionContext c,
            @State String query,
            @State boolean isFetching,
            @State boolean finishedInitialLoad,
            @State int lastPageFetched,
            @State int numTotalResults,
            @State List<NewsStory> stories) {
        Children.Builder builder = Children.create();
        final Progress.Builder progress = Progress.create(c)
                .widthDip(56).heightDip(56).alignSelf(CENTER);

        builder.child(
                SingleComponentSection.create(c)
                        .component(
                                EditText.create(c)
                                        .textSizeDip(22f)
                                        .hint("Search Headlines")
                                        .textStyle(Typeface.ITALIC)
                                        .maxLines(1)
                                        .textChangedEventHandler(HeadlinesListSection.handleTextChange(c))
                                        .setTextEventHandler(HeadlinesListSection.handleTextSet(c))));
        builder.child(
                DataDiffSection.<NewsStory>create(c)
                        .data(stories)
                        .renderEventHandler(HeadlinesListSection.renderNewsStory(c)));
        builder.child(
                isFetching
                        ? SingleComponentSection.create(c).component(Column.create(c).child(progress))
                        : null);
        return builder.build();
    }

    @OnCreateInitialState
    static void createInitialState(
            final SectionContext c,
            StateValue<String> query,
            StateValue<Boolean> isFetching,
            StateValue<Boolean> finishedInitialLoad,
            StateValue<Integer> lastPageFetched,
            StateValue<Integer> numTotalResults,
            StateValue<List<NewsStory>> stories) {
        query.set("USA");
        isFetching.set(false);
        finishedInitialLoad.set(false);
        lastPageFetched.set(0);
        numTotalResults.set(0);
        stories.set(new ArrayList<NewsStory>());
    }

    @OnRefresh
    static void onRefresh(
            final SectionContext c,
            NewsApiService service,
            @State String query,
            @State int lastPageFetched) {
        HeadlinesListSection.updateState(c, null, true, null, null);
        service.getStoriesForQuery(query, lastPageFetched);
    }

    @OnViewportChanged
    static void onViewportChanged(
            SectionContext c,
            int firstVisiblePosition,
            int lastVisiblePosition,
            int firstFullyVisibleIndex,
            int lastFullyVisibleIndex,
            int totalCount,
            NewsApiService newsApiService,
            @State String query,
            @State int numTotalResults,
            @State int lastPageFetched,
            @State boolean isFetching,
            @State boolean finishedInitialLoad,
            @State List<NewsStory> stories) {
        if (finishedInitialLoad && lastPageFetched * PAGE_SIZE >= numTotalResults) {
            Toast.makeText(c.getAndroidContext(), "No more results 🙁", Toast.LENGTH_LONG).show();
            return;
        }
        if (stories.size() > lastVisiblePosition + 3) {
            return;
        }
        if (isFetching) {
            Log.d(TAG, "Early return because something is already being fetched");
            return;
        }
        Log.d(TAG, "Fetching new stories");
        newsApiService.getStoriesForQuery(query, lastPageFetched + 1);
        HeadlinesListSection.updateState(c, null, true, lastPageFetched + 1, null);
    }

    @OnUpdateState
    static void updateState(
            StateValue<String> query,
            StateValue<Boolean> isFetching,
            StateValue<Boolean> finishedInitialLoad,
            StateValue<Integer> lastPageFetched,
            StateValue<Integer> numTotalResults,
            StateValue<List<NewsStory>> stories,
            @Nullable @Param String newQuery,
            @Nullable @Param Boolean newIsFetching,
            @Nullable @Param Integer newLastPageFetched,
            @Nullable @Param List<NewsStory> newStories) {
        if (newIsFetching != null) {
            isFetching.set(newIsFetching);
        }
        if (newLastPageFetched != null) {
            lastPageFetched.set(newLastPageFetched);
        }
        if (newQuery != null) {
            query.set(newQuery);
            lastPageFetched.set(0);
            finishedInitialLoad.set(false);
            numTotalResults.set(0);
            stories.set(new ArrayList<NewsStory>());
        }
        if (newStories != null) {
            List<NewsStory> storiesList = new ArrayList<>(stories.get());
            storiesList.addAll(newStories);
            stories.set(storiesList);
        }
    }

    @OnUpdateState
    static void updateNumTotalResults(
            StateValue<Integer> numTotalResults,
            StateValue<Boolean> finishedInitialLoad,
            @Param int totalResults) {
        numTotalResults.set(totalResults);
        finishedInitialLoad.set(true);
    }

    @OnCreateService
    static NewsApiService onCreateService(final SectionContext c) {
        return NewsApiServiceFactory.getNewsApiService();
    }

    @OnBindService
    static void onBindService(
            final SectionContext c,
            NewsApiService service,
            @State String query,
            @State boolean finishedInitialLoad,
            @State boolean isFetching) {
        service.registerEventHandler(HeadlinesListSection.onDataLoaded(c));
        if (isFetching || finishedInitialLoad) {
            return;
        }
        service.getStoriesForQuery(query);
    }

    @OnUnbindService
    static void onUnbindService(final SectionContext c, NewsApiService service) {
        service.unregisterEventHandler();
    }

    @OnEvent(NewsApiResultEvent.class)
    static void onDataLoaded(
            final SectionContext c,
            @FromEvent NewsApiResult result,
            @State boolean finishedInitialLoad) {
        Log.d(TAG, String.valueOf(result));
        if (result == null) {
            Toast.makeText(c.getAndroidContext(), "Something went wrong 😢", Toast.LENGTH_LONG);
        }
        if (!finishedInitialLoad) {
            HeadlinesListSection.updateNumTotalResults(c, result.getTotalResults());
            HeadlinesListSection.updateState(c, null, null, 1, null);
        }
        HeadlinesListSection.updateState(c, null, false, null, result.getArticles());
        SectionLifecycle.dispatchLoadingEvent(c, false, LoadingEvent.LoadingState.SUCCEEDED, null);
    }

    @OnEvent(RenderEvent.class)
    static RenderInfo renderNewsStory(
            final SectionContext c, @FromEvent NewsStory model) {
        return ComponentRenderInfo.create()
                .component(
                        HeadlinesListItem.create(c)
                                .storyImageUri(Uri.parse(model.getUrlToImage().toString()))
                                .title(model.getTitle())
                                .description(model.getDescription())
                                .dayDateTime(model.getFormattedDayDateTime())
                                .publicationName(model.getSource().getName()))
                .build();
    }

    @OnEvent(TextChangedEvent.class)
    static void handleTextChange(final SectionContext c, @FromEvent String text) {
        if (TextUtils.getTrimmedLength(text) < 3) {
            return;
        }
        HeadlinesListSection.updateState(c, text.trim(), null, null, null);
    }

    @OnEvent(SetTextEvent.class)
    static void handleTextSet(final SectionContext c, @FromEvent CharSequence text, @State boolean isFetching) {
        if (isFetching || TextUtils.getTrimmedLength(text) < 1) {
            return;
        }
        HeadlinesListSection.updateState(c, text.toString().trim(), null, null, null);
    }
}