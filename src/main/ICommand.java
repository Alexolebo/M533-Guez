package main;

public interface ICommand {
    String getDescription();
    String getUsage();
    boolean hasValidArguments(String input);
    void execute(String input, Game game);
}
