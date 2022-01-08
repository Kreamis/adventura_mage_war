package game;

public class CommandAttack implements ICommand{

    public GameWorld gameWorld;
    public Game game;

    public CommandAttack(GameWorld gameWorld, Game game) {
        this.gameWorld = gameWorld;
        this.game = game;
    }

    @Override
    public String getName() {
        return "zautoc_na";
    }

    @Override
    public String execute(String[] parameters) {
        if (parameters.length == 0) {
            return "nevím na koho zaútočit";
        }
        else if (parameters.length != 1) {
            return "nemůžeš zaútočit na víc osob najednou";
        }
        String nameOfChar = parameters[0];
        Location currentLocation = gameWorld.getCurrentLocation();
        Character character = currentLocation.returnChar(nameOfChar);

        if (character == null) {
            return "tato osoba tu není";
        }
        if (!character.isKillable()){
            return "nemůžeš zabít tuto osobu";
        }
        if(gameWorld.getCurrentLocation().holdingChar(parameters[0])){
            gameWorld.getCurrentLocation().removeChar((parameters[0]));
            return "právě jsi zabil " + parameters[0];
        }
        return "tuto osobu neznám.";
    }
}
