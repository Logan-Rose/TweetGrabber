package com.example.tweetscraper;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText handleInput;
    private Button search;
    private List<Tweet> tweets;
    private ListView listViewTweets;
    private TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        handleInput = (EditText) findViewById(R.id.textBoxHandle);
        search = findViewById(R.id.search);
        listViewTweets = findViewById(R.id.listViewTweets);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String handle = handleInput.getText().toString();
                List<Tweet> tweets = scrapeUser(handle);
                TweetList tweetsAdapter = new TweetList(MainActivity.this, tweets);
                listViewTweets.setAdapter(tweetsAdapter);
            }
        });
    }

    public String[] remove(String[] text, String removal, String name){
        String[] temp;
        for(int i = 0; i < text.length; i++){
            temp = text[i].split(removal);
            if(temp.length >= 2) {
                text[i] = temp[0] + temp[1];
            }
        }
        return text;
    }


    public List<Tweet> scrapeUser(String userHandle){
        Document test =  null;
        List<Tweet> list = new ArrayList<>();
        userName = findViewById(R.id.textViewName);
        try {
            test = Jsoup.connect("http://www.twitter.com/" + userHandle).get();
            String title = test.title();
            String name = title.split("@")[0];
            name = name.substring(0, name.length()-2);
            System.out.println(name);
            Element timeline = test.select("div#timeline").first();
            String text = timeline.text();
            System.out.println("Tweet "+ name);
            userName.setText("showing tweets from " +name);
            String[] tweets = text.split("More Copy link to Tweet Embed Tweet");
            tweets = remove(tweets, "Reply Retweet Retweeted Like Liked Thanks. Twitter will use this to make your timeline better. Undo Undo " + name, name);
            tweets = remove(tweets, "Reply Retweet Retweeted Like Liked Show this thread Show this thread Thanks. Twitter will use this to make your timeline better. Undo Undo " + name, name);
            tweets = remove(tweets, "Verified account @" + userHandle + " ", name);
            String[] splitTweet;
            System.out.println("--------------------------------------");
            ArrayList<Tweet> tweetObjects = new ArrayList<Tweet>();
            for(int i = 1; i <tweets.length;i++){
                Tweet currentTweet = new Tweet(tweets[i]);
                tweetObjects.add(currentTweet);
                System.out.println(currentTweet);
                list.add(currentTweet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
