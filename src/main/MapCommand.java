package main;

import utils.Array2Dprinter;
import utils.IPrintable;

public class MapCommand implements ICommand {
   private WorldMap map;

   public MapCommand(WorldMap map) {
      this.map = map;
   }

   public String getDescription() {
      return "Display the map of the game.";
   }

   public String getUsage() {
      return "map";
   }

   public boolean hasValidArguments(String input) {
      return input.trim().equals("map");
   }

   public void execute(String input, Game game) {
      String mapString = Array2Dprinter.print2DArray((IPrintable[][])this.map.getMap(), this.map.getPlayerRow(), this.map.getPlayerCol());
      System.out.println(mapString);
   }
}
