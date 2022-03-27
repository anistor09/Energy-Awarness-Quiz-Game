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
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

public class ServerUtils {

    private static String SERVER = "http://localhost:8080/";
    private static final String WEBSOCKETSERVER =
            SERVER.replaceAll("http", "ws").replaceAll("https", "ws");
    private static int multiGameIndex;

    public void setSERVER(String SERVER) {
        ServerUtils.SERVER = SERVER;
        SERVER.replaceAll("http", "ws").replaceAll("https", "ws");
    }

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
    public String getServer(){
        return SERVER;
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

    //  MULTIPLAYER GAME LOGIC

    private StompSession session = connect(WEBSOCKETSERVER + "/websocket"); //the StompSession

    /**
     * This method configures the StompSession for the websocket
     * @param url to connect
     * @return the StomSession of the websocket
     */
    private StompSession connect(String url) {
        var client = new StandardWebSocketClient();
        var stomp = new WebSocketStompClient(client);
        stomp.setMessageConverter(new MappingJackson2MessageConverter());
        try {
            return stomp.connect(url, new StompSessionHandlerAdapter() {}).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        throw new IllegalStateException();
    }

    /**
     * This method will retrieve the current MultiGame that has an active lobby, with all the players that are currently
     * in the lobby.
     * @return the MultiPlayerGame Object
     */
    public MultiPlayerGame getCurrentMultiplayerGame() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.addAll(getCurrentMultiGameGuess());
        questions.addAll(getCurrentMultiGameInsteadOf());
        questions.addAll(getCurrentMultiGameMultipleChoice());
        questions.addAll(getCurrentMultiGameMostEnergy());
        return new MultiPlayerGame(questions, new ArrayList<>(),new ArrayList<Player>(getCurrentMultiGamePlayers()));
    }

    /**
     * This method will send the new player through the websocket
     * @param player to send
     */
    public void sendPlayer(Player player) {
        this.send("/app/updateLobby", player);
    }

    /**
     * This method will get the list of MostEnergyQuestions that are in the MultiPlayerGame that is being retrieved
     * @return the list of MostEnergyQuestion
     */
    public List<MostEnergyQuestion> getCurrentMultiGameMostEnergy() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/multiGame/mostEnergy")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    public List<InsteadOfQuestion> getCurrentMultiGameInsteadOf() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/multiGame/insteadOf")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    public List<GuessQuestion> getCurrentMultiGameGuess() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/multiGame/guess")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    public List<MultipleChoiceQuestion> getCurrentMultiGameMultipleChoice() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/multiGame/multipleChoice")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    /**
     * This method will retrieve the list of players that are currently in the MultiGame. Keep in mind more players can
     * join still, and that is why we have the method registerForNewPlayers
     * @return the list of players
     */
    public List<Player> getCurrentMultiGamePlayers() {
        return ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/game/multiGame/players")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    /**
     * This method will listen for a topic in the websocket session with path as the one in the destination. It is
     * expecting Objects of type player.
     * @param dest the topic of the websocket to listen to
     * @param consumer the Consumer that represents the action that this method is supposed to execute when on trigger
     *                 of the topic. This is to be passed as a lambda function
     */
    public void registerForNewPlayers(String dest, Consumer<Player> consumer) {
        if(session.isConnected())
            connect("");
        session.subscribe(dest, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Player.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                consumer.accept((Player) payload);
            }
        });
    }
    /**
     * This method will listen for a topic in the websocket session with path as the one in the destination. It is
     * expecting Objects of type Emoji.
     * @param dest the topic of the websocket to listen to
     * @param consumer the Consumer that represents the action that this method is supposed to execute when on trigger
     *                 of the topic. This is to be passed as a lambda function
     */
    public void registerForEmoji(String dest,Consumer<Emoji> consumer){
        session.subscribe(dest, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Emoji.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                consumer.accept((Emoji) payload);
            }
        });

    }

    /**
     * This method will listen for messages regarding the start of the game. Whenever the server propagates the
     * startGame message on the server it wil
     * @param dest
     * @param consumer
     */
    public void registerForGameStart(String dest, Consumer<Boolean> consumer) {
        session.subscribe(dest, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Boolean.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                consumer.accept((Boolean) payload);
            }
        });
    }


    /**
     * This method will send ,to the websocket destination provided, the object o
     * @param dest the path on the websocket
     * @param o the object to send
     */
    public void send(String dest, Object o) {
        session.send(dest, o);
    }


}