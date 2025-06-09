package main;

public class WorldMap {
    private Location[][] map;
    private int playerRow;
    private int playerCol;

    public WorldMap(int rows, int cols) {
        map = new Location[rows][cols];
    }

    public void addLocation(Location loc, int row, int col) {
        map[row][col] = loc;
    }

    public Location getLocation(int row, int col) {
        if (row < 0 || col < 0 || row >= map.length || col >= map[0].length) return null;
        return map[row][col];
    }

    public void setPlayerLocation(int row, int col) {
        playerRow = row;
        playerCol = col;
    }

    public Location getPlayerLocation() {
        return map[playerRow][playerCol];
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public Location[][] getMap() {
        return map;
    }
    public Location findLocationByName(String name) {
    for (Location[] row : map) {
        for (Location loc : row) {
            if (loc != null && loc.getName().equalsIgnoreCase(name)) {
                return loc;
            }
        }
    }
    return null;
    }

    public void setPlayerLocation(Location target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPlayerLocation'");
    }

    public boolean allZonesUnlocked() {
    for (Location[] row : map) {
        for (Location loc : row) {
            if (loc != null && !loc.isOpen()) {
                return false;
            }
        }
    }
    return true;
    }

    

}
