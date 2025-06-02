package main;

public class SaveCommand {
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
    public String getName() {
        return "save";
    }
 
    @Override
    public void execute(Game game, String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"))) {
            for (String command : commandHistory) {
                writer.write(command);
                writer.newLine();
            }
            System.out.println("Progression sauvegardée avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde.");
            e.printStackTrace();
        }
    }
}
}
