package ru.eremenko.promotion.repo;

import org.springframework.stereotype.Component;
import ru.eremenko.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eremenko
 */
@Component
public class PromotionRepo {
    private List<Promotion> promotionList = new ArrayList<>();

    public void addPromo(Promotion promotion) {
        promotionList.add(promotion);
    }

    public List<Promotion> getPromotionList() {
        return new ArrayList<>(promotionList);
    }

    public Promotion getPromoById(long id) {
       return getPromotionList().stream().filter(promo -> promo.getId() == id).findFirst().orElse(null);
    }

    public void updatePromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public void deletePromoById(long id) {
        updatePromotionList(getPromotionList().stream().filter(promo -> promo.getId() != id).collect(Collectors.toList()));
    }

    public void deleteParticipantById(long promoId, long participantId) {
        List<Promotion> promoList = getPromotionList();
        promoList.stream()
                .filter(promo -> promo.getId() == promoId)
                .forEach(promotion -> {
                    promotion.setParticipants(promotion.getParticipants().stream().filter(participant -> participant.getId() != participantId).collect(Collectors.toList()));
                });

        updatePromotionList(promoList);
    }

    public void deletePrizeById(long promoId, long prizeId) {
        List<Promotion> promoList = getPromotionList();
        promoList.stream()
                .filter(promo -> promo.getId() == promoId)
                .forEach(promotion -> {
                    promotion.setPrizes(promotion.getPrizes().stream().filter(prize -> prize.getId() != prizeId).collect(Collectors.toList()));
                });

        updatePromotionList(promoList);
    }
}
