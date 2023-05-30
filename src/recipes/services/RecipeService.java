package recipes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository repository;

    public Map<String, Object> create(Recipe recipeRequest){

        Map<String, Object> json = new HashMap<>();

        Recipe recipe = repository.save(recipeRequest);

        json.put("id",recipe.getId());
        return json;
    }
    public Recipe get(int id){
       return repository.findById(id).orElse(null);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }

}
