package commons;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Entity
@Table
public class Player {
    /**
     * GenerationType.SEQUENCE : Hibernate requests the primary key value from a database sequence.
     * @SequenceGenerator annotation lets us define the name of the generator, the schema
     * of the database sequence and the allocation size of the sequence.
     */
    @Id
    @SequenceGenerator(name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "player_sequence")
    private Long id;
    private String username;
    private int currentScore;

    /**
     * Constructor for a player without ID parameter
     * @param username Player's username
     * @param currentScore Player's score
     */
    public Player(String username, int currentScore) {
        this.username = username;
        this.currentScore = currentScore;
    }

    /**
     * Constructor for a player. Creates a player with all given parameters
     * @param id Player's ID in the database (primary key)
     * @param username Player's username
     * @param currentScore Player's score
     */
    public Player(Long id, String username, int currentScore) {
        this.id = id;
        this.username = username;
        this.currentScore = currentScore;
    }

    /**
     * Empty constructor for a player
     */
    public Player() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return currentScore == player.currentScore && id.equals(player.id) && username.equals(player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, currentScore);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", currentScore=" + currentScore +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
