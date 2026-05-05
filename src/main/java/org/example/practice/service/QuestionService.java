package org.example.practice.service;

import org.example.practice.exception.QuestionNotFoundException;
import org.example.practice.repository.QuestionRepository;
import org.example.practice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }
    public Question getQuestionById(Integer id){
        return questionRepository.findById(id).orElseThrow(()-> new QuestionNotFoundException(id));

    }
    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }
    public void deleteQuestion(int id){
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException(id);
        }
        questionRepository.deleteById(id);
    }
    public Question updateQuestion(Integer id, Question updatedQuestion){
        Question q = questionRepository.findById(id).orElseThrow(()-> new QuestionNotFoundException(id));
            q.setQuestionTitle(updatedQuestion.getQuestionTitle());
            q.setOption1(updatedQuestion.getOption1());
            q.setOption2(updatedQuestion.getOption2());
            q.setOption3(updatedQuestion.getOption3());
            q.setOption4(updatedQuestion.getOption4());
            q.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
            q.setDifficulty(updatedQuestion.getDifficulty());
            q.setCategory(updatedQuestion.getCategory());
        return questionRepository.save(q);
    }

    public List<Question> getQuestionByDifficulty(String difficulty){
        return questionRepository.findByDifficulty(difficulty);
    }
    public List<Question> getQuestionByCategory(String category){
        return questionRepository.findByCategory(category);
    }
    public List<Question> searchByKeyword(String keyword){
        return questionRepository.findByQuestionTitleContaining(keyword);
    }
    public List<Question> findByCategoryAndDifficulty(String category,String difficulty){
        return questionRepository.findByCategoryAndDifficulty(category,difficulty);
    }
    public Page<Question> findByOption(String option,int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.findByOption1Containing(option,pageable);
    }
}
