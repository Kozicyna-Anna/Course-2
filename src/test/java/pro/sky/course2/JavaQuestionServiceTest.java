package pro.sky.course2;

import org.junit.jupiter.api.Test;
import service.JavaQuestionService;
import service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @Test
    void addTest() {
        int questionCount = questionService.getAll().size();

        questionService.add(new Question(null, null));
        questionService.add(new Question(null, ""));
        questionService.add(new Question("", ""));

        int actualQuestionCount = questionService.getAll().size();
        int expectedQuestionCount = questionCount + 3;
        assertEquals(expectedQuestionCount, actualQuestionCount);
    }

    @Test
    void removeTest() {

        questionService.add(new Question(null, null));
        questionService.add(new Question(null, ""));
        questionService.add(new Question("", ""));

        int questionCount = questionService.getAll().size();

        Set<Question> questionToRemove = questionService.getAll()
                .stream()
                .limit(questionCount - 1)
                .collect(Collectors.toSet());

        questionToRemove.forEach(questionService::remove);

        Collection<Question> actualQuestions = questionService.getAll();
        assertEquals(1, actualQuestions.size());

        boolean isRemovingCorrect = actualQuestions.stream()
                .noneMatch(questionToRemove:: contains);
        assertTrue (isRemovingCorrect);
    }
    @Test
    void getRandomQuestionTest(){

        Set<Question> questionToRemove = new HashSet<>(questionService.getAll());

        questionToRemove.forEach(questionService::remove);

        assertThrows(IllegalStateException.class, questionService::getRandomQuestion);
    }

}
