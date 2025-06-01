package main;

public class InspectCommand implements ICommand {
   private Player player;

   public InspectCommand(Player player) {
      this.player = player;
   }

   public String getDescription() {
      return "Inspect an object in your inventory.";
   }

   public String getUsage() {
      return "inspect <object_name>";
   }

   public boolean hasValidArguments(String input) {
      return input.trim().split(" ").length >= 2;
   }

   public void execute(String input, Game game) {
      String objectName = input.substring(8).trim();
      GameObject obj = this.player.getInventory().getItem(objectName);
      if (obj != null) {
         obj.inspect();
      } else {
         System.out.println("You don't have this object.");
      }

   }
}
