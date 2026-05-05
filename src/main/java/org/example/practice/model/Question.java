package org.example.practice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Question Title cannot be empty")
    private String questionTitle;
    @NotBlank(message = "Option1 cannot be empty")
    private String option1;
    @NotBlank(message = "Option2 Title cannot be empty")
    private String option2;
    @NotBlank(message = "Option3 Title cannot be empty")
    private String option3;
    @NotBlank(message = "Option4 Title cannot be empty")
    private String option4;
    @NotBlank(message = "Correct Answer Title cannot be empty")
    private String correctAnswer;
    @Pattern(regexp = "Hard|Medium|Easy")
    private String difficulty;
    @NotBlank(message = "Category Title cannot be empty")
    private String category;

}
