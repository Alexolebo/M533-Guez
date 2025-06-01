package main;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private Map<String, ICommand> commands = new HashMap<>();

    public void addCommand(ICommand command) {
        String key = command.getUsage().split(" ")[0].toLowerCase();
        commands.put(key, command);
    }

    public ICommand getCommand(String input) {
        String key = input.split(" ")[0].toLowerCase();
        return commands.get(key);
    }

    public Map<String, ICommand> getAllCommands() {
        return commands;
    }
}
