package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private Player player;
    private WorldMap world;
    private CommandRegistry commandRegistry;
    private Map<Location, Enigma> enigmaMap;
    private List<String> commandHistory = new ArrayList<>();

    public Game(boolean loadFromSave) {
        System.out.println("VEUILLEZ LIRE ATTENTIVEMENT LES REGLES DU JEU JUSTE EN DESSOUS !!");
        this.commandRegistry = new CommandRegistry();
        this.player = new Player();
        this.world = new WorldMap(4, 3);
        this.enigmaMap = new HashMap();
        
        Location loc00 = new Location("Start", "Vous êtes au début.", true);
        Location loc01 = new Location("Prilly", "L'endroit le plus dangereux de tous les temps.", true);
        Location loc02 = new Location("Yverdon-les-Bains", "La pire ville de suisse romande.", false);
        Location loc10 = new Location("Renens", "Le monde snack t'attend.", true);
        Location loc11 = new Location("Crissier", "Marcolet.", false);
        Location loc12 = new Location("Bourdonettes", "Une ville un peu moins dangereuse que Prilly.", false);
        Location loc20 = new Location("Eclépens", "Tu n'as pas de connexion ici.", false);
        Location loc21 = new Location("Paris", "L'endroit qui a la meilleure équipe de foot d'Europe.", true);
        Location loc22 = new Location("Chaux-de-Fonds", "Force à Pierre.", false);
        Location loc30 = new Location("Marseille", "Le terrain des gros en scooter.", false);
        Location loc31 = new Location("Barcelone", "Nous possédons le Shoko et Yamal", true);
        Location loc32 = new Location("Majorque", "Le paysage est incroable ici.", false);
        
        GameObject keyYverdon = new Key("key", "Une clé rouillée pour entrer à Yverdon", loc02);
        loc01.addObject(keyYverdon); // clé qui ouvre Yverdon et qui se trouve à Prilly
        GameObject badgePierre = new Key("badge", "Un badge noir qui t'ammène à la Chaud-De-Fond", loc22);
        loc21.addObject(badgePierre); // badge qui ouvre CDF et qui se trouve à Paris
        GameObject passEclepens = new Key("pass", "Un pass VIP avec l’inscription 'Village perdu' qui permet d'entrer à Eclépens", loc20);
        loc10.addObject(passEclepens); // pass qui ouvre Eclépens et qui se trouve à Renens

        this.world.addLocation(loc00, 0, 0);
        this.world.addLocation(loc01, 0, 1);
        this.world.addLocation(loc02, 0, 2);
        this.world.addLocation(loc10, 1, 0);
        this.world.addLocation(loc11, 1, 1);
        this.world.addLocation(loc12, 1, 2);
        this.world.addLocation(loc20, 2, 0);
        this.world.addLocation(loc21, 2, 1);
        this.world.addLocation(loc22, 2, 2);
        this.world.addLocation(loc30, 3, 0);
        this.world.addLocation(loc31, 3, 1);
        this.world.addLocation(loc32, 3, 2);

        // Création du cristal de téléportation
        GameObject teleportCrystal = new GameObject("crystal", "Permet de se téléporter à des lieux déjà visités.");

        // Le cristal est placé dans la ville de Paris 
        loc21.addObject(teleportCrystal);   

        

        
        // Depuis Prilly, on débloque Crissier
        Enigma crissierEnigma = new Enigma(
                "Si tu me dis mon nom, je n’existe plus. Qui suis-je ?",
                "le silence",
                loc11
        );
        enigmaMap.put(loc01, crissierEnigma); // Énigme placée à Prilly

        // Depuis Crissier, on débloque Bourdonnette
        Enigma bourdonetteEnigma = new Enigma(
                "Plus tu t’approches de moi, moins tu me vois. Qui suis-je ?",
                "le brouillard",
                loc12
        );
        enigmaMap.put(loc11, bourdonetteEnigma); // Énigme placée à Crissier
        
        // Depuis Eclépens, on débloque Marseille
        Enigma marseilleEnigma = new Enigma(
                "Je suis une capitale catalane où joue Lewandowski. Qui suis-je ?",
                "Barcelone",
                loc30
        );
        enigmaMap.put(loc20, marseilleEnigma); // Énigme placée à Eclépens

        // Depuis Barcelone, on débloque Majorque
        Enigma majorqueEnigma = new Enigma(
                "Tu peux me lancer sans me rattraper. Je fais mal sans jamais toucher. Qui suis-je ?",
                "une insulte",
                loc32
        );
        enigmaMap.put(loc31, majorqueEnigma); // Énigme placée à Barcelone

        

        this.world.setPlayerLocation(0, 0);
        this.player.setLocation(loc00);
        this.player.setInventory(new Inventory());
        
        
        this.commandRegistry.addCommand(new GoCommand(this.world, this.player, this.enigmaMap));
        this.commandRegistry.addCommand(new LookCommand(this.player));
        this.commandRegistry.addCommand(new MapCommand(this.world));
        this.commandRegistry.addCommand(new HelpCommand(this.commandRegistry));
        this.commandRegistry.addCommand(new TakeCommand(this.player));
        this.commandRegistry.addCommand(new InspectCommand(this.player));
        this.commandRegistry.addCommand(new UseCommand(this.player));
        this.commandRegistry.addCommand(new SolveCommand(this.enigmaMap, this.player));
        this.commandRegistry.addCommand(new TeleportCommand(this.player, this.world));
        

        // Commandes bonus
        commandRegistry.addCommand(new SaveCommand(commandHistory));
        commandRegistry.addCommand(new TeleportCommand(player, world));

        // Si chargement de sauvegarde demandé
        if (loadFromSave) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("save.txt"));
            for (String line : lines) {
                ICommand cmd = commandRegistry.getCommand(line);
                if (cmd != null && cmd.hasValidArguments(line)) {
                    cmd.execute(line, this);
                }
            }
            System.out.println("Sauvegarde chargée avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier de sauvegarde.");
        }
        }
    }
    

    public void run() {
        System.out.println("Bienvenue dans le jeu. Tape la commande 'help' pour afficher toutes les commandes disponibles du jeu.");
        System.out.println("Le but du jeu est de pouvoir découvrir toutes les zones grâce aux trois objets placés dans certaines locations ainsi que les énigmes.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            commandHistory.add(input);
            ICommand command = commandRegistry.getCommand(input);

            if (command != null && command.hasValidArguments(input)) {
                command.execute(input, this);
                if (world.allZonesUnlocked()) {
                System.out.println("Félicitations ! Toutes les zones ont été débloquées. Vous avez donc terminé le jeu !");
                break; // termine la boucle → le jeu s'arrête
            }           
            } else {
                System.out.println("Commande invalide.");
            }
        }

        
    }


    public Object getPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayer'");
    }

}
