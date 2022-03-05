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
package server.api;

import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commons.Quote;
import server.database.QuoteRepository;
/**
 * API layer of the Quote class.
 */
@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final Random random;
    private final QuoteRepository repo;

    /**
     *
     * Creates an instance of QuoteController Class.
     * @param random An instance of Random class.
     * @param repo   An instance of the repository class.
     */
    public QuoteController(Random random, QuoteRepository repo) {
        this.random = random;
        this.repo = repo;
    }

    /**
     * Serves the user's GET request by invoking the findAll() method from the repository class.
     * @return A list of Quote class returned by the Repository Class.
     */
    @GetMapping(path = { "", "/" })
    public List<Quote> getAll() {
        return repo.findAll();
    }

    /**
     * Serves the user's GET request by invoking the getById() method from the repository class.
     * @param id An int parameter that is the id of the Quote we want to retrieve from the database.
     * @return An instance of ResponseEntity that contains the Quote with the specified id if it exists in the database
     * or a bad request otherwise
     */

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getById(@PathVariable("id") long id) {
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.getById(id));
    }

    /**
     * API layer method for the POST request
     * @param quote An instance of the Quote class that should be inserted in the database.
     * @return An instance of ResponseEntity that contains a Quote if it was successfully inserted in the database or
     * a badRequest otherwise
     */

    @PostMapping(path = { "", "/" })
    public ResponseEntity<Quote> add(@RequestBody Quote quote) {

        if (quote.person == null || isNullOrEmpty(quote.person.firstName) || isNullOrEmpty(quote.person.lastName)
                || isNullOrEmpty(quote.quote)) {
            return ResponseEntity.badRequest().build();
        }

        Quote saved = repo.save(quote);
        return ResponseEntity.ok(saved);
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    /**
     * Serves the user's GET request by invoking the random.nextInt() from the Random class.
     * @return An instance of ResponseEntity that contains a Quote if a Quote with that random id exists
     * in the database
     */

    @GetMapping("rnd")
    public ResponseEntity<Quote> getRandom() {
        var idx = random.nextInt((int) repo.count());
        return ResponseEntity.ok(repo.getById((long) idx));
    }
}