package main;
import utils.Array2Dprinter;

public class MapCommand implements ICommand {
    private WorldMap map;

    public MapCommand(WorldMap map) {
        this.map = map;
    }

    @Override
    public String getDescription() {
        return "Display the map of the game.";
    }

    @Override
    public String getUsage() {
        return "map";
    }

    @Override
    public boolean hasValidArguments(String input) {
        return input.trim().equals("map");
    }

    @Override
    public void execute(String input, Game game) {
        Array2Dprinter.print2DArray(map.getMap(), 0, 0);
    }
}
