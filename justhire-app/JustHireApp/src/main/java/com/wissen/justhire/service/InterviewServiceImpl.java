package com.wissen.justhire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
		// TODO Auto-generated method stub

	}

	@Override
	public Question nextQuestion(Candidate candidate, Round round, String currentDifficulty, float score,
			String comment) {
		String nextDifficulty;
		if (score == 0 || score == 4) {
			nextDifficulty = getLowerDifficultyQuestion(currentDifficulty);
		} else if (score == 7) {
			nextDifficulty = currentDifficulty;
		} else {
			nextDifficulty = getHigherDifficultyQuestion(currentDifficulty);
		}

		List<Question> questions = questionRepository.findNext(nextDifficulty, round, candidate.getExperience());
		int questionNo = (int) (Math.random() * (questions.size() - 1));
		Question nextQuestion = questions.get(questionNo);
		QuestionsAsked questionAsked = new QuestionsAsked();
		questionAsked.setCandidate(candidate);
		questionAsked.setComment(comment);
		questionAsked.setQuestion(nextQuestion);
		questionAsked.setRound(round);
		questionAsked.setScore(score);
		return nextQuestion;
	}

//	difficulty in ascending order
	@Override
	public void stopInterview(Candidate candidate,Round currentRound, int easyCount, int mediumCount, int hardCount, int easyScore, int mediumScore, int hardScore ) {
		float score = easyScore + (mediumScore*2) + (hardScore*3);
		score /= (float)((easyCount*1) + (mediumCount*2) + (hardCount*3));
		float threshold = systemAttributeRepository.findById(1).get().getThreshold();
		if(score<threshold) {
			candidateRepository.updateStatusAndScore("Rejected", score, candidate.getCandidateId());
			processStatusRepository.deleteByCandidateId(candidate.getCandidateId());
		}
		else {
			if(currentRound.getRoundNumber() == systemAttributeRepository.findById(1).get().getNoOfRounds()) {
				processStatusRepository.deleteByCandidateId(candidate.getCandidateId());
				candidateRepository.updateStatusAndScore("Selected", score, candidate.getCandidateId());
				
			}
			else {
				processStatusRepository.updateRoundAndStatus("Pending", currentRound.getRoundNumber()+1, candidate.getCandidateId());
			}
		}
		
	}

}
