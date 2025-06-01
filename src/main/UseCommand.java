package main;

public class UseCommand implements ICommand {
   private Player player;

   public UseCommand(Player player) {
      this.player = player;
   }

   public String getDescription() {
      return "Use an object in your inventory.";
   }

   public String getUsage() {
      return "use <object_name>";
   }

   public boolean hasValidArguments(String input) {
      return input.trim().split(" ").length >= 2;
   }

   public void execute(String input, Game game) {
      String objectName = input.substring(4).trim();
      GameObject obj = this.player.getInventory().getItem(objectName);
      if (obj != null) {
         obj.use(this.player, game);
      } else {
         System.out.println("You don't have this object.");
      }

   }
}
