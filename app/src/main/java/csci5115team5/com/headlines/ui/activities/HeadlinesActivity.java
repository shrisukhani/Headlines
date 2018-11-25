package csci5115team5.com.headlines.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.Text;

import csci5115team5.com.headlines.HeadlinesListItem;
import csci5115team5.com.headlines.R;

public class HeadlinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_headlines);
        ComponentContext context = new ComponentContext(this);
        setContentView(LithoView.create(context,
//                Text.create(context)
//                .text("Shri")
//                .textSizeSp(22).build()));
                HeadlinesListItem.create(new ComponentContext(this))
                        .bigImgDrawable(R.drawable.shri)
                        .title("Big Title")
                        .shortSummary("This is a super short summary about the article we're looking at")
                        .publicationLogoDrawable(R.drawable.shri)
                        .dayDateTime("Tue, 11-27-2018\n03:49 PM")
                        .publicationName("The New York Times")
                        .build()));
    }
}
