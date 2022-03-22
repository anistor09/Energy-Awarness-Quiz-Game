package server.sevice;

import commons.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.api.QuestionController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock private QuestionController questionController;
    private GameService underTest;

    @BeforeEach
    void setUp() {
        underTest = new GameService(questionController);
    }


    boolean containsDuplicates(List<Question> list) {
        Set<Question> set = new HashSet<>();
        set.addAll(list);
        if(set.size() != list.size()) return true;
        return false;
    }
}