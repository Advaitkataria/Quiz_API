package org.example.practice.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.example.practice.model.Question;
import org.example.practice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id){
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@Valid @RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable Integer id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id,@Valid@RequestBody Question updatedQuestion){
        return new ResponseEntity<>(questionService.updateQuestion(id,updatedQuestion),HttpStatus.OK);
    }
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Question>> findQuestionByDifficulty(@PathVariable String difficulty){
        return new ResponseEntity<>(questionService.getQuestionByDifficulty(difficulty),HttpStatus.OK);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> findQuestionByCategory(@PathVariable @RequestParam String category){
        return  new ResponseEntity<>(questionService.getQuestionByCategory(category),HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Question>> findByKeyword(@RequestParam String keyword){
        return new ResponseEntity<>(questionService.searchByKeyword(keyword),HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Question>> findByCategoryAndDifficulty(@RequestParam String category, @RequestParam String difficulty){
        return new ResponseEntity<>(questionService.findByCategoryAndDifficulty(category,difficulty),HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<Page<Question>> findByOption(@RequestParam String option, @RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(questionService.findByOption(option, page, size),HttpStatus.OK);
    }
}
