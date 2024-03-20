package Presentation;

import Repositories.Reciperepo;
import models.Recipe;
import java.util.List;
import java.util.Map;

public class RecipePrincipale  {


    public RecipePrincipale() {

    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\amrou\\IdeaProjects\\traitement_recettes\\src\\main\\java\\recipes.xml";

        // Création d'une instance de Reciperepo et initialisation avec le chemin absolu du fichier XML
        Reciperepo repository = new Reciperepo();
        repository.init(filePath);


        //methode1)
        // Appel de la méthode getTotalEggsUsed() pour obtenir le nombre total d'œufs utilisés
        int totalEggsUsed = repository.nombreoeufstotal();
        System.out.println("Nombre total d'œufs utilisés : " + totalEggsUsed);

        //methode2)
        List<Recipe> recipesWithOliveOil = repository.recetteoliveoil();
        System.out.println("Recettes utilisant de l'huile d'olive : ");
        for (Recipe recipe : recipesWithOliveOil) {
            System.out.println(recipe.getTitle());
        }

        //methode3)
        // Récupération de la liste des recettes

        // Parcours de la liste des recettes et calcul du nombre d'œufs par recette
        for (Recipe recipe : repository.getRecipes()) {
            int eggsCount = repository.calculateEggsPerRecipe(recipe);
            System.out.println("Recette : " + recipe.getTitle() + ", Nombre d'œufs : " + eggsCount);
        }


        //methode4 500calories
        List<Recipe> recipesWithLessThan500Calories = repository.recipemoins500cal();
        System.out.println("Recettes avec moins de 500 calories :");
        for (Recipe recipe : recipesWithLessThan500Calories) {
            System.out.println(recipe.getTitle());
        }

        //methode 5)
        System.out.println("recette Inglese qt sucre: " + repository.zuppainglesesugar() +" cup");
//methode 6)
        System.out.println(repository.deuxetapezuppa());

        // methode 7)
        List<Recipe> recettesPlusDeCinqEtapes = repository.recettesPlusDeCinqEtapes();
        System.out.println("Recettes avec plus de cinq étapes : ");
        for (Recipe recipe : recettesPlusDeCinqEtapes) {
            System.out.println(recipe.getTitle());
        }

        //methode 8)
        List<Recipe> recettesSansBeurre = repository.pasdebeurre();
        System.out.println("Recettes sans beurre : ");
        for (Recipe recipe : recettesSansBeurre) {
            System.out.println(recipe.getTitle());
        }

        //methode 9)
        List<Recipe> recipesWithSameIngredients = repository.memeingredientszuppa();
        System.out.println("Recettes avec les mêmes ingrédients que Zuppa Inglese :");
        for (Recipe recipe : recipesWithSameIngredients) {
            System.out.println(recipe.getTitle());
        }

        //methode 10)
        System.out.println(repository.pluscalorique());
        //methode 11)
        String unitePlusFrequente = repository.unitePlusFrequente();
        System.out.println("L'unité la plus fréquente est : " + repository.unitePlusFrequente());

        //methode 12)
        Map<Recipe, Integer> nombreIngredientsParRecette = repository.nombreIngredientsParRecette();
        for (Map.Entry<Recipe, Integer> entry : nombreIngredientsParRecette.entrySet()) {
            System.out.println("Recette : " + entry.getKey().getTitle() + ", Nombre d'ingrédients : " + entry.getValue());
        }
        //methode 13)

        System.out.println("recette plus grasse : " + repository.plusdefat().getTitle() + " taux de fat : " + repository.plusdefat().getNutrition().getFat());

        //methode 14)
        System.out.println("l'ingredient le plus répeté: " + repository.Ingredientplusutilisé());

        //methode 15)

        List<Recipe> recettesParNbIngredient = repository.recetteparnbreingredient();

        // Affichage des recettes triées par nombre d'ingrédients
        for (Recipe recipe : recettesParNbIngredient) {
            System.out.println("Recette : " + recipe.getTitle());
            System.out.println("Nombre d'ingrédients : " + recipe.getIngredients().size());
            System.out.println("---------------------------------------------");
        }
//recette trié par nbre ingredient
        //methode 16)
        Map<String, List<String>> recettesParIngredient = repository.recettesParIngredient();

        // Affichage des recettes par ingrédient
        recettesParIngredient.forEach((ingredient, recettes) -> {
            System.out.println("Ingrédient : " + ingredient);
            System.out.println("Recettes : " + recettes);
            System.out.println();
        });

        //methode 17)
        System.out.println("***************************");
        System.out.println("Repartition des recettes: ");

        Map<Integer, List<Recipe>> stepsDistribution = repository.repartitionParEtape();

        for (Map.Entry<Integer, List<Recipe>> entry : stepsDistribution.entrySet()) {
            System.out.println("Nombre d'étapes : " + entry.getKey());
            System.out.println("Recettes :");
            for (Recipe recipe : entry.getValue()) {
                System.out.println("- " + recipe.getTitle());
            }
            System.out.println();
        }

        //methode 18)

        Recipe recetteLaPlusFacile = repository.plusfacile();
        if (recetteLaPlusFacile != null) {
            System.out.println("La recette la plus facile est : " + recetteLaPlusFacile.getTitle());
        } else {
            System.out.println("Aucune recette trouvée.");
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");



    }


}