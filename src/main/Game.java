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
        Location loc01 = new Location("Prilly", "L'endroit le plus dangereux de tous les temps.", true);
        Location loc02 = new Location("Yverdon-les-Bains", "La pire ville de suisse romande.", false);
        Location loc10 = new Location("Renens", "Le monde snack t'attend.", true);
        Location loc11 = new Location("Crissier", "Marcolet.", false);
        Location loc12 = new Location("Bourdonettes", "Une ville un peu moins dangereuse que Prilly.", false);
        Location loc20 = new Location("Eclépens", "Tu n'as pas de connexion ici.", false);
        Location loc21 = new Location("Paris", "L'endroit qui a la meilleure équipe de foot d'Europe.", true);
        Location loc22 = new Location("Chaux-de-Fonds", "Force à Pierre.", false);
        GameObject keyYverdon = new Key("key", "Une clé rouillée", loc02);
        loc01.addObject(keyYverdon);
        GameObject badgePierre = new Key("badge", "Un badge noir avec le logo d’un PC gamer", loc22);
        loc21.addObject(badgePierre);
        GameObject passCrissier = new Key("pass", "Un pass VIP avec l’inscription 'Marcolet Club'", loc11);
        loc10.addObject(passCrissier);

        this.world.addLocation(loc00, 0, 0);
        this.world.addLocation(loc01, 0, 1);
        this.world.addLocation(loc02, 0, 2);
        this.world.addLocation(loc10, 1, 0);
        this.world.addLocation(loc11, 1, 1);
        this.world.addLocation(loc12, 1, 2);
        this.world.addLocation(loc20, 2, 0);
        this.world.addLocation(loc21, 2, 1);
        this.world.addLocation(loc22, 2, 2);

        this.world.setPlayerLocation(0, 0);
        this.player.setLocation(loc00);
        this.player.setInventory(new Inventory());
        
        this.commandRegistry.addCommand(new GoCommand(this.world, this.player));
        this.commandRegistry.addCommand(new LookCommand(this.player));
        this.commandRegistry.addCommand(new MapCommand(this.world));
        this.commandRegistry.addCommand(new HelpCommand(this.commandRegistry));
        this.commandRegistry.addCommand(new TakeCommand(this.player));
        this.commandRegistry.addCommand(new InspectCommand(this.player));
        this.commandRegistry.addCommand(new UseCommand(this.player));
    }

    public void run() {
        System.out.println("Welcome to the game! Type 'help' to see available commands.");
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