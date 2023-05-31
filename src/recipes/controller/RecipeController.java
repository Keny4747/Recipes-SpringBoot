package recipes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.model.Recipe;
import recipes.services.RecipeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/recipe/new")
    public Map<String, Object> createRecipe(@Valid @RequestBody Recipe recipeRequest) {

        return recipeService.create(recipeRequest);
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.get(id);
        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.get(id);
        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        recipeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/recipe/{id}")
    public ResponseEntity<HttpStatus> updateRecipe( @PathVariable int id, @Valid @RequestBody Recipe recipeUpdate) {

        return recipeService.updateRecipe(id, recipeUpdate);
    }

    @GetMapping("/recipe/search/")
    public ResponseEntity<List<Recipe>> getRecipeByCategoryOrByName(@RequestParam(required = false) String category,
                                                    @RequestParam(required = false) String name) {

        if(category!=null && name!=null){
            return ResponseEntity.badRequest().build();
        }

        if(name !=null){
            return ResponseEntity.ok(recipeService.searchRecipeByName(name));
        }
        if(category!=null){
            return ResponseEntity.ok(recipeService.searchRecipeByCategory(category));
        }
        return ResponseEntity.badRequest().build();
    }
}
