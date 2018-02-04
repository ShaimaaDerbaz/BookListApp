package com.example.shaimaaderbaz.booklist.models;

/**
 * Created by Shaimaa Derbaz on 2/3/2018.
 */

public class Book {
    private String title;
    private String author;
    private String publisher;
    private String description;
    private String thumbnail;


    public Book(String title, String author, String publisher, String description, String thumbnail) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
