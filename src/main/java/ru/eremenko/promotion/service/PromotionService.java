package ru.eremenko.promotion.service;


import org.springframework.stereotype.Service;
import ru.eremenko.promotion.dto.IngoingAddParticipantDto;
import ru.eremenko.promotion.dto.IngoingAddPrizeDto;
import ru.eremenko.promotion.dto.IngoingPromoDto;
import ru.eremenko.promotion.dto.OutgoingShortPromoDto;
import ru.eremenko.promotion.model.Participant;
import ru.eremenko.promotion.model.Prize;
import ru.eremenko.promotion.model.Promotion;
import ru.eremenko.promotion.model.Result;
import ru.eremenko.promotion.repo.PromotionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eremenko
 */
@Service
public class PromotionService {

    private final PromotionRepo promotionRepo;

    public PromotionService(PromotionRepo promotionRepo) {
        this.promotionRepo = promotionRepo;
    }

    public long addPromo(IngoingPromoDto promoDto) {
        Promotion promotion = new Promotion();
        promotion.setName(promoDto.getName());
        promotion.setDescription(promoDto.getDescription());

        promotionRepo.addPromo(promotion);

        return promotion.getId();
    }

    public List<OutgoingShortPromoDto> getShortPromoDtoList() {
       return promotionRepo.getPromotionList().stream().map(promo -> new OutgoingShortPromoDto(promo.getId(), promo.getName(), promo.getDescription())).collect(Collectors.toList());
    }

    public Promotion getPromoById(long id) {
        return promotionRepo.getPromoById(id);
    }

    public boolean updatePromo(long id, IngoingPromoDto promoDto) {
        if (promoDto.getName() == null || promoDto.getName().isEmpty()) {
            return false;
        }
        List<Promotion> promotionList = promotionRepo.getPromotionList();
        promotionList.stream().filter(promo -> promo.getId() == id)
                .forEach(promotion -> {
                    promotion.setName(promoDto.getName());
                    promotion.setDescription(promoDto.getDescription() == null ? promotion.getDescription() : promoDto.getDescription());
                });
        promotionRepo.updatePromotionList(promotionList);
        return true;
    }

    public void deletePromo(long id) {
        promotionRepo.deletePromoById(id);
    }

    public long addParticipant(long promoId, IngoingAddParticipantDto participantDto) {
        List<Promotion> promotionList = promotionRepo.getPromotionList();
        Participant participant = new Participant(participantDto.getName());
        promotionList.stream().filter(promo -> promo.getId() == promoId)
                .forEach(promo -> promo.getParticipants().add(participant));
        promotionRepo.updatePromotionList(promotionList);

        return participant.getId();
    }

    public void deleteParticipant(long promoId, long participantId) {
        promotionRepo.deleteParticipantById(promoId, participantId);
    }

    public long addPrize(long promoId, IngoingAddPrizeDto prizeDto) {
        List<Promotion> promotionList = promotionRepo.getPromotionList();
        Prize prize = new Prize(prizeDto.getDescription());
        promotionList.stream().filter(promo -> promo.getId() == promoId)
                .forEach(promo -> promo.getPrizes().add(prize));
        promotionRepo.updatePromotionList(promotionList);

        return prize.getId();
    }

    public void deletePrize(long promoId, long prizeId) {
        promotionRepo.deletePrizeById(promoId, prizeId);
    }

    public List<Result> startRaffle(long promoId) {
        Promotion promotion = promotionRepo.getPromoById(promoId);
        List<Participant> participants = promotion.getParticipants();
        List<Prize> prizes = promotion.getPrizes();

        List<Result> results = new ArrayList<>();
        if (participants.size() == prizes.size()) {
            for (int i = 0; i < participants.size(); i++) {
                results.add(new Result(participants.get(i), prizes.get(i)));
            }
        }

        return results;
    }
}
