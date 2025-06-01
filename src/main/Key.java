package main;

import java.io.PrintStream;

public class Key extends GameObject {
   private Location target;

   public Key(String name, String description, Location target) {
      super(name, description);
      this.target = target;
   }

   public void use(Player player, Game game) {
      if (!this.target.isOpen()) {
         this.target.unlock();
         PrintStream var10000 = System.out;
         String var10001 = this.getName();
         var10000.println("You used the " + var10001 + " to unlock " + this.target.getName());
      } else {
         System.out.println(this.target.getName() + " is already unlocked.");
      }

   }
}
