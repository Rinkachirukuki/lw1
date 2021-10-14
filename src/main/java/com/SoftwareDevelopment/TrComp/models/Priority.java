package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Priority {
    @Id
    @Column(name = "priority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priority",
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    private List<Report> reports;

    public Priority() {
    }

    public Priority(int id, String name, List<Report> reports) {
        this.id = id;
        this.name = name;
        this.reports = reports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PreRemove
    private void preRemove() {
        for (Report s : reports) {
            s.setPriority(null);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
