package pro.sky.course2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ExaminerServiceImpl;
import service.QuestionService;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    private static final Random RANDOM = new Random();

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @ParameterizedTest
    @ValueSource(ints = {8, 100})
    void getQuestionTest(int amount) {

        Collection<Question> questionCollection = IntStream.generate(RANDOM::nextInt)
                        .limit(amount)
                                .boxed()
                                        .map(Objects::toString)
                                                .map(s -> new Question(s, s))
                                                        .toList();

        when(questionService.getAll())
                .thenReturn(questionCollection);

        when(questionService.getRandomQuestion());

    }
    @ParameterizedTest
    @ValueSource(ints = {3, 10})
    void getQuestionsNegativeTest(int amount) {

        Collection<Question> questionCollection = IntStream.generate(RANDOM::nextInt)
                        .limit(amount - 1)
                                .boxed()
                                        .map(Objects::toString)
                                                .map(s -> new Question(s, s))
                                                        .toList();
        when(questionService.getAll())
                .thenReturn(questionCollection);

        assertThrows(IncorrectQuestionAmountException.class, () ->
                        examinerService.getQuestions(amount));
    }
}
