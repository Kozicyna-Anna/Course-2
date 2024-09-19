package pro.sky.course2.service;

import pro.sky.course2.Question;

import java.util.Collection;

public interface QuestionService {

    default Question add(String question, String answer) {
        return this.add(new Question(question, answer));

    }

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
