package main;
import utils.Array2Dprinter;
import utils.IPrintable;


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
    String mapString = Array2Dprinter.print2DArray(
        (IPrintable[][]) map.getMap(),
        map.getPlayerRow(),
        map.getPlayerCol()
    );
    System.out.println(mapString);
}
    
}
