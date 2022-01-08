package game;

public class CommandGive implements ICommand{
    public GameWorld gameWorld;
    public String treasureName = "pokladnice";

    public String getTreasureName()
    {
        return treasureName;
    }


    public void setTreasureName(String treasureName)
    {
        this.treasureName = treasureName;
    }

    public CommandGive(GameWorld gameWorld) {

        this.gameWorld = gameWorld;

    }

    @Override
    public String getName() {
        return "dat";
    }

    @Override
    public String execute(String[] parameters) {
        if (parameters.length == 0) {
            return "musíš zadat 2 parametry - jmeno postavy a název předmětu";
        }
        if (parameters.length == 1) {
            return "Příkaz musí mít právě dva parametry, jméno postavy a název věci";
        }
        if (parameters.length >= 3) {
            return "v jednu chvíli můžeš dát pouze jednu věc";
        }
        if (gameWorld.getCurrentLocation().getName().equals("prvni_patro") && gameWorld.getInventory().isInInventory("vino") && parameters[0].equals("zabka") && parameters[1].equals("vino") && !gameWorld.getInventory().itemCapacity())
        {
            return "máš moc věcí něco vyhoď";
        }
        if (gameWorld.getCurrentLocation().getName().equals("prvni_patro") && gameWorld.getInventory().isInInventory("vino") && parameters[0].equals("zabka") && parameters[1].equals("vino") && gameWorld.getInventory().itemCapacity())
        {
            gameWorld.getInventory().choseItem("vino");
            String exitName = "pokladnice";

            Location exit = getTreasureName();
            gameWorld.setCurrentLocation(exit);

            return "dal jsi zlatou sošku obchodníkovy a dostal jsi zvláštní meč";
        }

        return "Nic se nestalo, nikomu jsi nic nedal";
    }
}
