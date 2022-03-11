package ru.eremenko.promotion.dto;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author eremenko
 */
@Getter
public class IngoingPromoDto {
    @NotNull
    private String name;
    private String description;
}
