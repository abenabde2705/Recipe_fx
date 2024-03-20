package Presentation;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class RecipeDetailsController {

    @FXML
    private Label titleLabel;

    @FXML
    private TextArea ingredientsTextArea;

    @FXML
    private TextArea preparationStepsTextArea;



    public void setRecipeDetails(String recipeTitle, List<String> ingredients, List<String> preparationSteps) {
        setRecipeTitle(recipeTitle);
        setIngredients(ingredients);
        setPreparationSteps(preparationSteps);
    }

    public void setRecipeTitle(String title) {
        titleLabel.setText(title);
    }

    public void setIngredients(List<String> ingredients) {
        StringBuilder ingredientsText = new StringBuilder();
        for (String ingredient : ingredients) {
            ingredientsText.append(ingredient).append("\n");
        }
        ingredientsTextArea.setText(ingredientsText.toString());
    }
    private void setPreparationSteps(List<String> preparationSteps) {
        StringBuilder stepsText = new StringBuilder();
        for (int i = 0; i < preparationSteps.size(); i++) {
            String step = preparationSteps.get(i);
            stepsText.append((i + 1)).append(". ").append(step).append("\n");
        }
        preparationStepsTextArea.setText(stepsText.toString());
    }

    @FXML
    private void onCloseButtonClick() {
        // Fermez la fenêtre de détails
        titleLabel.getScene().getWindow().hide();
    }
}
