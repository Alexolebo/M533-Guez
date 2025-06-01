// Source code is decompiled from a .class file using FernFlower decompiler.
package main;

import java.util.Locale;
import java.util.Map;

public class GoCommand implements ICommand {
   private WorldMap worldMap;
   private Player player;

   public GoCommand(WorldMap worldMap, Player player, Map<Location, Enigma> enigmaMap) {
      this.worldMap = worldMap;
      this.player = player;
   }

   public String getDescription() {
      return "Move the player to a direction (north, south, east, west).";
   }

   public String getUsage() {
      return "move <north|south|east|west>";
   }

   public boolean hasValidArguments(String input) {
      String[] tokens = input.split(" ");
      return tokens.length == 2 && (tokens[1].equalsIgnoreCase("north") || tokens[1].equalsIgnoreCase("south") || tokens[1].equalsIgnoreCase("east") || tokens[1].equalsIgnoreCase("west"));
   }

   public void execute(String input, Game game) {
      String direction = input.split(" ")[1].toLowerCase(Locale.ROOT);
      int row = this.worldMap.getPlayerRow();
      int col = this.worldMap.getPlayerCol();
      switch (direction) {
         case "north":
            --row;
            break;
         case "south":
            ++row;
            break;
         case "west":
            --col;
            break;
         case "east":
            ++col;
      }

      Location newLoc = this.worldMap.getLocation(row, col);
      if (newLoc == null) {
         System.out.println("impossible to move there");
      } else if (!newLoc.isOpen()) {
         System.out.println("zone locked");
      } else {
         this.player.setLocation(newLoc);
         this.worldMap.setPlayerLocation(row, col);
         System.out.println("You are now in " + newLoc.getName());
      }

   }
}
