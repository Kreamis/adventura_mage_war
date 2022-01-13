package game;

public class CommandThrowOut implements ICommand{

    public GameWorld gameWorld;
    public Inventory inventory;


    public CommandThrowOut(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.inventory = new GameWorld().getInventory();
    }


    @Override
    public String getName() {
        return "vyhodit";
    }

    @Override
    public String execute(String[] parameters) {
        if (parameters.length == 0) {
            return "musíš zadat název předmětu.";
        }
        if (parameters.length >= 2) {
            return "můžeš vyhodit najednou pouze jeden předmět";
        }
        String nameWhatThrow = parameters[0];
        Inventory inventory = gameWorld.getInventory();

        if (inventory.isInInventory(nameWhatThrow) ){
            Item toThrow = inventory.choseItem(nameWhatThrow);
            gameWorld.getCurrentLocation().addItem(toThrow);

            return "vyhodil jsi " + nameWhatThrow;
        }

        return "tento předmět nemáš v inventáři \n";

    }
}
