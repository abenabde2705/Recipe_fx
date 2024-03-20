package Presentation;

import Repositories.Reciperepo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Recipe;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class HelloController {
    @FXML
    private Button presentationTextuelleButton;

    @FXML
    private Button presentationGraphiqueButton;
    @FXML
    private Label welcomeText;
    @FXML
    private Label resulttext;

    private CookingApp mainApp;


    public CookingApp getMainApp() {
        return mainApp;
    }
    private Stage primaryStage;


    public void setMainApp(CookingApp mainApp) {
        this.mainApp = mainApp;
    }

    // on presentationgraphique button click
    @FXML
    protected void onHelloButtonClick() {
        this.mainApp.showGraphicalView(primaryStage);
    }

    //traitement de mouvement de souris sur les boutons (hover css)
    @FXML
    protected void handleMouseEntered(MouseEvent event) {
        Button sourceButton = (Button) event.getSource();
        sourceButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 14pt; -fx-padding: 10px;");
    }
    @FXML
    protected void handleMouseEnteredlaunch(MouseEvent event) {
        Button sourceButton = (Button) event.getSource();
        sourceButton.setStyle("-fx-background-color:#8c8c8c ; -fx-text-fill: black;  -fx-border-radius: 50px; -fx-background-radius: 10px;");
    }
    @FXML
    protected void handleMouseExitedlaunch(MouseEvent event) {
        Button sourceButton = (Button) event.getSource();
        sourceButton.setStyle("-fx-background-color: gray; -fx-text-fill: white;  -fx-border-radius: 50px; -fx-background-radius: 10px;");
    }
    @FXML
    protected void handleMouseEnteredshow(MouseEvent event) {
        Button sourceButton = (Button) event.getSource();
        sourceButton.setStyle("-fx-background-color: #87e88b; -fx-text-fill: Black; -fx-font-size: 14pt; -fx-padding: 10px;");
    }

    @FXML
    protected void handleMouseExited(MouseEvent event) {
        Button sourceButton = (Button) event.getSource();
        sourceButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px;");
    }

    @FXML
    protected void handleMouseExited2(MouseEvent event) {
        Button sourceButton = (Button) event.getSource();
        sourceButton.setStyle("-fx-font-size: 14pt; -fx-text-fill: white; -fx-background-color: #008CBA; -fx-padding: 10px;");
    }

    // le bouton return pour revenir à la première page
    @FXML
    protected void onreturnclik() {
        try {
            Stage primaryStage = (Stage) welcomeText.getScene().getWindow();
            // Charger le fichier FXML de la page hello-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec le contenu de hello-view.fxml
            Scene helloScene = new Scene(root);

            // Mettre à jour la scène du primaryStage avec la nouvelle scène
            primaryStage.setScene(helloScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //on presentation textuelle click

    @FXML
    protected void ontextButtonClick() throws Exception {

        Stage primaryStage = (Stage) welcomeText.getScene().getWindow();
        primaryStage.close();

        // Afficher le menu textuel et lire l'entrée de l'utilisateur
        while (true) {
            System.out.println("Menu Textuel:");
            System.out.println("1. Afficher les recettes");
            System.out.println("2. Tester les méthodes");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");

            // Lire l'entrée de l'utilisateur depuis la console
            Scanner scanner = new Scanner(System.in);
            int choix = scanner.nextInt();

            // Traiter le choix de l'utilisateur
            switch (choix) {
                case 1:
                    // Appeler une méthode pour afficher les recettes
                    afficherRecettes();
                    break;
                case 2:
                    // Appeler une méthode pour tester les méthodes
                    testerMethodes();
                    break;

                case 3:
                    // Quitter l'application
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        }
    }

    // Méthode pour afficher les recettes
    private void afficherRecettes() {
        // Récupérer les recettes depuis le repository ou effectuer toute autre logique nécessaire
        String filePath = "C:\\Users\\amrou\\IdeaProjects\\traitement_recettes\\src\\main\\java\\recipes.xml";
        Reciperepo repository = new Reciperepo();
        repository.init(filePath);
        List<String> listrecipe= repository.getRecipeTitles();
        for( String recette : listrecipe){
            System.out.println("- "+recette);
            System.out.println(" ");
        }
    }

    // Méthode pour tester les méthodes
    private void testerMethodes() {
        // Afficher les méthodes disponibles
        System.out.println("Méthodes disponibles :");
        for (int i = 0; i < methods.length; i++) {
            System.out.println((i + 1) + ". " + methods[i]);
        }

        // Demander à l'utilisateur de choisir une méthode
        System.out.print("Veuillez choisir une méthode à tester (entrez le numéro) : ");
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();

        // Exécuter la méthode choisie
        executerMethode(choix);
    }
    private void executerMethode(int choix) {
        // Si l'utilisateur choisit 0, revenir au menu précédent
        if (choix == 0) {
            return;
        }

        // Vérifier si le choix est valide
        if (choix < 1 || choix > methods.length) {
            System.out.println("Choix invalide !");
            return;
        }

        // Création d'une instance de Reciperepo
        String filePath = "C:\\Users\\amrou\\IdeaProjects\\traitement_recettes\\src\\main\\java\\recipes.xml";
        Reciperepo repository = new Reciperepo();
        repository.init(filePath);

        // Exécuter la méthode correspondante en fonction du choix de l'utilisateur
        switch (choix) {
            case 1:
                System.out.println("**************************");
                int totalEggsUsed = repository.nombreoeufstotal();
                System.out.println("Nombre total d'œufs utilisés : " + totalEggsUsed);
                break;
            case 2:
                System.out.println("**************************");
                List<Recipe> recipesWithOliveOil = repository.recetteoliveoil();
                System.out.println("Recettes utilisant de l'huile d'olive : ");
                for (Recipe recipe : recipesWithOliveOil) {
                    System.out.println(recipe.getTitle());
                }
                break;
            case 3:
                System.out.println("**************************");
                for (Recipe recipe : repository.getRecipes()) {
                    int eggsCount = repository.calculateEggsPerRecipe(recipe);
                    System.out.println("Recette : " + recipe.getTitle() + ", Nombre d'œufs : " + eggsCount);
                }
                break;
            case 4:
                System.out.println("**************************");
                List<Recipe> recipesWithLessThan500Calories = repository.recipemoins500cal();
                System.out.println("Recettes avec moins de 500 calories :");
                for (Recipe recipe : recipesWithLessThan500Calories) {
                    System.out.println(recipe.getTitle());
                }
                break;
            case 5:
                System.out.println("**************************");
                System.out.println("recette Inglese qt sucre: " + repository.zuppainglesesugar() + " cup");
                break;
            case 6:
                System.out.println("**************************");
                System.out.println(repository.deuxetapezuppa());
                break;
            case 7:
                System.out.println("**************************");
                List<Recipe> recettesPlusDeCinqEtapes = repository.recettesPlusDeCinqEtapes();
                System.out.println("Recettes avec plus de cinq étapes : ");
                for (Recipe recipe : recettesPlusDeCinqEtapes) {
                    System.out.println(recipe.getTitle());
                }
                break;
            case 8:
                System.out.println("**************************");
                List<Recipe> recettesSansBeurre = repository.pasdebeurre();
                System.out.println("Recettes sans beurre : ");
                for (Recipe recipe : recettesSansBeurre) {
                    System.out.println(recipe.getTitle());
                }
                break;
            case 9:
                System.out.println("**************************");
                List<Recipe> recipesWithSameIngredients = repository.memeingredientszuppa();
                System.out.println("Recettes avec les mêmes ingrédients que Zuppa Inglese :");
                for (Recipe recipe : recipesWithSameIngredients) {
                    System.out.println(recipe.getTitle());
                }
                break;
            case 10:
                System.out.println("**************************");
                System.out.println(repository.pluscalorique());

                break;
            case 11:
                System.out.println("**************************");
                String unitePlusFrequente = repository.unitePlusFrequente();
                System.out.println("L'unité la plus fréquente est : " + repository.unitePlusFrequente());

                break;
            case 12:
                System.out.println("**************************");
                Map<Recipe, Integer> nombreIngredientsParRecette = repository.nombreIngredientsParRecette();
                for (Map.Entry<Recipe, Integer> entry : nombreIngredientsParRecette.entrySet()) {
                    System.out.println("Recette : " + entry.getKey().getTitle() + ", Nombre d'ingrédients : " + entry.getValue());
                }
                break;
            case 13:
                System.out.println("**************************");
                System.out.println("recette plus grasse : " + repository.plusdefat().getTitle() + " taux de fat : " + repository.plusdefat().getNutrition().getFat());

                break;
            case 14:
                System.out.println("**************************");
                System.out.println("l'ingredient le plus répeté: " + repository.Ingredientplusutilisé());

                break;
            case 15:
                System.out.println("**************************");
                List<Recipe> recettesParNbIngredient = repository.recetteparnbreingredient();
                for (Recipe recipe : recettesParNbIngredient) {
                    System.out.println("Recette : " + recipe.getTitle());
                    System.out.println("Nombre d'ingrédients : " + recipe.getIngredients().size());
                    System.out.println("---------------------------------------------");
                }
                break;
            case 16:
                System.out.println("**************************");
                Map<String, List<String>> recipesByIngredient = repository.recettesParIngredient();
                for (Map.Entry<String, List<String>> entry : recipesByIngredient.entrySet()) {
                    System.out.println("Ingrédient : " + entry.getKey());
                    System.out.println("Recettes :");
                    for (String recipe : entry.getValue()) {
                        System.out.println("- " + recipe);
                    }
                    System.out.println();
                }

                break;
            case 17:
                System.out.println("**************************");
                Map<Integer, List<Recipe>> stepsDistribution = repository.repartitionParEtape();

                for (Map.Entry<Integer, List<Recipe>> entry : stepsDistribution.entrySet()) {
                    System.out.println("Nombre d'étapes : " + entry.getKey());
                    System.out.println("Recettes :");
                    for (Recipe recipe : entry.getValue()) {
                        System.out.println("- " + recipe.getTitle());
                    }
                    System.out.println();
                }


                break;
            case 18:
                System.out.println("**************************");
                Recipe recetteLaPlusFacile = repository.plusfacile();
                if (recetteLaPlusFacile != null) {
                    System.out.println("La recette la plus facile est : " + recetteLaPlusFacile.getTitle());
                } else {
                    System.out.println("Aucune recette trouvée.");
                }
                break;



            default:
                System.out.println("Choix invalide !");
                break;
        }
    }
    @FXML
    private TextArea recipeTitlesTextArea;

    @FXML
    private TextArea resultmethod;
    @FXML
    private ComboBox<String> combochoices;
    private String[] methods = {"Calculer le nombre total d’œufs utilisés",
            "Retourner les recettes utilisant l’huile d’olive",
            "Calculer le nombre d’œufs par recette",
            "Retourner les recettes fournissant moins de 500 calories",
            "Retourner la quantité de sucre utilisée par la recette Zuppa Inglese",
            "Afficher les 2 premières étapes de la recette Zuppa Inglese",
            "Retourner les recettes qui nécessitent plus de 5 étapes",
            "Retourner les recettes qui ne contiennent pas de beurre",
            "Retourner les recettes ayant des ingrédients en communs avec la recette Zuppa Inglese",
            "Afficher la recette la plus calorique",
            "Retourner l’unité la plus fréquente",
            "Calculer le nombre d’ingrédients par recette",
            "Retourner la recette comportant le plus de fat",
            "Calculer l’ingrédient le plus utilisé",
            "Afficher les recettes triées par nombre d’ingrédients",
            "Afficher pour chaque ingrédient, les recettes qui l’utilisent",
            "Calculer la répartition des recettes par étape de réalisation",
            "Calculer la recette la plus facile (avec le moins d’étape)"};

    public void initialize() {

        if (combochoices != null) {
            combochoices.setStyle("-fx-font-size: 14px;-fx-background-color: #4CAF50;");
            List<String> methodNames = new ArrayList<>();
            for (String method : methods) {
                methodNames.add(method);
            }
            combochoices.getItems().addAll(methodNames);
        } else {
            System.err.println("ComboBox 'combochoices' is null");
        }
    }
    @FXML
    protected void onLaunchButtonClick() {
        String filePath = "C:\\Users\\amrou\\IdeaProjects\\traitement_recettes\\src\\main\\java\\recipes.xml";

        // Création d'une instance de Reciperepo et initialisation avec le chemin absolu du fichier XML
        Reciperepo repository = new Reciperepo();
        repository.init(filePath);
        String selectedMethod = combochoices.getValue();
        if (selectedMethod != null) {
            switch (selectedMethod) {
                case "Calculer le nombre total d’œufs utilisés":
                    int totalEggs = repository.nombreoeufstotal();
                    resultmethod.setText(String.valueOf(totalEggs));
                    break;
                case "Retourner les recettes utilisant l’huile d’olive":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    List<String> recipesWithOliveOil = repository.recetteoliveoil().stream()
                            .map(Recipe::getTitle)
                            .collect(Collectors.toList());
                    resultmethod.setText(String.join("\n", recipesWithOliveOil));
                    break;
                case "Calculer le nombre d’œufs par recette":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    Map<String, Integer> eggsPerRecipe = repository.getRecipes().stream()
                            .collect(Collectors.toMap(
                                    Recipe::getTitle,
                                    repository::calculateEggsPerRecipe
                            ));
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Nombre d'œufs par recette :\n");

                    for (Map.Entry<String, Integer> entry : eggsPerRecipe.entrySet()) {
                        stringBuilder.append("Recette : ").append(entry.getKey()).append(", Nombre d'œufs : ").append(entry.getValue()).append("\n");
                    }
                    resultmethod.setText(stringBuilder.toString());
                    break;
                case "Retourner les recettes fournissant moins de 500 calories":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    List<String> lowCalorieRecipes = repository.recettesPlusDeCinqEtapes().stream()
                            .map(Recipe::getTitle)
                            .collect(Collectors.toList());
                    resultmethod.setText(String.join("\n", lowCalorieRecipes));
                    break;
                case "Retourner la quantité de sucre utilisée par la recette Zuppa Inglese":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    double sugarAmount = repository.zuppainglesesugar();
                    resultmethod.setText(String.valueOf(sugarAmount));
                    break;
                case "Afficher les 2 premières étapes de la recette Zuppa Inglese":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    List<String> firstTwoSteps = repository.deuxetapezuppa();
                    resultmethod.setText(String.join("- "+"\n", firstTwoSteps));
                    break;
                case "Retourner les recettes qui nécessitent plus de 5 étapes":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    List<String> recipesWithMoreThan5Steps = repository.recettesPlusDeCinqEtapes().stream()
                            .map(Recipe::getTitle)
                            .collect(Collectors.toList());
                    resultmethod.setText(String.join("\n", recipesWithMoreThan5Steps));
                    break;
                case "Retourner les recettes qui ne contiennent pas de beurre":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    List<String> recipesWithoutButter = repository.pasdebeurre().stream()
                            .map(Recipe::getTitle)
                            .collect(Collectors.toList());
                    resultmethod.setText(String.join("\n", recipesWithoutButter));
                    break;
                case "Retourner les recettes ayant des ingrédients en communs avec la recette Zuppa Inglese":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    List<String> recipesWithCommonIngredients = repository.memeingredientszuppa().stream()
                            .map(Recipe::getTitle)
                            .collect(Collectors.toList());
                    resultmethod.setText(String.join("\n", recipesWithCommonIngredients));
                    break;
                case "Afficher la recette la plus calorique":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    String mostCaloricRecipe = repository.pluscalorique();
                    resultmethod.setText(mostCaloricRecipe);
                    break;
                case "Retourner l’unité la plus fréquente":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    String mostFrequentUnit = repository.unitePlusFrequente();
                    resultmethod.setText(mostFrequentUnit);
                    break;
                case "Calculer le nombre d’ingrédients par recette":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    Map<String, Integer> ingredientsPerRecipe = repository.nombreIngredientsParRecette().entrySet().stream()
                            .collect(Collectors.toMap(
                                    entry -> entry.getKey().getTitle(),
                                    Map.Entry::getValue
                            ));
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Nombre d'ingrédients par recette :\n");
                    for (Map.Entry<String, Integer> entry : ingredientsPerRecipe.entrySet()) {
                        stringBuilder2.append("Recette : ").append(entry.getKey()).append(", Nombre d'ingrédients : ").append(entry.getValue()).append("\n");
                    }
                    resultmethod.setText(stringBuilder2.toString());

                    break;
                case "Retourner la recette comportant le plus de fat":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    Recipe recipeWithMostFat = repository.plusdefat();
                    resultmethod.setText(recipeWithMostFat.getTitle());
                    break;
                case "Calculer l’ingrédient le plus utilisé":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    String mostUsedIngredient = repository.Ingredientplusutilisé();
                    resultmethod.setText(mostUsedIngredient);
                    break;
                case "Afficher les recettes triées par nombre d’ingrédients":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    List<Recipe> recipesSortedByIngredientCount = repository.recetteparnbreingredient();
                    List<String> sortedRecipeTitles = recipesSortedByIngredientCount.stream()
                            .map(Recipe::getTitle)
                            .collect(Collectors.toList());
                    resultmethod.setText(String.join("\n", sortedRecipeTitles));
                    break;
                case "Afficher pour chaque ingrédient, les recettes qui l’utilisent":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    Map<String, List<String>> recipesByIngredient = repository.recettesParIngredient();
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Recettes par ingrédient :\n");
                    for (Map.Entry<String, List<String>> entry : recipesByIngredient.entrySet()) {
                        stringBuilder3.append("Ingrédient : ").append(entry.getKey()).append("\n");
                        stringBuilder3.append("Recettes : \n");
                        // Itération sur la liste des recettes pour cet ingrédient
                        for (String recipe : entry.getValue()) {
                            stringBuilder3.append("- ").append(recipe).append("\n");
                        }
                        stringBuilder3.append("\n");
                    }
                    resultmethod.setText(stringBuilder3.toString());                    break;
                case "Calculer la répartition des recettes par étape de réalisation":
                    Map<Integer, List<Recipe>> stepsDistribution = repository.repartitionParEtape();
                    StringBuilder resultText = new StringBuilder();
                    for (Map.Entry<Integer, List<Recipe>> entry : stepsDistribution.entrySet()) {
                        resultText.append("Nombre d'étapes : ").append(entry.getKey()).append("\n");
                        resultText.append("Recettes :\n");
                        for (Recipe recipe : entry.getValue()) {
                            resultText.append("- ").append(recipe.getTitle()).append("\n");
                        }
                        resultText.append("\n"); // Ajouter une ligne vide pour séparer les différentes entrées
                    }
                    resultmethod.setText(resultText.toString());
                    break;
                case "Calculer la recette la plus facile (avec le moins d’étape)":
                    // Appeler la méthode correspondante et afficher le résultat dans la TextArea
                    Recipe easiestRecipe = repository.plusfacile();
                    resultmethod.setText(easiestRecipe.getTitle());
                    break;
             }
        }
    }
    @FXML
    private VBox recipeButtonsContainer;


    @FXML
    protected void onListRecipesButtonClick() {
        recipeButtonsContainer.getChildren().clear();

        String filePath = "C:\\Users\\amrou\\IdeaProjects\\traitement_recettes\\src\\main\\java\\recipes.xml";

        // Création d'une instance de Reciperepo et initialisation avec le chemin absolu du fichier XML
        Reciperepo repository = new Reciperepo();
        repository.init(filePath);
        List<String> recipeTitles = repository.getRecipeTitles();

        // Pour chaque recette, créez un bouton cliquable
        for (String recipeTitle : recipeTitles) {
            Button recipeButton = new Button(recipeTitle);
            recipeButton.setStyle("-fx-background-color: #fdfdfd; -fx-text-fill: #000000; -fx-font-size: 14px; -fx-padding: 5px; -fx-end-margin: 10px");
            recipeButton.setOnMouseEntered(e -> {
                recipeButton.setStyle("-fx-background-color: #868686;  -fx-font-size: 14px; -fx-padding: 5px; -fx-end-margin: 10px;");
            });

            recipeButton.setOnMouseExited(e -> {
                recipeButton.setStyle("-fx-background-color: #fdfdfd;  -fx-font-size: 14px;  -fx-padding: 5px; -fx-end-margin: 10px;");
            });
            recipeButton.setOnAction(event -> showRecipeDetails(recipeTitle));
            // Ajoutez le bouton à votre interface utilisateur
            // (par exemple, à un conteneur VBox)
            recipeButtonsContainer.getChildren().add(recipeButton);

        }
    }

    private void showRecipeDetails(String recipeTitle) {
        String filePath = "C:\\Users\\amrou\\IdeaProjects\\traitement_recettes\\src\\main\\java\\recipes.xml";

        // Création d'une instance de Reciperepo et initialisation avec le chemin absolu du fichier XML
        Reciperepo repository = new Reciperepo();
        repository.init(filePath);

        List<String> recipeDetails = repository.getIngredients(recipeTitle);
        List<String> preparationStepsList = repository.getPreparationSteps(recipeTitle);

        try {
            // Charger le fichier FXML pour la vue des détails de la recette
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RecipeDetails.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur de la vue des détails de la recette
            RecipeDetailsController controller = loader.getController();

            // Mettre à jour le titre et les ingrédients de la recette dans le contrôleur

            controller.setRecipeDetails(recipeTitle, recipeDetails, preparationStepsList);




            // Afficher la fenêtre des détails de la recette dans une nouvelle fenêtre modale
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
