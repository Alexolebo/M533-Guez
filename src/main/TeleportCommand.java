package main;

import java.util.Set;

public class TeleportCommand implements ICommand {

    private Player player;
    private WorldMap world;

    public TeleportCommand(Player player, WorldMap world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public void execute(String input, Game game) {
        String[] parts = input.split(" ", 2);
        if (!player.hasItem("teleport crystal")) {
            System.out.println("Vous n'avez pas le teleport crystal.");
            return;
        }

        if (parts.length < 2) {
            System.out.println("Téléportation où ? Syntaxe : teleport nom_du_lieu");
            return;
        }

        String destinationName = parts[1].trim();

        Location target = world.findLocationByName(destinationName);
        if (target == null) {
            System.out.println("Ce lieu n'existe pas.");
            return;
        }

        if (!player.hasVisited(target)) {
            System.out.println("Vous n'avez pas encore visité ce lieu.");
            return;
        }

        player.moveTo(target);
        System.out.println("✨ Téléporté à " + destinationName + " !");
        System.out.println(player.getLocation().getLongDescription());
    }

    @Override
    public boolean hasValidArguments(String input) {
        return input.trim().split(" ").length >= 2;
    }

    @Override
    public String getUsage() {
        return "teleport nom_du_lieu";
    }
}
