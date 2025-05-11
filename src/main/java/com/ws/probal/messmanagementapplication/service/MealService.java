package com.ws.probal.messmanagementapplication.service;

import com.ws.probal.messmanagementapplication.domain.entity.MealEntry;
import com.ws.probal.messmanagementapplication.domain.entity.Member;
import com.ws.probal.messmanagementapplication.domain.request.MealEntryRequest;
import com.ws.probal.messmanagementapplication.repository.MealEntryRepository;
import com.ws.probal.messmanagementapplication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealEntryRepository mealRepo;

    private final MemberRepository memberRepo;

    public MealEntry addMeal(MealEntryRequest mealEntryRequest) {
        Member member = memberRepo.findById(mealEntryRequest.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        MealEntry meal = new MealEntry(null, mealEntryRequest.getDate(), mealEntryRequest.getMealCount(), member);
        return mealRepo.save(meal);
    }
}
