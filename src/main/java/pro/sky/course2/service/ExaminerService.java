package pro.sky.course2.service;

import pro.sky.course2.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
