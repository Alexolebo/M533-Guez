package main;

import java.util.Scanner;

public class Game {

    private Player player;
    private WorldMap world;
    private CommandRegistry commandRegistry;

    public Game(){
        System.out.println("Initializing game...");
        commandRegistry = new CommandRegistry();
        player = new Player();
        world = new WorldMap(3, 3);

        Location loc00 = new Location("Start", "You are at the starting point.", true);
        Location loc01 = new Location("Locked", "A locked room.", false);
        Location loc10 = new Location("North room", "You are in a room to the north.", true);

        world.addLocation(loc00, 0, 0);
        world.addLocation(loc01, 0, 1);
        world.addLocation(loc10, 1, 0);

        world.setPlayerLocation(0, 0);
        player.setLocation(loc00);

        commandRegistry.addCommand(new GoCommand(world, player));
        commandRegistry.addCommand(new LookCommand(player));
        commandRegistry.addCommand(new MapCommand(world));
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