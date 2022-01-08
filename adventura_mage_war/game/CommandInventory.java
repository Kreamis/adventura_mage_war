package game;

public class CommandInventory implements ICommand{
    private GameWorld gameWorld;
    public CommandInventory(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    @Override
    public String getName() {
        return "inventar";
    }

    @Override
    public String execute(String[] parameters) {
        return gameWorld.getInventory().NamesOfItems();
    }
}
