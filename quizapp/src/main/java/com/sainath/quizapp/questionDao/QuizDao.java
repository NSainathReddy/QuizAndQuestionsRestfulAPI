package com.sainath.quizapp.questionDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sainath.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
