package main;

import java.util.Map;
import java.util.Scanner;

public class SolveCommand implements ICommand {
   private Map<Location, Enigma> enigmas;
   private Player player;

   public SolveCommand(Map<Location, Enigma> enigmaMap, Player player) {
      this.enigmas = enigmaMap;
      this.player = player;
   }

   public String getDescription() {
      return "Try to solve the enigma of the current zone.";
   }

   public String getUsage() {
      return "solve";
   }

   public boolean hasValidArguments(String input) {
      return input.trim().equalsIgnoreCase("solve");
   }

   @Override
    public void execute(String input, Game game) {
    Location current = player.getLocation();

    
    Enigma enigma = enigmas.get(current);
    if (enigma == null) {
        System.out.println("Il n'y a pas d'énigme ici.");
        return;
    }

    System.out.println("Enigma: " + enigma.getQuestion());
    System.out.print("Your answer: ");

    Scanner sc = new Scanner(System.in);
    String response = sc.nextLine().trim();

    if (response.equalsIgnoreCase(enigma.getAnswer())) {
        Location target = enigma.getTarget();
        target.unlock(); 
        System.out.println("Correct! The zone " + target.getName() + " is now unlocked.");
    } else {
        System.out.println("Wrong answer.");
    }
}
}
