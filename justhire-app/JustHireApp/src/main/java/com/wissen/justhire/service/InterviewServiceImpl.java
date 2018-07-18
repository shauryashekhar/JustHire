package com.wissen.justhire.service;

import java.util.List;

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
		List<Question> questions = questionRepository.findNext(difficulty, round, candidate.getExperience());

		do {
			questionNo = (int) (Math.random() * (questions.size() - 1));
		} while (questionAskedRepository.questionExists(candidate, questions.get(questionNo)) == 1);

		if (questionNo == -1)
			return null;
		else
			return questions.get(questionNo);
	}

	@Override
	public int getMinimumQuestionThreshold() {
		return systemAttributeRepository.findById(1).get().getMinimumQuestions();
	}

	@Override
	public List<Candidate> viewPendingCandidates(int round) {
		return null;
	}

	@Override
	public void startInterview(Candidate candidate) {
		processStatusRepository.updateStatus("Ongoing", candidate.getCandidateId());
	}

	@Override
	public Question nextQuestion(Candidate candidate, Round round, String currentDifficulty, float score, String comment) {

		String nextDifficulty;
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

		QuestionsAsked questionAsked = new QuestionsAsked();
		questionAsked.setCandidate(candidate);
		questionAsked.setComment(comment);
		questionAsked.setQuestion(nextQuestion);
		questionAsked.setRound(round);
		questionAsked.setScore(score);
		questionAskedRepository.save(questionAsked);
		return nextQuestion;
	}

//	difficulty in ascending order
	@Override
	public void stopInterview(Candidate candidate, Round currentRound, int easyCount, int mediumCount, int hardCount,
			int easyScore, int mediumScore, int hardScore) {
		float score = easyScore + (mediumScore * 2) + (hardScore * 3);
		score /= (float) ((easyCount * 1) + (mediumCount * 2) + (hardCount * 3));
		float threshold = systemAttributeRepository.findById(1).get().getThreshold();
		if (score < threshold) {
			candidateRepository.updateStatusAndScore("Rejected", score, candidate.getCandidateId());
			processStatusRepository.deleteByCandidateId(candidate.getCandidateId());
		} else {
			if (currentRound.getRoundNumber() == systemAttributeRepository.findById(1).get().getNoOfRounds()) {
				processStatusRepository.deleteByCandidateId(candidate.getCandidateId());
				candidateRepository.updateStatusAndScore("Selected", score, candidate.getCandidateId());

			} else {
				processStatusRepository.updateRoundAndStatus("Pending", currentRound.getRoundNumber() + 1,
						candidate.getCandidateId());
			}
		}

	}

}
