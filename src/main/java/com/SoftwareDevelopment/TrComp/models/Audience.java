package com.SoftwareDevelopment.TrComp.models;

import javax.persistence.*;

@Entity
public class Audience {

    @Id
    @Column(name = "audience_code")
    public String audienceCode;

    @Column(name = "is_with_projector")
    public boolean isWithProjector;

    @Column(name = "seats_count")
    public int seatsCount;

    public Audience() {}
}
