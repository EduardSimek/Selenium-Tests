package models;

import enumerators.SinType;

import java.util.List;

public class Sin {
    private String title;
    private String author;
    private String message;
    private List<SinType> tags;

    public Sin(String title, String author, String message){
        this.title = title;
        this.author = author;
        this.message = message;
    }

    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getMessage() {return message;}

    public void setTitle(String title) {this.title = title;}

    public void setAuthor(String author) {this.author = author;}

    public void setMessage(String message) {this.message = message;}

    public List<SinType> getTags() {return tags;}

    public void setTags(List<SinType> tags) {this.tags = tags;}

}

