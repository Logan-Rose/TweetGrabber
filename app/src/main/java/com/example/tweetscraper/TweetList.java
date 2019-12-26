package com.example.tweetscraper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TweetList extends ArrayAdapter<Tweet> {
    private Activity context;
    List<Tweet> tweets;

    public TweetList(Activity context, List<Tweet> tweets) {
        super(context, R.layout.activity_tweet_list, tweets);
        this.context = context;
        this.tweets = tweets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_tweet_list, null, true);


        TextView textViewContent = /*(TextView)*/ listViewItem.findViewById(R.id.tweetContent);
        TextView textViewStats = /*(TextView)*/ listViewItem.findViewById(R.id.tweetStats);
        TextView textViewDate = /*(TextView)*/ listViewItem.findViewById(R.id.tweetDate);

        Tweet tweet = tweets.get(position);

        textViewContent.setText(tweet.getContent());
        textViewStats.setText(tweet.getStats());
        textViewDate.setText(tweet.getDate());
        return listViewItem;
    }
}
