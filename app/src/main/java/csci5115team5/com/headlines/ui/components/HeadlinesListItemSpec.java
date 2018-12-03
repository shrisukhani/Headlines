package csci5115team5.com.headlines.ui.components;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.widget.ImageView;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

import csci5115team5.com.headlines.R;

@LayoutSpec
public class HeadlinesListItemSpec {

    @OnCreateLayout
    static Component onCreateLayout(
            ComponentContext c,
            @Prop String title,
            @Prop String description,
            @Prop String publicationName,
            @Prop String dayDateTime,
            @Prop int bigImgDrawable) {
        return Card.create(c)
                .marginDip(YogaEdge.HORIZONTAL, 2)
                .content(
                        Column.create(c)
                                .backgroundColor(0xffE8E8E8)
                                .child(genTitleBuilder(c, title))
                                .child(genStoryImgBuilder(c, bigImgDrawable))
                                .child(genPubNameDayDateTimeBuilder(c, publicationName, dayDateTime))
                                .child(genDescriptionBuilder(c, description)))
                .elevationDip(6)
                .cornerRadiusDip(4)
                .build();
    }

    private static Image.Builder genStoryImgBuilder(
            ComponentContext c, int storyImgDrawable) {
        return Image.create(c)
                .drawableRes(storyImgDrawable)
                .widthPercent(100f)
                .heightDip(200)
                .marginDip(YogaEdge.LEFT, 2)
                .scaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private static Text.Builder genPubNameDayDateTimeBuilder(
            ComponentContext c, String publicationName, String dayDateTime) {
        return Text.create(c)
                .marginDip(YogaEdge.LEFT, 8)
                .marginDip(YogaEdge.VERTICAL, 8)
                .textSizeSp(12.5f)
                .textStyle(Typeface.BOLD_ITALIC)
                .text(publicationName + ", " + dayDateTime)
                .textColor(0xff5b6362);
    }

    private static Text.Builder genTitleBuilder(ComponentContext c, String title) {
        return Text.create(c, 0, R.style.TextAppearance_AppCompat_Title)
                .text(title)
                .textColor(0xff000000)
                .marginDip(YogaEdge.TOP, 16)
                .marginDip(YogaEdge.BOTTOM, 8)
                .marginDip(YogaEdge.HORIZONTAL, 8)
                .typeface(Typeface.DEFAULT_BOLD);
    }

    private static Text.Builder genDescriptionBuilder(ComponentContext c, String description) {
        return Text.create(c)
                .text(description)
                .maxLines(4)
                .ellipsize(TextUtils.TruncateAt.END)
                .textSizeSp(17)
                .paddingDip(YogaEdge.BOTTOM, 8)
                .marginDip(YogaEdge.HORIZONTAL, 8);
    }
}