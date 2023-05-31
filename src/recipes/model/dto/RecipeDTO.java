package recipes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private String name;
    private String category;
    private String description;
    private List<String> ingredients;
    private List<String> directions;
}
