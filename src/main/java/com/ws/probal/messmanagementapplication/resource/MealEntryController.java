package com.ws.probal.messmanagementapplication.resource;


import com.ws.probal.messmanagementapplication.domain.entity.MealEntry;
import com.ws.probal.messmanagementapplication.domain.request.MealEntryRequest;
import com.ws.probal.messmanagementapplication.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meal")
public class MealEntryController {

    private final MealService mealService;

    @PostMapping
    public ResponseEntity<MealEntry> addMeal(@RequestBody MealEntryRequest request) {
        return ResponseEntity.ok(mealService.addMeal(request));
    }
}
