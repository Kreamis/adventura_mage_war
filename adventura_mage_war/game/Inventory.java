package game;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final int CAPACITY = 2;
    private Map<String, Item> items;
    private GameWorld gameWorld;
    private Location location;

    public Inventory()
    {
        items = new HashMap<String, Item>();
    }

    public boolean itemCapacity(){
        if((items.size()< CAPACITY)){
            return true;
        }
        return false;
    }
    public boolean addItem (Item item) {
        if (itemCapacity()){
            items.put(item.getName(),item);
            return true;
        }
        return false;
    }
    public String NamesOfItems () {
        int remains = CAPACITY - items.size();
        String nazvy = "inventář [ ";
        if (items.isEmpty()){
            return nazvy + "nic je prázdný ] -> můžeš sebrat ještě " + remains + " předmět(y)";
        }
        for (String jmenoVeci : items.keySet()){

            nazvy += jmenoVeci + "," ;

        }
        if (remains > 0){
            return nazvy + " ]> můžeš sebrat další " + remains + " předmět(y)";}
        return nazvy + " ] -> inventář je plný";
    }

    public Item getItem(String name)
    {
        return items.get(name);
    }

    public Item choseItem (String name) {
        Item foundedItem;
        if (items.containsKey(name)) {
            foundedItem = items.get(name);
            items.remove(name);

            return foundedItem;

        }
        return null;
    }



    public boolean isInInventory (String name) {
        return items.containsKey(name);

    }


}
