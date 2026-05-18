package com.beautifulgit.mealplanner.service;

import com.beautifulgit.mealplanner.dto.MealPlanCreateRequest;
import com.beautifulgit.mealplanner.dto.MealPlanResponse;
import com.beautifulgit.mealplanner.entity.MealPlanEntryEntity;
import com.beautifulgit.mealplanner.exception.NotFoundException;
import com.beautifulgit.mealplanner.model.MealPlanEntry;
import com.beautifulgit.mealplanner.repository.MealPlanRepository;
import com.beautifulgit.mealplanner.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealPlanService {
    private final MealPlanRepository mealPlanRepository;
    private final RecipeRepository recipeRepository;

    public MealPlanService(MealPlanRepository mealPlanRepository, RecipeRepository recipeRepository) {
        this.mealPlanRepository = mealPlanRepository;
        this.recipeRepository = recipeRepository;
    }

    public MealPlanResponse create(MealPlanCreateRequest request) {
        recipeRepository.findById(request.recipeId())
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + request.recipeId()));
        MealPlanEntryEntity saved = mealPlanRepository.save(new MealPlanEntryEntity(
                request.date(),
                request.mealType(),
                request.recipeId(),
                request.note()
        ));
        return toResponse(saved);
    }

    public List<MealPlanResponse> findAll() {
        return mealPlanRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<MealPlanResponse> findByDateRange(LocalDate from, LocalDate to) {
        if (from == null && to == null) {
            return findAll();
        }
        if (from == null) {
            from = to;
        }
        if (to == null) {
            to = from;
        }
        return mealPlanRepository.findByDateGreaterThanEqualAndDateLessThanEqual(from, to).stream().map(this::toResponse).toList();
    }

    public String exportCsv(LocalDate from, LocalDate to) {
        List<MealPlanResponse> items = findByDateRange(from, to);
        StringBuilder csv = new StringBuilder("id,date,mealType,recipeId,note\n");
        for (MealPlanResponse item : items) {
            csv.append(item.id()).append(',')
                    .append(item.date()).append(',')
                    .append(item.mealType()).append(',')
                    .append(item.recipeId()).append(',')
                    .append(escape(item.note()))
                    .append('\n');
        }
        return csv.toString();
    }

    private MealPlanResponse toResponse(MealPlanEntryEntity entry) {
        return new MealPlanResponse(entry.getId(), entry.getDate(), entry.getMealType(), entry.getRecipeId(), entry.getNote());
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return '"' + value.replace("\"", "\"\"") + '"';
    }
}
