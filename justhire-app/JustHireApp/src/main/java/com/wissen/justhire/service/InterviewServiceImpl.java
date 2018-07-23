package com.wissen.justhire.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.justhire.exception.MaxQuestionsRechedException;
import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.QuestionsAsked;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.ProcessStatusRepository;
import com.wissen.justhire.repository.QuestionAskedRepository;
import com.wissen.justhire.repository.QuestionRepository;
import com.wissen.justhire.repository.RoundRepository;
import com.wissen.justhire.repository.SystemAttributeRepository;
import com.wissen.justhire.web.AnswerForm;
import com.wissen.justhire.web.ResponseMsg;

import antlr.CSharpNameSpace;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private SystemAttributeRepository systemAttributeRepository;

	@Autowired
	private ProcessStatusRepository processStatusRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionAskedRepository questionAskedRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private RoundRepository roundRepository;

	private String getLowerDifficultyQuestion(String currentDifficulty) {
		if (currentDifficulty.equals("Easy"))
			return "Easy";
		else if (currentDifficulty.equals("Medium"))
			return "Easy";
		else
			return "Medium";
	}

	private String getHigherDifficultyQuestion(String currentDifficulty) {
		if (currentDifficulty.equals("Easy"))
			return "Medium";
		else if (currentDifficulty.equals("Medium"))
			return "Hard";
		else
			return "Hard";
	}

	private Question getQuestion(String difficulty, Round round, Candidate candidate) {
		int questionNo = -1;
//		System.out.println("hello");
		List<Question> questions = questionRepository.findNext(difficulty, round, candidate.getExperience());
//		System.out.println("hello"+questions.size());
//		for(Question a: questions)
//			System.out.println(a.getQuestion());

		List<QuestionsAsked> questionsAskedRepoList = questionAskedRepository.findByCandidate(candidate);

//		for(QuestionsAsked a: questionsAskedRepoList)
//			System.out.println(a.getQuestion());

		List<Question> questionsAsked = new ArrayList<>();

		for (QuestionsAsked asked : questionsAskedRepoList) {
			questionsAsked.add(asked.getQuestion());
		}

		questions.removeAll(questionsAsked);
		if (questions.isEmpty())
			return null;
		questionNo = (int) (Math.random() * (questions.size() - 1));

		return questions.get(questionNo);
	}

	@Override
	public int getMinimumQuestionThreshold() {
		return systemAttributeRepository.findById(1).get().getMinimumQuestions();
	}

	@Override
	public List<Candidate> viewPendingCandidates(int round) {
		return processStatusRepository.viewPendingCandidate(round);
	}

	@Override
	public void startInterview(int candidateId) {
		processStatusRepository.updateStatus("Ongoing", candidateId);
	}

	@Override
	public void submitAnswer(AnswerForm answerForm) {
		QuestionsAsked questionsAsked = new QuestionsAsked();
		questionsAsked.setCandidate(candidateRepository.findById(answerForm.getCandidateId()).get());
		questionsAsked.setQuestion(questionRepository.findById(answerForm.getQuestionId()).get());
		questionsAsked.setRound(roundRepository.getRound(answerForm.getRoundId()));
		questionsAsked.setComment(answerForm.getComment());
		questionsAsked.setScore(answerForm.getScore());
		questionAskedRepository.save(questionsAsked);

	}

	@Override
	public Question firstQuestion(Candidate candidate, Round round) {
		return getQuestion("Easy", round, candidate);
	}

	@Override
	public Question nextQuestion(int candidateId, int roundId) {

		Candidate candidate = candidateRepository.findById(candidateId).get();
		Round round = roundRepository.getRound(roundId);

		List<QuestionsAsked> questionsAskeds = questionAskedRepository.getPreviousQuestion(candidate);
		String currentDifficulty = questionsAskeds.get(0).getQuestion().getDifficulty();
		String nextDifficulty;
		float score = questionsAskeds.get(0).getScore();
		Question nextQuestion = null;

		if (score == 0 || score == 4) {
			nextDifficulty = getLowerDifficultyQuestion(currentDifficulty);
		} else if (score == 7) {
			nextDifficulty = currentDifficulty;
		} else {
			nextDifficulty = getHigherDifficultyQuestion(currentDifficulty);
		}

		while (nextQuestion == null) {
			nextQuestion = getQuestion(nextDifficulty, round, candidate);
			if (nextQuestion == null) {
				if (nextDifficulty == "Hard")
					throw new MaxQuestionsRechedException("Max Questions Limit Reached");
				else {
					nextDifficulty = getHigherDifficultyQuestion(nextDifficulty);
				}
			}
		}
		return nextQuestion;
	}

//	difficulty in ascending order
	@Override
	public ResponseMsg stopInterview(int candidate, int currentRound) {
		int easyCount = 0, mediumCount = 0, hardCount = 0;
		float easyScore = 0, mediumScore = 0, hardScore = 0;
		Optional<Candidate> candidate1 = candidateRepository.findById(candidate);
		Optional<Round> round = roundRepository.findById(currentRound);
		System.out.println("Current Round "+currentRound);

		List<QuestionsAsked> questionsList = questionAskedRepository.getPreviousQuestion(candidate1.get()); // write
																											// Query

		for (QuestionsAsked asked : questionsList) {
			if (asked.getQuestion().getDifficulty() == "Easy") {
				easyCount++;
				easyScore += asked.getScore();
			} else if (asked.getQuestion().getDifficulty() == "Medium") {
				mediumCount++;
				mediumScore += asked.getScore();
			} else {
				hardCount++;
				hardScore += asked.getScore();
			}
		}

		float score = easyScore + (mediumScore * 2) + (hardScore * 3);
		score /= (float) ((easyCount * 1) + (mediumCount * 2) + (hardCount * 3));
		System.out.println(score+"  :: "+round.get().getRoundNumber() );
		float threshold = systemAttributeRepository.findById(1).get().getThreshold();
		if (score < threshold) {
			candidateRepository.updateStatusAndScore("Rejected", score, candidate1.get().getCandidateId());
			processStatusRepository.deleteByCandidateId(candidate);
		} else {
			if (round.get().getRoundNumber() == systemAttributeRepository.findById(1).get().getNoOfRounds()) {
				processStatusRepository.deleteByCandidateId(candidate1.get().getCandidateId());
				candidateRepository.updateStatusAndScore("Selected", score, candidate1.get().getCandidateId());

			} else {
				System.out.println("hola working");
				processStatusRepository.updateRoundAndStatus("pending", round.get().getRoundNumber() + 1,
						candidate1.get().getCandidateId());
			}
		}

		ResponseMsg msg = new ResponseMsg();
		msg.setMessage("Score Updated");
		return msg;
//		System.out.println(score);
	}

	@Override
	public List<QuestionsAsked> getReportQuestions(int candidateId) {
		Optional<Candidate> candidate = candidateRepository.findById(candidateId);
		List<QuestionsAsked> questionList = questionAskedRepository.getAllAskedQuestion(candidate.get());
		return questionList;
	}

}
