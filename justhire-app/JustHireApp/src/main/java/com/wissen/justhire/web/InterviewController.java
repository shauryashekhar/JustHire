package com.wissen.justhire.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.ProcessStatusRepository;
import com.wissen.justhire.repository.RoundRepository;
import com.wissen.justhire.repository.SystemAttributeRepository;
import com.wissen.justhire.service.InterviewService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/interview")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;

	@Autowired
	private SystemAttributeRepository systemAttributeRepository;

	@Autowired
	private ProcessStatusRepository processStatusRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private RoundRepository roundRepository;

	@GetMapping(path = "minimum-questions", produces = "application/json")
	public int getMinimumQuestionsThreshold() {

		return systemAttributeRepository.findById(1).get().getMinimumQuestions();
	}

	@GetMapping(value = "candidate/pending/{roundId}")
	public List<Candidate> getPendingCandidate(@PathVariable int roundId) {

		List<Candidate> pendingCandidate = processStatusRepository.viewPendingCandidate(roundId);
		List<Integer> candidateList = new ArrayList<>();
		for(Candidate c:pendingCandidate)
			candidateList.add(c.getCandidateId());
		//System.out.println(c.getEmail());
		
		List<Candidate> pendingCandidateList = candidateRepository.findAllById(candidateList);
		System.out.println(pendingCandidateList);
		return pendingCandidateList;
	}

	@PutMapping(path = "start/", produces = "application/json", params = { "candidateId" })
	public void startInterview(@RequestParam int candidateId) {
		interviewService.startInterview(candidateId);
	}

	@GetMapping(path = "firstquestion", produces = "application/json", params = { "candidateId", "roundId" })
	public Question getFirstQuestion(@RequestParam int candidateId, @RequestParam int roundId) { // round wala chutzpa

		Round round = roundRepository.getRound(roundId);
//		System.out.println(round.getRoundNumber());
		Optional<Candidate> candidate = candidateRepository.findById(candidateId);
//		System.out.println(candidate.get().getCandidateId());

		return interviewService.firstQuestion(candidate.get(), round);
	}

	@PostMapping(path = "submitanswer", produces = "application/json")
	public void submitAnswer(@RequestBody AnswerForm answer) {
		AnswerForm answerForm = new AnswerForm();
		answerForm.setCandidateId(answer.getCandidateId());
		answerForm.setQuestionId(answer.getQuestionId());
		answerForm.setRoundId(answer.getRoundId());
		answerForm.setComment(answer.getComment());
		answerForm.setScore(answer.getScore());
		interviewService.submitAnswer(answerForm);
	}

	@GetMapping(path = "next-question", produces = "application/json", params = { "candidateId", "roundId" })
	public Question getNextQuestion(@RequestParam int candidateId, @RequestParam int roundId) {
		return interviewService.nextQuestion(candidateId, roundId);
	}

	@PostMapping(path = "stop-interview", produces = "application/json", params = { "candidateId", "roundId",
			"easyScore", "mediumScore", "hardScore", "easyCount", "mediumCount", "hardCount" })
	public void stopInterview(@RequestParam int candidateId, @RequestParam int roundId, @RequestParam int easyScore,
			@RequestParam int mediumScore, @RequestParam int hardScore, @RequestParam int easyCount,
			@RequestParam int mediumCount, @RequestParam int hardCount) {
		interviewService.stopInterview(candidateId, roundId, easyScore, mediumScore, hardScore, easyCount, mediumCount,
				hardCount);

	}

}