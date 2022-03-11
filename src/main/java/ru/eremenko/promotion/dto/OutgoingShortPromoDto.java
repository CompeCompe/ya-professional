package ru.eremenko.promotion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author eremenko
 */
@Getter
@Setter
@AllArgsConstructor
public class OutgoingShortPromoDto {
    private long id;
    private String name;
    private String description;
}
