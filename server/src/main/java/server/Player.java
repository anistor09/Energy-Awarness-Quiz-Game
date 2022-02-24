package server;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    private Long id;
    private String username;
    private int currentScore;

    public Player(String username, int currentScore) {
        this.username = username;
        this.currentScore = currentScore;
    }

    public Player(Long id, String username, int currentScore) {
        this.id = id;
        this.username = username;
        this.currentScore = currentScore;
    }

    public Player() {
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
