package ru.eremenko.promotion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author eremenko
 */
@Data
public class Participant {
    @JsonIgnore
    private static long ID = 0;

    private long id;
    private String name;

    public Participant() {
        this.id = ID++;
    }

    public Participant(String name) {
        this();
        this.name = name;
    }
}
