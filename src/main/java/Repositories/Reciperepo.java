package Repositories;
import models.Recipe;
import models.Ingredient;
import models.Nutrition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Reciperepo {

    private List<Recipe> recipes;

    public Reciperepo() {
        this.recipes = new ArrayList<>();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void init(String filePath) {
        //Parsing
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList recipeNodes = doc.getElementsByTagName("rcp:recipe");
            for (int i = 0; i < recipeNodes.getLength(); i++) {
                Element recipeElement = (Element) recipeNodes.item(i);

                String id = recipeElement.getAttribute("id");
                String title = getTagValue(recipeElement, "rcp:title");
                String date = getTagValue(recipeElement, "rcp:date");
                List<Ingredient> ingredients = parseIngredients(recipeElement.getElementsByTagName("rcp:ingredient"));
                List<String> preparationSteps = parsePreparationSteps(recipeElement.getElementsByTagName("rcp:preparation")); // Modification ici

                String comment = getTagValue(recipeElement, "rcp:comment");
                Nutrition nutrition = parseNutrition(recipeElement.getElementsByTagName("rcp:nutrition"));
                String related = getTagValue(recipeElement, "rcp:related");

                Recipe recipe = new Recipe(id, title, date, ingredients, preparationSteps, comment, nutrition, related);
                recipes.add(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0 && nodeList.item(0).hasChildNodes()) {
            return nodeList.item(0).getChildNodes().item(0).getNodeValue();
        } else {
            return ""; // Ou une autre valeur par défaut si nécessaire
        }
    }
    private List<String> parsePreparationSteps(NodeList preparationNodes) {
        List<String> preparationSteps = new ArrayList<>();
        for (int i = 0; i < preparationNodes.getLength(); i++) {
            Element preparationElement = (Element) preparationNodes.item(i);
            NodeList stepNodes = preparationElement.getElementsByTagName("rcp:step");
            for (int j = 0; j < stepNodes.getLength(); j++) {
                Element stepElement = (Element) stepNodes.item(j);
                String step = stepElement.getTextContent().trim();
                preparationSteps.add(step);
            }
        }
        return preparationSteps;
    }
    private List<Ingredient> parseIngredients(NodeList ingredientNodes) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientNodes.getLength(); i++) {
            Element ingredientElement = (Element) ingredientNodes.item(i);
            String name = ingredientElement.getAttribute("name");
            String amountStr = ingredientElement.getAttribute("amount");
            double amount;
            if (amountStr.equals("*")) {
                amount = 0.0; // Valeur par défaut
            } else {
                try {
                    amount = Double.parseDouble(amountStr);
                } catch (NumberFormatException e) {
                    amount = 0.0; // Valeur par défaut en cas d'erreur de conversion
                }
            }
            String unit = ingredientElement.getAttribute("unit");
            Ingredient ingredient = new Ingredient(name, amount, unit);
            ingredients.add(ingredient);
        }
        return ingredients;
    }



    private Nutrition parseNutrition(NodeList nutritionNodes) {
        Element nutritionElement = (Element) nutritionNodes.item(0);
        int calories = Integer.parseInt(nutritionElement.getAttribute("calories"));
        String fat = nutritionElement.getAttribute("fat");
        String carbohydrates = nutritionElement.getAttribute("carbohydrates");
        String protein = nutritionElement.getAttribute("protein");
        String alcohol = nutritionElement.getAttribute("alcohol");

        return new Nutrition(calories, fat, carbohydrates, protein,alcohol);
    }
    public List<String> getIngredients(String recipeName) {
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equalsIgnoreCase(recipeName)) {
                List<String> ingredientsList = new ArrayList<>();
                for (Ingredient ingredient : recipe.getIngredients()) {
                    // Concaténer le nom de l'ingrédient avec sa quantité
                    String ingredientDetails = "* " + ingredient.getName() + " - " + ingredient.getAmount() + ingredient.getUnit();
                    ingredientsList.add(ingredientDetails);
                }
                return ingredientsList;
            }
        }
        return Collections.emptyList(); // Retourne une liste vide si aucune recette ne correspond au nom donné
    }
    public List<String> getPreparationSteps(String recipeName) {
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equalsIgnoreCase(recipeName)) {
                List<String> stepsList = new ArrayList<>();
                for (String step : recipe.getPreparationSteps()) {
                    stepsList.add(step);
                }
                return stepsList;
            }
        }
        return Collections.emptyList(); // Retourne une liste vide si aucune recette ne correspond au nom donné
    }


    /** LES METHODES :*/
    public List<String> getRecipeTitles() {
        return recipes.stream()
                .map(Recipe::getTitle)
                .collect(Collectors.toList());
    }
    public int nombreoeufstotal(){
        return recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .filter(ing->ing.getName().toLowerCase().contains("egg"))
                .mapToInt(ingredient -> (int) ingredient.getAmount())
                .sum();
    }

    public List<Recipe> recetteoliveoil(){
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .anyMatch(ingredient -> ingredient.getName().equalsIgnoreCase("olive oil")))
                .collect(Collectors.toList());
    }

    public int calculateEggsPerRecipe(Recipe recipe) {
        int eggCount = 0;
        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().toLowerCase().contains("egg")) {
                eggCount += ingredient.getAmount();
            }
        }
        return eggCount;
    }

    public List<Recipe> recipemoins500cal(){
        return recipes.stream()
                .filter(recipe->recipe.getNutrition().getCalories()<500)
                .collect(Collectors.toList());
    }

    public double zuppainglesesugar(){
        return recipes.stream()
                .filter(n->n.getTitle().equals("Zuppa Inglese"))
                .flatMap(recipe -> recipe.getIngredients().stream())
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase("sugar"))
                .mapToDouble(Ingredient::getAmount)
                .sum();
    }


    //marche pas
    public List<String> deuxetapezuppa() {
        return recipes.stream()
                .filter(recipe -> recipe.getTitle().equals("Zuppa Inglese"))
                .flatMap(recipe -> recipe.getPreparationSteps().stream().limit(2))
                .collect(Collectors.toList());
    }



    public List<Recipe> recettesPlusDeCinqEtapes() {
        return recipes.stream()
                .filter(recipe -> recipe.getPreparationSteps().size() > 5)
                .collect(Collectors.toList());
    }
    public List<Recipe> pasdebeurre(){
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .noneMatch(ingredient -> ingredient.getName().equalsIgnoreCase("butter")))
                .collect(Collectors.toList());
    }

    public List<Recipe> memeingredientszuppa(){
        List<String> ingredientsZuppaInglese = recipes.stream()
                .filter(recipe -> recipe.getTitle().equals("Zuppa Inglese"))
                .flatMap(recipe -> recipe.getIngredients().stream())
                .map(Ingredient::getName)
                .collect(Collectors.toList());

        // Filtrer les recettes ayant les mêmes ingrédients que la recette Zuppa Inglese
        return recipes.stream()
                .filter(recipe -> recipe != null && !recipe.getTitle().equalsIgnoreCase("Zuppa Inglese")) // Exclure la recette Zuppa Inglese elle-même
                .filter(recipe -> recipe.getIngredients().stream()
                        .map(Ingredient::getName)
                        .anyMatch(ingredientsZuppaInglese::contains))
                .collect(Collectors.toList());


    }

    public String pluscalorique() {
        Recipe mostCaloricRecipe = recipes.stream()
                .max(Comparator.comparingInt(r -> r.getNutrition().getCalories()))
                .orElseThrow(() -> new RuntimeException("Aucune recette trouvée"));

        return "Recette la plus calorique : " + mostCaloricRecipe.getTitle() +
                ", Calories : " + mostCaloricRecipe.getNutrition().getCalories();
    }



    public String unitePlusFrequente() {
        Map<String, Long> uniteCounts = recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .map(Ingredient::getUnit)
                .filter(unit -> unit != null && !unit.isEmpty()) // Exclure les chaînes vides

                .collect(Collectors.groupingBy(unit -> unit, Collectors.counting()));

        return uniteCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Aucune unité trouvée");
    }

    public Map<Recipe, Integer> nombreIngredientsParRecette() {
        return recipes.stream()
                .collect(Collectors.toMap(
                        recipe -> recipe,
                        recipe -> recipe.getIngredients().size()
                ));
    }

    public Recipe plusdefat(){
        return recipes.stream()
                .max(Comparator.comparing(r->r.getNutrition().getFat()))
                .orElseThrow(() -> new RuntimeException("Aucune recette trouvée"));

    }

    public String Ingredientplusutilisé(){
        Map<String, Long> ingredientCounts = recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .collect(Collectors.groupingBy(Ingredient::getName, Collectors.counting()));
        return ingredientCounts.entrySet()
                .stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("aucun ingredient trouvé");

    }

    public List<Recipe> recetteparnbreingredient(){
        return recipes.stream()
                .sorted(Comparator.comparingInt(recipe -> recipe.getIngredients().size()))
                .collect(Collectors.toList());
    }

    public Map<String, List<String>> recettesParIngredient() {
        return recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream()
                        .map(Ingredient::getName)
                        .distinct() // Supprime les doublons d'ingrédients
                        .map(ingredient -> Map.entry(ingredient, recipe.getTitle())))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    //marche pas

    public Map<Integer, List<Recipe>> repartitionParEtape() {
        return recipes.stream()
                // Regrouper les recettes par nombre d'étapes
                .collect(Collectors.groupingBy(recipe -> recipe.getPreparationSteps().size()));
    }

    public Recipe plusfacile(){
        return recipes.stream()
                .min(Comparator.comparing(r->r.getPreparationSteps().size()))
                .orElseThrow();
    }

}
