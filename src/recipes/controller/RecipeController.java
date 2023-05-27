package recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import recipes.model.Recipe;
import recipes.services.RecipeService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecipeController {


    private final RecipeService recipeService;


    @PostMapping("/recipe")
    public Recipe createRecipe(@RequestBody Recipe recipeRequest){
        return recipeService.create(recipeRequest);
    }
    @GetMapping("/recipe")
    public Recipe getRecipe(){
        return recipeService.get();
    }
}
