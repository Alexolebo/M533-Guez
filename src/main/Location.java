package main;

import java.util.ArrayList;
import java.util.List;
import utils.IPrintable;

public class Location implements IPrintable {
    private String name;
    private String description;
    private boolean isOpen;
    private List<Item> items;

    public Location(String name, String description, boolean isOpen) {
        this.name = name;
        this.description = description;
        this.isOpen = isOpen;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void unlock() {
        this.isOpen = true;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public String getPrintableString() {
        return isOpen ? " O " : " X ";
    }

    @Override
    public boolean isGrayedOut() {
        return false;
    }
}
