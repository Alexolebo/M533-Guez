package main;

public class LookCommand implements ICommand {
    private Player player;

    public LookCommand(Player player) {
        this.player = player;
    }

    public String getDescription() {
        return "Show the description of the current location.";
    }

    public String getUsage() {
        return "look";
    }

    public boolean hasValidArguments(String input) {
        return input.trim().equals("look");
    }

    public void execute(String input, Game game) {
        Location current = player.getLocation();
        System.out.println(current.getDescription());
    }
}
