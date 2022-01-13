package game;

public enum PlayerRole {
    MAGE("mage"),
    WAR("war");

    private final String name;

    PlayerRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
