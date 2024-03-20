package models;

import java.util.List;

public class Recipe {
    private String id;
    private String title;
    private String date;
    private List<Ingredient> ingredients;
    private List<String> preparationSteps;
    private String comment;
    private Nutrition nutrition;
    private String relatedRecipeId;

    // Constructeur
    public Recipe(String id, String title, String date, List<Ingredient> ingredients, List<String> preparationSteps,
                  String comment, Nutrition nutrition, String relatedRecipeId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.ingredients = ingredients;
        this.preparationSteps = preparationSteps;
        this.comment = comment;
        this.nutrition = nutrition;
        this.relatedRecipeId = relatedRecipeId;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getPreparationSteps() {
        return this.preparationSteps;
    }

    public void setPreparationSteps(List<String> preparationSteps) {
        this.preparationSteps = preparationSteps;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public String getRelatedRecipeId() {
        return relatedRecipeId;
    }

    public void setRelatedRecipeId(String relatedRecipeId) {
        this.relatedRecipeId = relatedRecipeId;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", ingredients=" + ingredients +
                ", preparationSteps=" + preparationSteps +
                ", comment='" + comment + '\'' +
                ", nutrition=" + nutrition +
                ", relatedRecipeId='" + relatedRecipeId + '\'' +
                '}';
    }
}
