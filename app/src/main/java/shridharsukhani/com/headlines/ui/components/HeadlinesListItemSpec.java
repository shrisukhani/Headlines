package shridharsukhani.com.headlines.ui.components;

import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.fresco.FrescoImage;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

import shridharsukhani.com.headlines.R;

import static com.facebook.drawee.drawable.ScalingUtils.ScaleType.CENTER_CROP;

@LayoutSpec
public class HeadlinesListItemSpec {

    @OnCreateLayout
    static Component onCreateLayout(
            ComponentContext c,
            @Prop String title,
            @Prop String description,
            @Prop String publicationName,
            @Prop String dayDateTime,
            @Prop Uri storyImageUri) {
        return Card.create(c)
                .marginDip(YogaEdge.HORIZONTAL, 2)
                .content(
                        Column.create(c)
                                .backgroundColor(0xffE8E8E8)
                                .child(genTitleBuilder(c, title))
                                .child(genStoryImgBuilder(c, storyImageUri))
                                .child(genPubNameDayDateTimeBuilder(c, publicationName, dayDateTime))
                                .child(genDescriptionBuilder(c, description)))
                .elevationDip(6)
                .cornerRadiusDip(4)
                .build();
    }

    private static FrescoImage.Builder genStoryImgBuilder(
            ComponentContext c, Uri storyImageUri) {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(storyImageUri)
                .build();
        return FrescoImage.create(c)
                .placeholderImage(
                        c.getAndroidContext().getDrawable(R.drawable.story_img_placeholder))
                .controller(controller)
                .widthPercent(100f)
                .heightDip(200)
                .marginDip(YogaEdge.LEFT, 2)
                .actualImageScaleType(CENTER_CROP);
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

    private static Text.Builder genDescriptionBuilder(
            ComponentContext c, String description) {
        return Text.create(c)
                .text(description)
                .maxLines(4)
                .ellipsize(TextUtils.TruncateAt.END)
                .textSizeSp(17)
                .paddingDip(YogaEdge.BOTTOM, 8)
                .marginDip(YogaEdge.HORIZONTAL, 8);
    }
}