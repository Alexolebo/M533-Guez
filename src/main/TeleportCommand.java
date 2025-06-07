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

        // Vérifie que le joueur a bien l’objet "crystal"
        if (!player.getInventory().contains("crystal")) {
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

        // Vérifie que le joueur a déjà visité cet endroit
        if (!player.hasVisited(target)) {
            System.out.println("Vous n'avez pas encore visité ce lieu.");
            return;
        }

        // Téléportation
        player.setLocation(target);
        world.setPlayerLocation(target); // Assure que la position est aussi mise à jour dans la carte
        System.out.println(" Téléporté à " + destinationName + " !");
        System.out.println(target.getDescription());
    }

    @Override
    public boolean hasValidArguments(String input) {
        return input.trim().split(" ").length >= 2;
    }

    @Override
    public String getUsage() {
        return "teleport nom_du_lieu";
    }

    @Override
    public String getDescription() {
        return "Permet de se téléporter dans un lieu déjà visité si vous avez le crystal.";
    }

}
