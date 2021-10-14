package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Report {

    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "short_description")
    private String short_description;

    @Column(name = "description")
    private String description;

    @Column(name = "repeat_steps")
    private String repeat_steps;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="priority_id")
    private Priority priority;

    public Report() {
    }

    public Report(Integer id, String short_description, String description, String repeat_steps, String author, Date date, Priority priority) {
        this.id = id;
        this.short_description = short_description;
        this.description = description;
        this.repeat_steps = repeat_steps;
        this.author = author;
        this.date = date;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepeat_steps() {
        return repeat_steps;
    }

    public void setRepeat_steps(String repeat_steps) {
        this.repeat_steps = repeat_steps;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
