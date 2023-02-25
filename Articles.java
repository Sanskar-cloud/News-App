package com.example.newsapp;

public class Articles {
    String title;
    String url;
    String content;
    String description;
    String urlToImage;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public Articles(String title, String url, String content, String description, String urlToImage) {
        this.title = title;
        this.url = url;
        this.content = content;
        this.description = description;
        this.urlToImage = urlToImage;
    }
}
