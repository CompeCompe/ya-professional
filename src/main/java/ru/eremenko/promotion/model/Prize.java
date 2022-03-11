package ru.eremenko.promotion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author eremenko
 */
@Data
public class Prize {
    @JsonIgnore
    private static long ID = 0;

    private long id;
    private String description;

    public Prize() {
        this.id = ID++;
    }

    public Prize(String description) {
        this();
        this.description = description;
    }
}
