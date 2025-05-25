package main;

public class Player {
    private Location location;

    public void moveTo(Location newLocation) {
        this.location = newLocation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }
}
