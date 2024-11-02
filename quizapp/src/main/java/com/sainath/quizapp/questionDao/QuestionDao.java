package com.sainath.quizapp.questionDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sainath.quizapp.model.Questions;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {

	List<Questions> findByCategory(String category);

	@Query(value="select * from questions where category = :category order by rand() limit :numQ",nativeQuery = true)
	List<Questions> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);
}
