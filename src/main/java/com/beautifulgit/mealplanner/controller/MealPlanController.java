package com.beautifulgit.mealplanner.controller;

import com.beautifulgit.mealplanner.dto.MealPlanCreateRequest;
import com.beautifulgit.mealplanner.dto.MealPlanResponse;
import com.beautifulgit.mealplanner.service.MealPlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanController {
    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealPlanResponse create(@Valid @RequestBody MealPlanCreateRequest request) {
        return mealPlanService.create(request);
    }

    @GetMapping
    public List<MealPlanResponse> findAll() {
        return mealPlanService.findAll();
    }

    @GetMapping("/by-date")
    public List<MealPlanResponse> findByDateRange(@RequestParam(required = false) LocalDate from,
                                                  @RequestParam(required = false) LocalDate to) {
        return mealPlanService.findByDateRange(from, to);
    }

    @GetMapping(value = "/export", produces = "text/csv")
    public String exportCsv(@RequestParam(required = false) LocalDate from,
                            @RequestParam(required = false) LocalDate to) {
        return mealPlanService.exportCsv(from, to);
    }
}
