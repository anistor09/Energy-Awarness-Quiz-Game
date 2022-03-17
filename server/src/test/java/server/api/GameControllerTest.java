package server.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.sevice.GameService;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock private GameService gameService;
    private GameController underTest;

    @BeforeEach
    void setUp() {
        underTest = new GameController(gameService);
    }

    @Test
    void createGame() {
        underTest.createGame();
        verify(gameService).createGame();
    }
}