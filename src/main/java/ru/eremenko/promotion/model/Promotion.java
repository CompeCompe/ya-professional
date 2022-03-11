package ru.eremenko.promotion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eremenko
 */
@Data
public class Promotion {
    @JsonIgnore
    private static long ID = 0;

    private long id;
    private String name;
    private String description;
    private List<Prize> prizes = new ArrayList<Prize>();
    private List<Participant> participants = new ArrayList<Participant>();


    public Promotion() {
        this.id = ID++;
    }
}
