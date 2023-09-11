package com.Quiz.QuizApp.Service;

import com.Quiz.QuizApp.Entity.Question;
import com.Quiz.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) throws IOException {
        Optional<Question> ques=questionDao.findById(question.getId());
        if(ques.isPresent()){
            throw new IOException("Question Already Exists");
        }
        else {
            try {
                questionDao.save(question);
                return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Data not added", HttpStatus.BAD_REQUEST);
            }
        }
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Question> updateQuestion(Integer id,Question question) throws IOException {
        Optional<Question> ques=questionDao.findById(id);
        if(!ques.isPresent()){
            throw new IOException("Question not exists with id "+id);
        }
        else {
            try {
                return new ResponseEntity<>(questionDao.save(question), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<Question> getQuestion(Integer id) throws IOException {
        Optional<Question> ques=questionDao.findById(id);
        if(!ques.isPresent()){
            throw new IOException("Question not exists with id "+id);
        }
        else {
            return new ResponseEntity<>(questionDao.findById(id).get(), HttpStatus.OK);
        }
    }

    public String deleteQuestion(Integer id) {
        Optional<Question> ques=questionDao.findById(id);
        if(ques.isPresent()){
            questionDao.deleteById(id);
            return "Deleted Successfully";
        }
        else{
            return "Question with id "+id+" not exists";
        }

    }
}
