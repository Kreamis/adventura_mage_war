package game;

/**
 * Třída implementující příkaz pro sbírání předmětů.
 *
 * @author Jan Říha
 * @version ZS-2021, 2021-12-19
 */
public class CommandPick implements ICommand
{

    private GameWorld gameWorld;
    private Inventory inventory;
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param gameWorld hra, ve které se bude příkaz používat
     */

    public CommandPick(GameWorld gameWorld, Game game)
    {

        this.gameWorld = gameWorld;
        this.inventory = gameWorld.getInventory();
        this.game = game;
    }
    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>seber</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "seber";
    }

    /**
     * Metoda se pokusí sebrat předmět z aktuální lokace a uložit ho do hráčova
     * inventáře. Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný
     * parametr <i>(tj. neznáme požadovaný předmět)</i>, nebo bylo zadáno dva a
     * více parametrů <i>(tj. hráč chce sebrat více předmětů současně)</i>, vrátí
     * chybové hlášení. Pokud byl zadán právě jeden parametr, zkontroluje, zda
     * v aktuální lokaci je předmět s daným názvem, zda je přenositelný a zda
     * na něj hráč má v inventáři místo <i>(tj. zda ho lze sebrat)</i>. Pokud ne,
     * vrátí chybové hlášení. Pokud všechny kontroly proběhnou v pořádku, provede
     * přesun předmětu z lokace do inventáře a vrátí informaci o sebrání předmětu.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if(gameWorld.getCurrentLocation().getName().equals("pokladnice")&&gameWorld.getCurrentLocation().getItem("poklad").equals("poklad"))
       {
           gameWorld.isVictorious();
            game.setGameOver(true);
        }
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám sebrat.";
        }
        
        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím sebrat více věcí současně.";
        }
        String itemName = parameters[0];
        Location currentLocation = gameWorld.getCurrentLocation();
        Item item = currentLocation.returnItem(itemName);
        
        if (!currentLocation.containsItem(itemName)) {
            return "Předmět '" + itemName + "' tady není.";
        }
        
        if (!item.isPickable()) {
            return itemName + " nemůžeš sebrat.";
        }

        if(!inventory.itemCapacity()){
            return "už toho víc neuneseš";
        }

        inventory.addItem(item);
        if(parameters.length == 1 && gameWorld.getCurrentLocation().containsItem(parameters[0])){
            gameWorld.getCurrentLocation().removeItem((parameters[0]));
            return "sebral jsi " + parameters[0];
        }

        return "Tento předmět neznám";
    }
}
