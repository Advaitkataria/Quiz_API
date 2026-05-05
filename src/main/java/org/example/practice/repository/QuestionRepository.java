package org.example.practice.repository;

import org.example.practice.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findByDifficulty(String difficulty);
    List<Question> findByCategory(String category);
    List<Question> findByQuestionTitleContaining(String keyword);

    @Query("SELECT q from Question q where q.category = ?1 AND q.difficulty = ?2")
    List<Question> findByCategoryAndDifficulty(String category,String difficulty);

    Page<Question> findByOption1Containing(String keyword, Pageable pageable);
}
