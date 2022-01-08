package game;

/**
 * Třída představující mapu lokací herního světa. V datovém atributu
 * {@link #currentLocation} uchovává odkaz na aktuální lokaci, ve které
 * se hráč právě nachází. Z aktuální lokace je možné se prostřednictvím
 * jejích sousedů dostat ke všem přístupným lokacím ve hře.
 * <p>
 * Veškeré informace o stavu hry <i>(mapa prostorů, inventář, vlastnosti
 * hlavní postavy, informace o plnění úkolů apod.)</i> by měly být uložené
 * zde v podobě datových atributů.
 *
 * @author Jan Říha
 * @version ZS-2021, 2021-12-13
 */
public class GameWorld
{
    private Location currentLocation;
    private Inventory inventory;


    /**
     * Konstruktor třídy, vytvoří jednotlivé lokace, propojí je pomocí východů a vloží do nich předměty.
     */
    public GameWorld()
    {
        inventory = new Inventory();
        Location enterence = new Location("vstup", "vstup do  dungeonu, za tebou jsou vrazy kteří hlídají, aby sis to náhodou nerozmyslel");
        Location firstFloor = new Location("prvni_patro", "Toto je les kolem tvého domu, rostou zde houby.");
        Location darkForest = new Location("temny_les", "Toto je temný les, říká se, že tady můžeš potkat vlka.");
        Location treasureRoom = new Location("pokladnice", "Toto je chaloupka, ve které bydlí babička.");

        enterence.addExit(firstFloor);

        firstFloor.addExit(enterence);
        firstFloor.addExit(darkForest);

        darkForest.addExit(firstFloor);
        darkForest.addExit(treasureRoom);

        treasureRoom.addExit(darkForest);

        Item wine = new Item("vino", "Láhev opravdu kvalitního vína.");
        Item babovka = new Item("babovka", "mňam do píči");
        Item table = new Item("stul", "Těžký dubový stůl, vůbec nemá smysl snažit se s ním pohnout.", false);
        Item treasure = new Item("poklad", "jej");


        enterence.addItem(wine);
        enterence.addItem(babovka);
        enterence.addItem(table);
        treasureRoom.addItem(treasure);

        Character zabka = new Character("zabka", "když mi dáš prstýnek ukážu ti čáry", false,true);
        Character pobuda = new Character("pobuda", "toho bych rovnou pobodal, samozřejmě jen pro jistotu",true,false);

        firstFloor.addChar(zabka);
        firstFloor.addChar(pobuda);

        currentLocation = enterence;
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return aktuální lokace
     */
    public Location getCurrentLocation()
    {
        return currentLocation;
    }
    /**
     * Metoda nastaví aktuální lokaci. Používá ji příkaz {@link CommandMove}
     * při přechodu mezi lokacemi.
     *
     * @param currentLocation lokace, která bude nastavena jako aktuální
     */
    public void setCurrentLocation(Location currentLocation)
    {
        this.currentLocation = currentLocation;
    }


    public Inventory getInventory()
    {
        return inventory;
    }


    /**
     * Metoda vrací informaci, zda hráč vyhrál <i>(zda jsou splněné všechny
     * úkoly a podmínky nutné pro výhru)</i>.
     *
     * @return {@code true}, pokud hráč vyhrál; jinak {@code false}
     */
    public boolean isVictorious()
    {
        return inventory.isInInventory("treasure");
    }

}
