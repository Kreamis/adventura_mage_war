package game;

public class CommandOpen implements ICommand{
    public GameWorld gameWorld;
    public Inventory inventory;
    public Game game;


    public CommandOpen(GameWorld gameWorld, Game game) {
        this.gameWorld = gameWorld;
        this.inventory = gameWorld.getInventory();
        this.game = game;

    }
    @Override
    public String getName() {
        return "otevrit";
    }

    @Override
    public String execute(String[] parameters) {

        if(parameters.length<1) {
            return "musíš zadat co otevřít";
        }
        if(parameters.length>1){
            return "můžeš otevřít jen jednu věc";

        }
        if(parameters[0].equals("bedna"))
            if (inventory.isInInventory("klic")) {
                System.out.println("otevřel jsi truhlu");
                if (game.getRole().equals(PlayerRole.MAGE)) {
                    Item mec = new Item("mec", "božský meč pro božské bojovníky.");
                    gameWorld.getCurrentLocation().addItem(mec);
                    gameWorld.getCurrentLocation().removeItem("bedna");
                    return "našel jsi naprosto liboví meč, škoda že tobě je k ničemu :/, ale možná bude mít jiné využití";
                }
                if (game.getRole().equals(PlayerRole.WAR)) {
                    Item hulka = new Item("hulka", "božská hůlka pro božský magiče.");
                    gameWorld.getCurrentLocation().addItem(hulka);
                    gameWorld.getCurrentLocation().removeItem("bedna");
                    return "našel jsi naprosto libovou hůlku, škoda že tobě je k ničemu :/, ale možná bude mít jiné využití";
                }
                return "nemáš klíč od bedny";
            }


            return "v této hře lze otevřít pouze předmět 'bedna'";

    }
}
