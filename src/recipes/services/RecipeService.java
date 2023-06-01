package recipes.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import recipes.exceptions.AuthorNotFoundException;
import recipes.exceptions.RecipeNotFoundException;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;
import recipes.security.UserInfoUserDetails;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository repository;

    public Map<String, Object> create(Recipe recipeRequest) {

        Map<String, Object> json = new HashMap<>();

        recipeRequest.setDate(LocalDateTime.now());
        repository.save(recipeRequest);

        json.put("id", recipeRequest.getId());
        return json;
    }

    public Recipe get(int id) {
        return repository.findById(id).orElse(null);
    }

    public ResponseEntity<HttpStatus> updateRecipe(int id, Recipe recipeUpdate, UserInfoUserDetails userDetails) {
        Recipe recipe = repository.findById(id).orElse(null);

        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(!recipe.getEmail().equals(userDetails.getUsername())){
            throw new AuthorNotFoundException();
        }

        recipe.setDate(LocalDateTime.now());
        recipe.setName(recipeUpdate.getName());
        recipe.setCategory(recipeUpdate.getCategory());
        recipe.setDescription(recipeUpdate.getDescription());
        recipe.setIngredients(recipeUpdate.getIngredients());
        recipe.setDirections(recipeUpdate.getDirections());
        repository.save(recipe);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    public List<Recipe> searchRecipeByName(String name) {
        return repository.findRecipeByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public List<Recipe> searchRecipeByCategory(String category) {
        return repository.findRecipeByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public void deleteById(int id,UserInfoUserDetails userDetails) {
        Optional<Recipe> recipe = repository.findRecipeById(id);
        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException();
        }

        if(!recipe.get().getEmail().equals(userDetails.getUsername())){
            throw new AuthorNotFoundException();
        }

        repository.deleteById(id);
    }


}
