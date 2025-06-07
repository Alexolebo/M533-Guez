package main;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private Location location;
    private Inventory inventory;

    public Player() {
    }

   public void moveTo(Location newLocation) {
    this.location = newLocation;
    visit(newLocation); // Marquer le lieu comme visité
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

    private Set<Location> visitedLocations = new HashSet<>();

    public boolean hasItem(String itemName) {
        return inventory.contains(itemName); // adapte selon ta classe Inventory
    }

    public void visit(Location location) {
        visitedLocations.add(location);
    }

    public boolean hasVisited(Location location) {
        return visitedLocations.contains(location);
    }
}
