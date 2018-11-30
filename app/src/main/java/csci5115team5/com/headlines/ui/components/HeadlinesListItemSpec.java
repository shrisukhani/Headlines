package csci5115team5.com.headlines.ui.components;

import android.graphics.Typeface;
import android.widget.ImageView;

import com.facebook.litho.Border;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaWrap;

import csci5115team5.com.headlines.R;

@LayoutSpec
public class HeadlinesListItemSpec {

    @OnCreateLayout
    static Component onCreateLayout(
            ComponentContext c,
            @Prop String title,
            @Prop String publicationName,
            @Prop String dayDateTime,
            @Prop int bigImgDrawable,
            @Prop int publicationLogoDrawable) {
        return Card.create(c)
                .marginDip(YogaEdge.HORIZONTAL, 2)
                .content(
                        Row.create(c)
                                .backgroundColor(0xffE8E8E8)
                                .child(
                                        Column.create(c)
                                                .heightDip(100)
                                                .flexGrow(1)
                                                .flexShrink(1)
                                                .wrap(YogaWrap.NO_WRAP)
                                                .child(genTitleBuilder(c, title))
                                                .child(
                                                        Row.create(c)
                                                                .justifyContent(YogaJustify.SPACE_EVENLY)
                                                                .marginDip(YogaEdge.TOP, 1)
                                                                .child(genPubImgBuilder(c, publicationLogoDrawable))
                                                                .child(genPubNameDayDateTimeBuilder(c, publicationName, dayDateTime))))
                                .child(genStoryImgBuilder(c, bigImgDrawable))
                                .border(genBorder(c, c.getColor(R.color.colorPrimary))))
                .elevationDip(3)
                .build();
    }

    private static Image.Builder genStoryImgBuilder(ComponentContext c, int storyImgDrawable) {
        return Image.create(c)
                .drawableRes(storyImgDrawable)
                .widthDip(100)
                .heightDip(100)
                .marginDip(YogaEdge.LEFT, 2)
                .scaleType(ImageView.ScaleType.CENTER_CROP)
                .flexShrink(0)
                .flexGrow(0);
    }

    private static Text.Builder genPubNameDayDateTimeBuilder(
            ComponentContext c, String publicationName, String dayDateTime) {
        return Text.create(c)
                .marginDip(YogaEdge.LEFT, 4)
                .textSizeSp(12.5f)
                .text(publicationName + "\n" + dayDateTime)
                .typeface(Typeface.SERIF)
                .flexGrow(1)
                .flexShrink(1)
                .textColor(0xff5b6362);
    }

    private static Image.Builder genPubImgBuilder(ComponentContext c, int logoDrawable) {
        return Image.create(c)
                .heightDip(32)
                .scaleType(ImageView.ScaleType.CENTER_CROP)
                .drawableRes(logoDrawable)
                .widthDip(32)
                .flexGrow(0)
                .flexShrink(1);
    }

    private static Border genBorder(ComponentContext c, int color) {
        return Border.create(c)
                .radiusDip(2)
                .widthDip(YogaEdge.ALL, 2)
                .color(YogaEdge.ALL, color)
                .build();
    }

    private static String maybeTruncateTitle(String title) {
        title = title.replace('\n', ' ');
        return (title.length() > 96)
                ? title.substring(0, 96) + "..."
                : title;
    }

    private static Text.Builder genTitleBuilder(ComponentContext c, String title) {
        return Text.create(c)
                .heightDip(68)
                .text(maybeTruncateTitle(title))
                .textSizeSp(18f)
                .flexGrow(1)
                .flexShrink(1);
    }
}
