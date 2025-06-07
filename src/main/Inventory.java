package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventory {
    private List<GameObject> items = new ArrayList();
    private int capacity = 10;

    public Inventory() {
    }

    public boolean addItem(GameObject item) {
        if (this.items.size() < this.capacity) {
            this.items.add(item);
            return true;
        } else {
            return false;
        }
    }

    public void removeItem(GameObject item) {
        this.items.remove(item);
    }

    public GameObject getItem(String name) {
        Iterator var2 = this.items.iterator();

        GameObject obj;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            obj = (GameObject) var2.next();
        } while (!obj.getName().equalsIgnoreCase(name));

        return obj;
    }

    public List<GameObject> getItems() {
        return this.items;
    }
    

    public boolean contains(String itemName) {
        for (GameObject item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }
}
