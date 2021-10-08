package com.example.PasteBinClone.paste;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Paste {

    public Paste(){}

        @Id
        @SequenceGenerator(
                name = "paste_sequence",
                sequenceName = "paste_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "paste_sequence"
        )
        private Long id;
        private String user;
        private String title;
        private String text;
        private LocalDateTime datecreated;

    public Paste(Long id,
                 String user,
                 String title,
                 String text,
                 LocalDateTime datecreated) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.text = text;
        this.datecreated = datecreated;
    }

    public Paste(String user,
                 String title,
                 String text,
                 LocalDateTime datecreated) {
        this.user = user;
        this.title = title;
        this.text = text;
        this.datecreated = datecreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(LocalDateTime datecreated) {
        this.datecreated = datecreated;
    }

    @Override
    public String toString() {
        return "Paste{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", datecreated=" + datecreated +
                '}';
    }
}
