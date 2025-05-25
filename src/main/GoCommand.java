package main;
import java.util.Locale;


public class GoCommand implements ICommand {
    private WorldMap worldMap;
    private Player player;

    public GoCommand(WorldMap worldMap, Player player) {
        this.worldMap = worldMap;
        this.player = player;
    }

    @Override
    public String getDescription() {
        return "Move the player to a direction (north, south, east, west).";
    }

    @Override
    public String getUsage() {
        return "move <north|south|east|west>";
    }

    @Override
    public boolean hasValidArguments(String input) {
        String[] tokens = input.split(" ");
        return tokens.length == 2 &&
                (tokens[1].equalsIgnoreCase("north") ||
                tokens[1].equalsIgnoreCase("south") ||
                tokens[1].equalsIgnoreCase("east") ||
                tokens[1].equalsIgnoreCase("west"));
    }

    @Override
    public void execute(String input, Game game) {
        String direction = input.split(" ")[1].toLowerCase(Locale.ROOT);
        int row = worldMap.getPlayerRow();
        int col = worldMap.getPlayerCol();
        switch (direction) {
            case "north": row--; break;
            case "south": row++; break;
            case "west": col--; break;
            case "east": col++; break;
        }
        Location newLoc = worldMap.getLocation(row, col);
        if (newLoc == null) {
            System.out.println("impossible to move there");
        } else if (!newLoc.isOpen()) {
            System.out.println("zone locked");
        } else {
            player.setLocation(newLoc);
            worldMap.setPlayerLocation(row, col);
            System.out.println(newLoc.getDescription());
        }
    }
}
