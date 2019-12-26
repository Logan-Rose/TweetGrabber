package com.example.tweetscraper;
public class Tweet {
    String content;
    String replies;
    String retweets;
    String likes;
    String date;
    public Tweet(String text){
        String[] splitTweet = text.split(" replies");
        String[] parts = splitTweet[0].split(" ");
        this.content = splitTweet[0].substring(0, splitTweet[0].lastIndexOf(" ")).trim();
        this.replies = parts[parts.length - 1];
        this.retweets = splitTweet[1].split(" ")[1];
        this.likes = splitTweet[1].split(" ")[3];
        if(!splitTweet[1].split("likes")[1].trim().split(" ")[0].trim().equals("Reply") )
            this.date = splitTweet[1].split("likes")[1].trim();
    }

    @Override
    public String toString() {
        return "----------------------\n"+this.content + "\n"
                + this.replies + " replies, "
                + this.retweets + " retweets, "
                + this.likes + " likes\n"
                + this.date
                +"\n----------------------";
    }

    public String getContent(){
        return this.content;
    }
    public String getStats(){
        return this.replies + " replies, " + this.retweets + " retweets, " + this.likes + " likes";
    }
    public String getDate(){
        return this.date;
    }
}
