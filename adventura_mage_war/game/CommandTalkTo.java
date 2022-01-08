package game;

public class CommandTalkTo implements ICommand{

    private GameWorld gameWorld;

    public CommandTalkTo(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public String getName() {
        return "promluvit_s";
    }

    @Override
    public String execute(String[] parameters) {
        if (parameters.length == 0) {
            return "musíš zadat osobu";
        }
        if (parameters.length > 1) {
            return "můžeš si promluvit pouze s jednou postavou";
        }

        String name = parameters[0];
        Location currentLocation = gameWorld.getCurrentLocation();
        Character character = currentLocation.choseChar(name);

        if (!character.isTalkable()) {
            return "s tímhle bych radši nemluvil";
        }
        if (character == null) {
            return "tato osoba zde není";
        }
        else {
            if (parameters.length == 1 && gameWorld.getCurrentLocation().holdingChar(parameters[0])) {
                return character.getCharText();
            }
        }
        return "tato osoba neexistuje";
    }
}
