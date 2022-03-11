package ru.eremenko.promotion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author eremenko
 */
@Data
@AllArgsConstructor
public class Result {
    private Participant winner;
    private Prize prize;
}
