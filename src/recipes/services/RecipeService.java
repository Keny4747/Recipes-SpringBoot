package recipes.services;

import org.springframework.stereotype.Service;
import recipes.model.Recipe;

@Service
public class RecipeService {
    Recipe recipe;

    public Recipe create(Recipe recipeRequest){
        recipe = recipeRequest;
        return recipe;
    }
    public Recipe get(){
        return recipe;
    }
}
