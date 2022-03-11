package ru.eremenko.promotion.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eremenko.promotion.dto.IngoingAddParticipantDto;
import ru.eremenko.promotion.dto.IngoingAddPrizeDto;
import ru.eremenko.promotion.dto.IngoingPromoDto;
import ru.eremenko.promotion.model.Promotion;
import ru.eremenko.promotion.model.Result;
import ru.eremenko.promotion.service.PromotionService;

import java.util.List;

/**
 * @author eremenko
 */
@RestController
@RequestMapping("/promo")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;
    
    @Operation(summary = "Add new promotion")
    @PostMapping
    public ResponseEntity<?> addPromo(@RequestBody IngoingPromoDto promo) {
        if (promo.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        long newPromoId = promotionService.addPromo(promo);
        return new ResponseEntity<>(newPromoId, HttpStatus.OK);
    }

    @Operation(summary = "Get promotion list")
    @GetMapping
    public ResponseEntity<?> getPromoList() {
        return new ResponseEntity<>(promotionService.getShortPromoDtoList(), HttpStatus.OK);
    }

    @Operation(summary = "Get promotion by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPromo(@PathVariable("id") long promoId) {
        Promotion promotion = promotionService.getPromoById(promoId);
        if (promotion == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(promotion, HttpStatus.OK);
        }
    }

    @Operation(summary = "Update promotion")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePromo(@PathVariable("id") long promoId, @RequestBody IngoingPromoDto promo) {

        if (promotionService.updatePromo(promoId, promo)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete promotion by id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePromo(@PathVariable("id") long promoId) {
        promotionService.deletePromo(promoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Add new participant to promotion by id")
    @PostMapping(value = "/{id}/participant")
    public ResponseEntity<?> addParticipant(@PathVariable("id") long promoId, @RequestBody IngoingAddParticipantDto participantDto) {
        promotionService.addParticipant(promoId, participantDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete participant by promotion id and participant id")
    @DeleteMapping(value = "/{promoId}/participant/{participantId}")
    public ResponseEntity<?> deleteParticipant(@PathVariable("promoId") long promoId, @PathVariable("participantId") long participantId) {
        promotionService.deleteParticipant(promoId, participantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Add new prize to promotion by id")
    @PostMapping(value = "/{id}/prize")
    public ResponseEntity<?> addPrize(@PathVariable("id") long promoId, @RequestBody IngoingAddPrizeDto prizeDto) {
        promotionService.addPrize(promoId, prizeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete participant by promotion id and prize id")
    @DeleteMapping(value = "/{promoId}/prize/{prizeId}")
    public ResponseEntity<?> deletePrize(@PathVariable("promoId") long promoId, @PathVariable("prizeId") long prizeId) {
        promotionService.deletePrize(promoId, prizeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Start raffle")
    @PostMapping(value = "/{id}/raffle")
    public ResponseEntity<?> startRaffle(@PathVariable("id") long promoId) {
        List<Result> results = promotionService.startRaffle(promoId);
        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
    }
}
