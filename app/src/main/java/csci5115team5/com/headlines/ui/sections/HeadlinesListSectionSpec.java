package csci5115team5.com.headlines.ui.sections;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.yoga.YogaEdge;

import csci5115team5.com.headlines.R;
import csci5115team5.com.headlines.ui.components.HeadlinesListItem;

@GroupSectionSpec
public class HeadlinesListSectionSpec {

    @OnCreateChildren
    static Children onCreateChildren(SectionContext c) {
        Children.Builder builder = Children.create();
        for (int i = 0; i < 3; i ++) {
            builder.child(
                    SingleComponentSection.create(c)
                            .component(HeadlinesListItem.create(new ComponentContext(c))
                                    .bigImgDrawable(R.drawable.shri)
                                    .title("Ford Motor Co. to shift jobs from Louisville Assembly Plant to Kentucky Truck Plant - WDRB")
                                    .publicationLogoDrawable(R.drawable.shri)
                                    .dayDateTime("Tue, 11-27-2018 | 03:49 PM")
                                    .publicationName("The New York Times")));
        }
        return builder.build();
    }
}
