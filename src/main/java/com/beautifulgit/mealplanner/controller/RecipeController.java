package com.beautifulgit.mealplanner.controller;

import com.beautifulgit.mealplanner.dto.RecipeCreateRequest;
import com.beautifulgit.mealplanner.dto.RecipeResponse;
import com.beautifulgit.mealplanner.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeResponse create(@Valid @RequestBody RecipeCreateRequest request) {
        return recipeService.create(request);
    }

    @GetMapping
    public List<RecipeResponse> findAll() {
        return recipeService.findAll();
    }

    @GetMapping("/{id}")
    public RecipeResponse findById(@PathVariable Long id) {
        return recipeService.findById(id);
    }
}
