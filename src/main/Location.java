package main;

import java.util.ArrayList;
import java.util.List;
import utils.IPrintable;

public class Location implements IPrintable {
    private String name;
    private String description;
    private boolean isOpen;
    private List<GameObject> objects = new ArrayList();


    public Location(String name, String description, boolean isOpen) {
        this.name = name;
        this.description = description;
        this.isOpen = isOpen;
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

    public List<GameObject> getObjects() {
        return this.objects;
    }

    public void addObject(GameObject obj){
        this.objects.add(obj);

    }

    @Override
    public String getPrintableString() {
        if (!isOpen) return "LACHE CA"; // si la zone est verrouillée

        String fullName = name;

        if (!objects.isEmpty()) {
            fullName += "*"; // indique la présence d’un objet
        }

        return fullName;
    }

    @Override
    public boolean isGrayedOut() {
        return !isOpen;
    }
}
