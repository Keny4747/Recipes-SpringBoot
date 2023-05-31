package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findRecipeByNameContainingIgnoreCaseOrderByDateDesc(String name);

    List<Recipe> findRecipeByCategoryIgnoreCaseOrderByDateDesc(String category);


}
