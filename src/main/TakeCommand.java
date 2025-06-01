package main;

import java.util.Iterator;
import java.util.List;

public class TakeCommand implements ICommand {
   private Player player;

   public TakeCommand(Player player) {
      this.player = player;
   }

   public String getDescription() {
      return "Take an object from the current location.";
   }

   public String getUsage() {
      return "take <object_name>";
   }

   public boolean hasValidArguments(String input) {
      return input.trim().split(" ").length >= 2;
   }

   public void execute(String input, Game game) {
      String objectName = input.substring(5).trim();
      Location location = this.player.getLocation();
      List<GameObject> objects = location.getObjects();
      Iterator var6 = objects.iterator();

      GameObject obj;
      do {
         if (!var6.hasNext()) {
            System.out.println("There is no such object here.");
            return;
         }

         obj = (GameObject)var6.next();
      } while(!obj.getName().equalsIgnoreCase(objectName));

      if (this.player.getInventory().addItem(obj)) {
         objects.remove(obj);
         System.out.println("You took the " + obj.getName());
      } else {
         System.out.println("Your inventory is full.");
      }

   }
}
