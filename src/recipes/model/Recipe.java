package recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Recipe {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @NotNull()
    @NotEmpty()
    private String name;

    @NotBlank
    @NotNull()
    @NotEmpty()
    private String category;

    @NotBlank
    @NotNull()
    @NotEmpty()
    private String description;

    @NotEmpty()
    @ElementCollection
    @Size(min = 1)
    private List<String> ingredients;

    @NotEmpty()
    @ElementCollection
    @Size(min = 1)
    private List<String> directions;

    private LocalDateTime date;

}
