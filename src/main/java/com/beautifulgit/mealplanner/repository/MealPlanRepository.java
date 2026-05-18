package com.beautifulgit.mealplanner.repository;

import com.beautifulgit.mealplanner.entity.MealPlanEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealPlanRepository extends JpaRepository<MealPlanEntryEntity, Long> {
    List<MealPlanEntryEntity> findByDateBetween(LocalDate from, LocalDate to);

    List<MealPlanEntryEntity> findByDateGreaterThanEqualAndDateLessThanEqual(LocalDate from, LocalDate to);
}
