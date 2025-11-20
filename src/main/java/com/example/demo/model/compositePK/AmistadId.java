package com.example.demo.model.compositePK;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AmistadId implements Serializable {
    private long usuario1id;
    private long usuario2id;
}
