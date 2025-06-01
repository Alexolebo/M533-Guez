package main;

import java.util.Scanner;

public class Game {

    private Player player;
    private WorldMap world;
    private CommandRegistry commandRegistry;

    public Game() {
        System.out.println("Initializing game...");
        commandRegistry = new CommandRegistry();
        player = new Player();
        world = new WorldMap(3, 3);

        Location loc00 = new Location("Start", "Vous êtes au début.", true);
        Item cle = new Item("clé", "Une clé rouillée, elle ouvre peut-être quelque chose.");
        loc00.addItem(cle);
        Location loc01 = new Location("Prilly", "L'endroit le plus dangereux de tous les temps.", true);
        Location loc02 = new Location("Yverdons-Les-Bains", "La pire ville de suisse romande.", false);
        Location loc10 = new Location("Renens", "Le monde snack t attend.", true);
        Location loc11 = new Location("Crissier", "Marcolet", true);
        Location loc12 = new Location("Bourdonettes", "une ville un peu moins dangereuse que Prilly.", true);
        Location loc20 = new Location("Village", "tu es à Eclépens donc tu n'as pas de connexion", true);

        world.addLocation(loc00, 0, 0);
        world.addLocation(loc01, 0, 1);
        world.addLocation(loc02, 0, 2);
        world.addLocation(loc10, 1, 0);
        world.addLocation(loc11, 1, 1);
        world.addLocation(loc12, 1, 2);
        world.addLocation(loc20, 2, 0);

        world.setPlayerLocation(0, 0);
        player.setLocation(loc00);

        commandRegistry.addCommand(new GoCommand(world, player));
        commandRegistry.addCommand(new LookCommand(player));
        commandRegistry.addCommand(new MapCommand(world));
        commandRegistry.addCommand(new HelpCommand(commandRegistry));
    }

    public void run() {
        System.out.println("Welcome to the game! Type 'help' to see available commands.");
        // your runtime code here...

        //

        // end of game
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            ICommand command = commandRegistry.getCommand(input);

            if (command != null && command.hasValidArguments(input)) {
                command.execute(input, this);
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

}