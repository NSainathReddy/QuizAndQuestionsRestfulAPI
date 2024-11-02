package com.sainath.quizapp.questionController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sainath.quizapp.model.QuestionWrapper;
import com.sainath.quizapp.model.Response;
import com.sainath.quizapp.questionService.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title) {
		return quizService.createQuiz(category,numQ,title);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable int id) {
		return quizService.getQuizById(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> getEvaluatedResult(@PathVariable int id,@RequestBody List<Response> responses) {
		return quizService.getEvaluatedResult(id,responses);
	}
	
}
