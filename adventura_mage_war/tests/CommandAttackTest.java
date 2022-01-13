package tests;

import game.Character;
import game.Game;
import game.Item;
import game.Location;
import org.junit.jupiter.api.BeforeEach;

public class CommandAttackTest {

    private Game game;

    @BeforeEach
    public void setUp()
    {
        game = new Game();

        Location l1 = new Location("hala", "Toto je vstupní hala Nové budovy VŠE v Praze.");
        Location l2 = new Location("bufet", "Toto je bufet, v případě nouze zde najdeš pohotovostní zásobu krmiva.");
        Item i1 = new Item("sebratelny_item","tenhle předmět jde sebrat",true);
        Item i2 = new Item("nesebratelny_item","tenhle předmět nejde sebrat",false);
        Character ch1 = new Character("nesmrtelny", "tohohle typka nejde zabít a nejde s ním mluvit", false, false);
        Character ch2 = new Character("smrtelny", "tohohle typka jde zabít a jde s ním mluvit", true, true);
        Character ch3 = new Character("nesmrtelny2", "tohohle typka nejde zabít ale jde s ním mluvit", false, true);
        Character ch4 = new Character("smrtelny2", "tohohle typka jde zabít a nejde s ním mluvit", true, false);

        l1.addExit(l2);
        l2.addExit(l1);
        l1.addChar(ch1);
        l1.addChar(ch2);
        l2.addChar(ch1);
        l2.addChar(ch2);


        game.getGameWorld().setCurrentLocation(l1);
    }
}
