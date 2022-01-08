package game;

public class Character implements Comparable<Character> {
    private String name;
    private String text;
    private boolean killable;
    private boolean talkable;


    public Character(String name, String text, boolean killable, boolean untalkable)
    {
        this.name = name;
        this.text = text;
        this.killable = killable ;
        this.talkable = untalkable ;
    }

    public String getCharName()
    {
        return name;
    }

    public String getCharText()
    {
        return text;
    }

    public boolean isKillable()
    {
        return killable;
    }

    public boolean isTalkable()
    {
        return talkable;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof Character) {
            Character character = (Character) o;

            return name.equals(character.getCharName());
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public int compareTo(Character character)
    {
        return name.compareTo(character.getCharName());
    }
}

