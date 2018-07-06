package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    Tweet tweet;

    ImageView ivProfileImage;
    TextView tvUsername;
    TextView tvBody;
    TextView tvRelativeTime;
    TextView tvScreenname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // resolve the view objects
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvBody = (TextView) findViewById(R.id.tvBody);
        tvRelativeTime = (TextView) findViewById(R.id.tvRelativeTime);
        tvScreenname = (TextView) findViewById(R.id.tvScreenname);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        tvUsername.setText(tweet.user.name);
        tvBody.setText(tweet.body);
        tvScreenname.setText("@" + tweet.user.screenName);
        tvRelativeTime.setText(getRelativeTimeAgo(tweet.createdAt));
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileImage);
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";

        try {

            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            String[] dateInfo = relativeDate.split(" ");
            String amountTime = dateInfo[0];
            String periodTime = dateInfo[1];

            if (periodTime.contains("min")) relativeDate = amountTime + "m";
            else if (periodTime.contains("hour")) relativeDate = amountTime + "h";
            else if (periodTime.contains("year")) relativeDate = amountTime + "y";
            else if (periodTime.contains("second")) relativeDate = amountTime + "s";
            else relativeDate = "0s";

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
