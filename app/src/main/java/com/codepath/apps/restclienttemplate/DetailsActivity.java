package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

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
        tvRelativeTime.setText(tweet.createdAt);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileImage);
    }

    public void goBack(View v){
        startActivity(new Intent(DetailsActivity.this, TimelineActivity.class));
    }
}
