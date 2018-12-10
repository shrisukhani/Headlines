package shridharsukhani.com.headlines.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.yoga.YogaEdge;

import shridharsukhani.com.headlines.R;
import shridharsukhani.com.headlines.ui.sections.HeadlinesListSection;

public class HeadlinesActivity extends AppCompatActivity {
    private static final String TAG = HeadlinesActivity.class.getSimpleName();
    private static final int INTERNET_PERM_REQ_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SectionContext context = new SectionContext(this);

        setContentView(
                LithoView.create(
                        context,
                        RecyclerCollectionComponent.create(context)
                                .marginDip(YogaEdge.ALL, 4)
                                .section(HeadlinesListSection.create(context))
                                .build()));

        getSupportActionBar().setCustomView(R.layout.custom_appbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }
}
