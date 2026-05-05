package org.example.practice.exception;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(int id) {

        super("Question with id: " + id + " not found!");
    }
}
