package main;

import java.util.Scanner;

import utils.Color;
import utils.StringStyling;
import utils.Style;



public class Main {
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Charger la dernière sauvegarde ? (oui/non) : ");
        String answer = sc.nextLine().trim().toLowerCase();

        boolean load = answer.equals("oui");
        System.out.println(StringStyling.StyleString("Starting...", Style.ITALIC, Color.BLACK));
        Game game = new Game(load);
        game.run();
        System.out.println(StringStyling.StyleString("Terminating...", Style.ITALIC, Color.BLACK));
    }

}
