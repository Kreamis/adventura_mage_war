package game;

/**
 * Třída implementující příkaz pro průzkum konkrétního předmětu.
 *
 * @author Jan Říha
 * @version ZS-2021, 2021-12-19
 */
public class CommandInvestigate implements ICommand
{
    private Game game;
    private GameWorld gameWorld;
    private Inventory inventory;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandInvestigate(GameWorld gameWorld, Game game)
    {
        this.game = game;
        this.gameWorld = gameWorld;
        this.inventory = new GameWorld().getInventory();
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>prozkoumej</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "prozkoumej";
    }

    /**
     * Metoda vrátí detailní popis vybraného předmětu v inventáři nebo
     * v aktuální lokaci.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length == 0) {
            return "musíš zadat predmět";
        }
        if (parameters.length > 1) {
            return "můžeš si prohlídnout jen jeden předmět";
        }

        String nameToInvestigate = parameters[0];
        Inventory inventory = gameWorld.getInventory();

        if (inventory.isInInventory(nameToInvestigate) ){
            return inventory.getItem(nameToInvestigate).getDescription();
        }
        if (gameWorld.getCurrentLocation().containsItem(nameToInvestigate)){
            return gameWorld.getCurrentLocation().getItem(nameToInvestigate).getDescription();
        }

        return "tento item neexistuje";
    }
}
