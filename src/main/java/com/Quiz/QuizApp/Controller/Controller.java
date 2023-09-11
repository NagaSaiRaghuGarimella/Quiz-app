package com.Quiz.QuizApp.Controller;

import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Questions")
public class Controller {
    @Autowired
    QuestionService questionService;
    @GetMapping("question")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) throws IOException {
           return questionService.addQuestion(question);
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PutMapping("updateQuestion/{id}")
    public ResponseEntity<Question> updateQuestion( @PathVariable Integer id, @RequestBody Question question) throws IOException {
        return questionService.updateQuestion(id,question);
    }

    @GetMapping("getQuestion/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Integer id) throws IOException {
        return questionService.getQuestion(id);
    }

    @DeleteMapping("deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }
}
