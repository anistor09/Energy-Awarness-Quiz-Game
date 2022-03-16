package server.sevice;

import commons.SinglePlayerGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.SinglePlayerGameRepository;

import java.util.List;

@Service
public class SinglePlayerGameService {


    private SinglePlayerGameRepository singlePlayerGameRepository;

    @Autowired
    public SinglePlayerGameService(SinglePlayerGameRepository singlePlayerGameRepository) {
        this.singlePlayerGameRepository = singlePlayerGameRepository;
    }


    public List<SinglePlayerGame> getSinglePlayerGames() {
        return singlePlayerGameRepository.findAll();
    }

    public void addSinglePlayerGame(SinglePlayerGame singlePlayerGame) {
        singlePlayerGameRepository.save(singlePlayerGame);
    }

    /**
     * Deletes a game from the repository if it exists
     * @param gameId ID of the game to be  deleted
     */
    public void deleteSinglePlayerGame(Long gameId) {
        // check if this playerId exists
        boolean exists = singlePlayerGameRepository.existsById(gameId);
        if (!exists) {
            throw new IllegalStateException("Single-player game with ID " + gameId + " does not exist.");
        }

        singlePlayerGameRepository.deleteById(gameId);
    }
}
