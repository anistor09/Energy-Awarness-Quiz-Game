/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package client.utils;

import commons.Game;
import commons.Player;
import commons.Quote;
import commons.SinglePlayerGame;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import org.glassfish.jersey.client.ClientConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

public class ServerUtils {

    private static final String SERVER = "http://localhost:8080/";

    /**
     * This method gets the quotes from the url
     * @throws IOException = In case the input is wrong
     */
    public void getQuotesTheHardWay() throws IOException {
        var url = new URL("http://localhost:8080/api/quotes");
        var is = url.openConnection().getInputStream();
        var br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    public List<Quote> getQuotes() {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/quotes") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(new GenericType<List<Quote>>() {});
    }

    public Quote addQuote(Quote quote) {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/quotes") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(quote, APPLICATION_JSON), Quote.class);
    }

    /**
     * This method will make a simple request for the leaderboard to the server
     * @return the List of players representing the leaderboard
     */

    public List<Player> getLeaderboard() {
        List<Player> leaderboard = ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/player")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<List<Player>>() {});
        return leaderboard;
    }

    /**
     * This method will make a POST request to the server with the new Player
     * @param player the Player to add to the leaderboard/database
     * @return the same Player in case it is needed
     */

    public Player addPlayer(Player player) {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/player")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .post(Entity.entity(player, APPLICATION_JSON), Player.class);
    }

    /**
     * This method will make a request for the server to get a new Game with all the needed attributes
     * Keep in mind that the player in the game is the one you provide and the list of jokers will be empty
     * @param player to add to the game
     * @return the Game object
     */

    public Game createSinglePlayerGame(Player player) {
        SinglePlayerGame game = ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/singleGame")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
        game.setPlayer(player);
        return game;
    }
}