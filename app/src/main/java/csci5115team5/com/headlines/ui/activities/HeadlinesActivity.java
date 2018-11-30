package csci5115team5.com.headlines.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.yoga.YogaEdge;

import csci5115team5.com.headlines.ui.sections.HeadlinesListSection;

public class HeadlinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SectionContext context = new SectionContext(this);

        setContentView(
                LithoView.create(
                        context,
                        RecyclerCollectionComponent.create(context)
                                .marginDip(YogaEdge.ALL, 4)
                                .section(HeadlinesListSection.create(context).build())
                                .disablePTR(true)
                                .build()));
    }
}
