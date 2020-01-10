package com.example.tweetscraper;
public class Tweet {
    String content;
    String replies;
    String retweets;
    String likes;
    String date;
    public Tweet(String text){
        System.out.println(text);
        String[] splitTweet = text.split(" replies");
        this.content = splitTweet[0].substring(0, splitTweet[0].lastIndexOf(" ")).trim();
    }

    @Override
    public String toString() {
        return this.content;
    }

    public String getContent(){
        return this.content;
    }
}
