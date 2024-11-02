package com.sainath.quizapp.questionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sainath.quizapp.model.QuestionWrapper;
import com.sainath.quizapp.model.Questions;
import com.sainath.quizapp.model.Quiz;
import com.sainath.quizapp.model.Response;
import com.sainath.quizapp.questionDao.QuestionDao;
import com.sainath.quizapp.questionDao.QuizDao;

@Service
public class QuizService {

	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	QuizDao quizDao;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		try {
			List<Questions> questions = questionDao.findRandomQuestionsByCategory(category,numQ); 
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(questions);
			quizDao.save(quiz);
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
		try {
			Optional<Quiz> quiz = quizDao.findById(id);
			List<Questions> questions = quiz.get().getQuestions();
			List<QuestionWrapper> questionList = new ArrayList<>();
			for(Questions q:questions) {
				QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getCategory(),q.getDifficultylevel(),
						q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
				questionList.add(qw);
			}
			return new ResponseEntity<>(questionList,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> getEvaluatedResult(int id, List<Response> responses) {
		try {
			Optional<Quiz> quiz = quizDao.findById(id);  // quizDao.findById(id).get();
			List<Questions> questions = quiz.get().getQuestions();
			int result = 0;
			int i = 0;
			for(Response r:responses) {
				if(r.getResponse().equals(questions.get(i).getRightAnswer()))
					result++;
				i++;
			}
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(Integer.MIN_VALUE,HttpStatus.BAD_REQUEST);
	}
	
}
