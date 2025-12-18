package com.example.demo.model.compositePK;

import jakarta.persistence.Embeddable;


import java.io.Serializable;

@Embeddable
public class AmistadId implements Serializable {
    private long usuario1id;
    private long usuario2id;
}
