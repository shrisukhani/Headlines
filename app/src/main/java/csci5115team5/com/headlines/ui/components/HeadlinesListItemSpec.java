package csci5115team5.com.headlines.ui.components;

import android.graphics.Color;
import android.widget.ImageView;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

@LayoutSpec
public class HeadlinesListItemSpec {

    @OnCreateLayout
    static Component onCreateLayout(
            ComponentContext c,
            @Prop String title,
            @Prop String shortSummary,
            @Prop String publicationName,
            @Prop String dayDateTime,
            @Prop int bigImgDrawable,
            @Prop int publicationLogoDrawable) {
        // @TODO: Implement this method
        return null;
    }
}
