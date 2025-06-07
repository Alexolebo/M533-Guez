package main;

import java.util.Map;

public class HelpCommand implements ICommand {
    private CommandRegistry registry;

    public HelpCommand(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String getDescription() {
        return "Affiche toutes les commandes disponibles.";
    }

    @Override
    public String getUsage() {
        return "help";
    }

    @Override
    public boolean hasValidArguments(String input) {
        return input.trim().equals("help");
    }

    @Override
    public void execute(String input, Game game) {
        System.out.println("Commandes disponibles :");

        for (Map.Entry<String, ICommand> entry : registry.getAllCommands().entrySet()) {
            ICommand command = entry.getValue();

            // Cacher la commande teleport si le joueur n'a pas le crystal
            if (command instanceof TeleportCommand && !game.getPlayer().hasItem("teleport crystal")) {
                continue;
            }

            System.out.println("- " + command.getUsage() + ": " + command.getDescription());
        }
    }
}
