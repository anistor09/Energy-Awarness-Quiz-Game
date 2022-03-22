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

import commons.*;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import org.glassfish.jersey.client.ClientConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
     * This method will get the list of all the activities that exist in the repository
     * @return the list of all activities
     */
    public List<Activity> getActivities() {
        List<Activity> list = ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/activity")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
        return list;
    }

    /**
     * This method will make a put request to the server to update the activity with Id as the id in the provided
     * to match the other fields of the provided activity
     * @param activity with the fields to update
     * @return the updated Activity
     */
    public Activity editActivity(Activity activity) {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/activity")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .put(Entity.entity(activity, APPLICATION_JSON), Activity.class);
    }

    /**
     * This method will retrieve an activity from the server based on the id provided
     * @param id to retrieve the activity
     * @return the retrieved activity
     */
    public Activity getActivityById(String id) {
        Activity activity = ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/activity/" + id)
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
        return activity;
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
        ArrayList<Question> questions = new ArrayList<>();
        questions.addAll(getListMostEnergy());
        questions.addAll(getListInsteadOf());
        questions.addAll(getListMultipleChoice());
        questions.addAll(getListGuessQuestion());
        Collections.shuffle(questions);
        SinglePlayerGame game = new SinglePlayerGame(questions, new ArrayList<JokerCard>(), player);
        return game;
    }


    /**
     * This method will make a request for list of MostEnergyQuestions
     * @return a list of random MostEnergyQuestions from the server
     */
    public List<MostEnergyQuestion> getListMostEnergy() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/singleGame/mostEnergy")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    /**
     * This method will make a request for list of InsteadOf questions
     * @return a list of random InsteafOfQuestion from the server
     */
    public List<InsteadOfQuestion> getListInsteadOf() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/singleGame/insteadOf")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    /**
     * This method will make a request for list of MultipleChoiceQuestion
     * @return a list of random MultipleChoiceQuestion from the server
     */
    public List<MultipleChoiceQuestion> getListMultipleChoice() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/singleGame/multipleChoice")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    /**
     * This method will make a request for list of GuessQuestion
     * @return a list of random GuessQuestion from the server
     */
    public List<GuessQuestion> getListGuessQuestion() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/singleGame/guess")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }
}