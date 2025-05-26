package main;

import utils.Color;
import utils.StringStyling;
import utils.Style;

public class Main {

    public static void main(String[] args) {

        System.out.println(StringStyling.StyleString("Starting...", Style.ITALIC, Color.BLACK));
        Game game = new Game();
        // Création d’un objet Item
        Item cle = new Item("Clé", "Une clé dorée ancienne.");

        // Affichage pour vérifier que tout fonctionne
        System.out.println("Nom de l'objet : " + cle.getName());
        System.out.println("Description de l'objet : " + cle.getDescription());
        System.out.println("toString() : " + cle);

        game.run();

        System.out.println(StringStyling.StyleString("Terminating...", Style.ITALIC, Color.BLACK));
    }

}
