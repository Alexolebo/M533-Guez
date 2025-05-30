package main;

public class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name.toLowerCase();
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return name;
    }
}
