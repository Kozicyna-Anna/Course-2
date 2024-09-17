package service;

import org.springframework.stereotype.Service;
import pro.sky.course2.Question;

import java.util.*;

import static java.util.Collections.unmodifiableCollection;

@Service
public class JavaQuestionService implements QuestionService {

    private static final Random RANDOM = new Random();

    private final Set<Question> questionSet = new HashSet<>();

    @Override
    public Question add(Question question) {
        questionSet.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
         questionSet.remove(question);
        return question;
    }
    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questionSet);
    }
    @Override
    public Question getRandomQuestion() {
        if (questionSet.isEmpty()) {
            throw new IllegalStateException("Хранилище с вопросами пустое!");
        }

        int questionIndex = RANDOM.nextInt(questionSet.size());
        return new ArrayList<>(questionSet).get(questionIndex);
    }
}
