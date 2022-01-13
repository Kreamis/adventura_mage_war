package game;

import java.util.*;

/**
 * Hlavní třída hry. Vytváří a uchovává odkaz na instanci třídy {@link GameWorld}
 * a také vytváří množinu platných příkazů a instance tříd provádějících jednotlivé
 * příkazy.
 * <p>
 * Během hry třída vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Jan Říha
 * @version ZS-2021, 2021-12-13
 */
public class Game
{
    private final ListOfCommands validCommands;
    private boolean gameOver;
    private PlayerRole role;
    private GameWorld world;
    public Inventory inventory;

    private Set<ICommand> commands;

    /**
     * Konstruktor třídy, vytvoří hru a množinu platných příkazů. Hra po
     * vytvoření již běží a je připravená zpracovávat herní příkazy.
     */
    public Game()
    {
        gameOver = false;
        world = new GameWorld();
        validCommands = new ListOfCommands();
        commands = new HashSet<>();
        inventory = new Inventory();

        commands.add(new CommandHelp(this.validCommands, this.getGameWorld()));
        commands.add(new CommandTerminate(this));
        commands.add(new CommandLookAround(this));
        commands.add(new CommandInvestigate(getGameWorld(), this));
        commands.add(new CommandMove(this));
        commands.add(new CommandPick(getGameWorld(),this));
        commands.add(new CommandInventory(getGameWorld()));
        commands.add(new CommandThrowOut(getGameWorld()));
        commands.add(new CommandTalkTo(getGameWorld()));
        commands.add(new CommandAttack(getGameWorld(), this));
        commands.add(new CommandGive(getGameWorld()));
        commands.add(new CommandOpen(getGameWorld(), this));

        validCommands.enterCommand(new CommandHelp(this.validCommands, this.getGameWorld()));
        validCommands.enterCommand(new CommandTerminate(this));
        validCommands.enterCommand(new CommandLookAround(this));
        validCommands.enterCommand(new CommandInvestigate(getGameWorld(), this));
        validCommands.enterCommand(new CommandMove(this));
        validCommands.enterCommand(new CommandPick(getGameWorld(),this));
        validCommands.enterCommand(new CommandInventory(getGameWorld()));
        validCommands.enterCommand(new CommandThrowOut(getGameWorld()));
        validCommands.enterCommand(new CommandTalkTo(getGameWorld()));
        validCommands.enterCommand(new CommandAttack(getGameWorld(), this));
        validCommands.enterCommand(new CommandGive(getGameWorld()));
        validCommands.enterCommand(new CommandOpen(getGameWorld(), this));
    }

    /**
     * Metoda vrací informaci, zda hra již skončila <i>(je jedno, jestli
     * výhrou, prohrou nebo příkazem 'konec')</i>.
     *
     * @return {@code true}, pokud hra již skončila; jinak {@code false}
     */
    public boolean isGameOver()
    {
        return gameOver;
    }

    public PlayerRole getRole() {
        return role;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    /**
     * Metoda nastaví příznak indikující, zda hra skončila. Metodu
     * využívá třída {@link CommandTerminate}, mohou ji ale použít
     * i další implementace rozhraní {@link ICommand}.
     *
     * @param gameOver příznak indikující, zda hra již skončila
     */
    public void setGameOver(boolean gameOver)
    {

        this.gameOver = gameOver;
    }

    /**
     * Metoda vrací odkaz na mapu prostorů herního světa.
     *
     * @return mapa prostorů herního světa
     */
    public GameWorld getGameWorld()
    {
        return world;
    }

    /**
     * Metoda zpracuje herní příkaz <i>(jeden řádek textu zadaný na konzoli)</i>.
     * Řetězec uvedený jako parametr se rozdělí na slova. První slovo je považováno
     * za název příkazu, další slova za jeho parametry.
     * <p>
     * Metoda nejprve ověří, zda první slovo odpovídá hernímu příkazu <i>(např.
     * 'napoveda', 'konec', 'jdi' apod.)</i>. Pokud ano, spustí obsluhu tohoto
     * příkazu a zbývající slova předá jako parametry.
     *
     * @param line text, který hráč zadal na konzoli jako příkaz pro hru
     * @return výsledek zpracování <i>(informace pro hráče, které se vypíšou na konzoli)</i>
     */
    public String processCommand(String line)
    {
        String[] parts = line.split("[ \t]+");

        String commandName = parts[0];
        String[] commandParameters = new String[parts.length - 1];

        for (int i = 0; i < commandParameters.length; i++) {
            commandParameters[i] = parts[i + 1];
        }

        for (ICommand cmd : commands) {
            if (cmd.getName().equals(commandName)) {

               // ICommand command = validCommands.returnCommand(commandName);
                String commandResult = cmd.execute(commandParameters);

                if (world.isVictorious()) {
                    gameOver = true;
                }

                return commandResult;
            }
        }

        return "Tomu nerozumím, příkaz '" + commandName + "' neznám.";
    }

    /**
     * Metoda vrací úvodní text pro hráče, který se vypíše na konzoli ihned po
     * zahájení hry.
     *
     * @return úvodní text
     */
    public String getPrologue()
    {
        return "Vítejte!\n"
        + "Toto je příběh o Červené Karkulce, babičce a vlkovi.\n"
        + "Nevíte-li, jak pokračovat, zadejte příkaz 'napoveda'.\n"
        + "nejprve si urči zda jcheš být mage nebo war";


    }

    /**
     * Metoda vrací závěrečný text pro hráče, který se vypíše na konzoli po ukončení
     * hry. Metoda se zavolá pro všechna možná ukončení hry <i>(hráč vyhrál, hráč
     * prohrál, hráč ukončil hru příkazem 'konec')</i>. Tyto stavy je vhodné
     * v metodě rozlišit.
     *
     * @return závěrečný text
     */
    public String getEpilogue()
    {
        String epilogue = "Díky, že sis zahrál(a).";

        if (world.isVictorious()) {
            epilogue = "Vyhrál(a) jsi, dokázal jsi získat poklad a nyní můžeš splatit jeden z tvých dluhů, dobrá práce.\n\n" + epilogue;
        }

        return epilogue;
    }
}
