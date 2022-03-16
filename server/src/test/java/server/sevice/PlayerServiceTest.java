package server.sevice;

import commons.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.database.PlayerRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;
    private PlayerService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new PlayerService(playerRepository);
    }

    @Test
    void getPlayers() {
        underTest.getPlayers();
        verify(playerRepository).findAll();
    }

    @Test
    void addPlayer() {
        underTest.addPlayer(null);
        verify(playerRepository).save(null);
    }

    /**
     * This method is currently not testable due to the lack of activities on the database when test is ran.
     */
    @Disabled
    @Test
    void deletePlayer() {

    }
}