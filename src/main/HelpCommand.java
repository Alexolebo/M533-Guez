package main;

import java.util.Map;

public class HelpCommand implements ICommand {
    private CommandRegistry registry;

    public HelpCommand(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String getDescription() {
        return "List all available commands with their usage.";
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
        System.out.println("Available commands:");
        for (Map.Entry<String, ICommand> entry : registry.getAllCommands().entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue().getDescription());
        }
    }
}
