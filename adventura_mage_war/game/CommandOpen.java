package game;

public class CommandOpen implements ICommand{
    @Override
    public String getName() {
        return "otevrit";
    }

    @Override
    public String execute(String[] parameters) {
        return null;
    }
}
