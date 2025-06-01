package main;

import java.util.Map;

public class SolveCommand implements ICommand {
   private Map<Location, Enigma> enigmaMap;
   private Player player;

   public SolveCommand(Map<Location, Enigma> enigmaMap, Player player) {
      this.enigmaMap = enigmaMap;
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

   public void execute(String input, Game game) {
      Location current = this.player.getLocation();
      if (this.enigmaMap.containsKey(current)) {
         ((Enigma)this.enigmaMap.get(current)).ask();
      } else {
         System.out.println("There is no enigma in this location.");
      }

   }
}
