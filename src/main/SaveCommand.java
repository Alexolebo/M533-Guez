package main;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
 
public class SaveCommand implements ICommand {
    private List<String> commandHistory;
 
    public SaveCommand(List<String> commandHistory) {
        this.commandHistory = commandHistory;
    }
 
    @Override
    public String getDescription() {
        return "Sauvegarde la partie dans un fichier.";
    }

    @Override
    public String getUsage() {
        return "save";
    }

    @Override
    public boolean hasValidArguments(String input) {
        return input.trim().equalsIgnoreCase("save");
    }

    @Override
    public void execute(String input, Game game) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"))) {
            for (String command : commandHistory) {
                writer.write(command);
                writer.newLine();
            }
            System.out.println("Partie sauvegardée avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde.");
        }
    }
}

