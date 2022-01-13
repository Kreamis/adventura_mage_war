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
        if (gameWorld.getCurrentLocation().getName().equals("prvni_patro") && parameters[0].equals("zabka") && !parameters[1].equals("hulka"))
        {

            return "tuhle vec zabka nechce";
        }

        if (gameWorld.getCurrentLocation().getName().equals("prvni_patro") && parameters[0].equals("zabka") && !parameters[1].equals("mec"))
        {

            return "tuhle vec zabka nechce";
        }

        if (gameWorld.getCurrentLocation().getName().equals("prvni_patro") && gameWorld.getInventory().isInInventory("hulka") && parameters[0].equals("zabka") && parameters[1].equals("hulka") && gameWorld.getInventory().itemCapacity())
        {
            gameWorld.getInventory().choseItem("hulka");
            Location pokladnice = gameWorld.getLocation("pokladnice");

            gameWorld.setCurrentLocation(pokladnice);

            return "jsi v pokladnici";
        }

        if (gameWorld.getCurrentLocation().getName().equals("prvni_patro") && gameWorld.getInventory().isInInventory("mec") && parameters[0].equals("zabka") && parameters[1].equals("mec") && gameWorld.getInventory().itemCapacity())
        {
            gameWorld.getInventory().choseItem("mec");
            Location pokladnice = gameWorld.getLocation("pokladnice");

            gameWorld.setCurrentLocation(pokladnice);

            return "jsi v pokladnici";
        }

        return "Někde jsi udělal chybu nikomu jsi nic nedal";
    }
}
