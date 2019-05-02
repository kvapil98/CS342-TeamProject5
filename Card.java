    public class Card {
    private Superhero heroname;
    private int attack;
    private int defense;
    private int special;

    // Contructor, getters, and setters here

    private static final int MIN_DEFENSE = 70;
    private static final int MIN_ATTACK = 70;
    private static final int MIN_SPECIAL = 70;

    private static final int MAX_DEFENSE = 100;
    private static final int MAX_ATTACK = 100;
    private static final int MAX_SPECIAL = 100;

    public enum Superhero
    {
        IronMan, Thor, Vision, Hulk, Superman,

        Batman, Spiderman, Venom, Loki, Ultron,

        GreenLantern, CaptainAmerica, LexLuthor, Magneto, Joker,

        Flash, Wolverine, BlackPanther, Aquaman, Antman,

        Thanos, Doomsday, CaptainMarvel, Deadpool, ScareCrow,

        Darkseid, Bane, Catwoman, BlackWidow, WonderWoman,

        Carnage, Saitaman, Vegeta, Goku, Naruto,

        Jiren, Beerus, Whis, Rorschach, Shazam,

        DrManhattan, Storm, ProfessorX, MartianManhunter, Gamora,

        Raven, Michealangelo, Leonardo, Raphael, Donatello ;
    }


    public Card(Superhero name, int attack, int defense, int special)
    {
        setName(name);
        setAttack(attack);
        setDefense(attack);
        setSpecial(attack);
    }

    Card(){};


    public void setAttack(int attack)
    {
        if (attack < MIN_ATTACK || attack > MAX_ATTACK)
            throw new RuntimeException(
                    String.format("Invalid attack: %d (must be between %d and %d inclusive)",
                            attack, MIN_ATTACK, MAX_ATTACK));
        this.attack = attack;
    }

    public void setDefense(int defense)
    {
        if (defense < MIN_DEFENSE || defense > MAX_DEFENSE)
            throw new RuntimeException(
                    String.format("Invalid defense: %d (must be between %d and %d inclusive)",
                            defense, MIN_DEFENSE, MAX_DEFENSE));
        this.defense = defense;
    }

    public void setSpecial(int special)
    {
        if (special < MIN_SPECIAL || special > MAX_SPECIAL)
            throw new RuntimeException(
                    String.format("Invalid special: %d (must be between %d and %d inclusive)",
                            special, MIN_SPECIAL, MAX_SPECIAL));
        this.special = special;
    }

    public void setName(Superhero hero)
    {
        if (hero == null)
            throw new RuntimeException("Name must be non-null");
        this.heroname = hero;
    }


    public Superhero getName()
    {
        return heroname;
    }


    public int getDefense()
    {
        return defense;
    }


    public int getAttack()
    {
        return attack;
    }


    public int getSpecial()
    {
        return special;
    }

}
