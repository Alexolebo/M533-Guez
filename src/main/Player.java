package main;

public class Player {
   private Location location;
   private Inventory inventory;

   public Player() {
   }

   public void moveTo(Location newLocation) {
      this.location = newLocation;
   }

   public Location getLocation() {
      return this.location;
   }

   public void setLocation(Location loc) {
      this.location = loc;
   }

   public Inventory getInventory() {
      return this.inventory;
   }

   public void setInventory(Inventory inventory) {
      this.inventory = inventory;
   }
}
