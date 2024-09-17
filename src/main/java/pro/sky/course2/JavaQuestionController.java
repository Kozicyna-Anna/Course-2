package pro.sky.course2;

import org.springframework.web.bind.annotation.*;
import service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam("QuestionText") String question,
                        @RequestParam("QuestionAnswer") String answer) {
        return this.questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam("QuestionText") String question,
                           @RequestParam("QuestionAnswer") String answer) {
        return this.questionService.remove(new Question(question, answer));
    }
    @GetMapping
    public Collection<Question>getAll() {
     return this.questionService.getAll();
    }
}
