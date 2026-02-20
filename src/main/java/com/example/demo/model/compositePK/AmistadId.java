package com.example.demo.model.compositePK;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AmistadId implements Serializable {
    @Column(name = "usuario1_id")
    private long usuario1id;
    @Column(name = "usuario2_id")
    private long usuario2id;
}
