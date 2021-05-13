package Games;

public class Player{
    private final String name;
    private int games_won = 0;
    private int games_played= 0;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
