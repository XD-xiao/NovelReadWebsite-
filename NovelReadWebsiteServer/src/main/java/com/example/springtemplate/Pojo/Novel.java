package com.example.springtemplate.Pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Novel {
    private Integer id;
    private String cover;
    private String title;
    private String author;
    private String status;
    private LocalDateTime lastUpdated;
    private float rating;
    private int collect;
    private int watch;
    private String summary;
    private String tags;

    public Novel(Integer id, String cover, String title, String author, String status, LocalDateTime lastUpdated, float rating, int collect, int watch, String summary, String tags) {
        this.id = id;
        this.cover = cover;
        this.title = title;
        this.author = author;
        this.status = status;
        this.lastUpdated = lastUpdated;
        this.rating = rating;
        this.collect = collect;
        this.watch = watch;
        this.summary = summary;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", rating=" + rating +
                ", collect=" + collect +
                ", watch=" + watch +
                ", summary='" + summary + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
