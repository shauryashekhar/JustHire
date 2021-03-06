package com.wissen.justhire;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.wissen.justhire.model.Question;
import com.wissen.justhire.model.QuestionsAsked;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.InterviewRepository;
import com.wissen.justhire.repository.LoginRepository;
import com.wissen.justhire.repository.ProcessStatusRepository;
import com.wissen.justhire.repository.QuestionAskedRepository;
import com.wissen.justhire.repository.QuestionRepository;
import com.wissen.justhire.repository.SystemAttributeRepository;
import com.wissen.justhire.repository.UserRepository;
import com.wissen.justhire.service.StorageService;

@SpringBootApplication
@EnableJpaRepositories
public class JustHireAppApplication implements CommandLineRunner {
	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(JustHireAppApplication.class, args);

	}

	@Bean
	public CommandLineRunner test(UserRepository userRepository, CandidateRepository candidateRepository,
			QuestionRepository questionRepository, QuestionAskedRepository questionAskedRepository,
			InterviewRepository interviewRepository, LoginRepository loginRepository,
			SystemAttributeRepository attributeRepository, ProcessStatusRepository processStatusRepository) {
		return args -> {

			// Testing Candidate Repository

			// find all candidate

//				List<Candidate> candidates = candidateRepository.findAll();
//				for (Candidate c : candidates)
//					System.out.println(c.getFirstName());
//	
//				// insert candidate
//				Candidate can = new Candidate();
//				can.setFirstName("Sagar");
//				can.setLastName("Naik");
//				can.setEmail("s.naik@gmail.com");
//				candidateRepository.save(can);
//	
//				// update
//	
//				candidateRepository.updateStatusAndScore("selected", 10.00f, 4);
//				System.out.println("updated");

//		----------------------------------------------------------------------------------------------------------------

			// Testing Interview Repository

			// Insert

//			Interview interview = new Interview();
//			Candidate candidate = new Candidate();
//			candidate.setCandidateId(1);
//			interview.setCandidate(candidate);
//			User user = new User();
//			user.setUserId(14);
//			interview.setUser(user);
//			interviewRepository.save(interview);
//			System.out.println("saved");

//			----------------------------------------------------------------------------------------------------------------

			// Testing Login Repository

			// Insert

//			Login login = new Login();
//			login.setUserId(19);
//			login.setEmail("ksnaik@gmail.com");
//			login.setPassword("1234");
//			loginRepository.save(login);
//			
			// findByEmail

//			System.out.println(loginRepository.findByEmail("shaurya@email.com").getEmail());

//			----------------------------------------------------------------------------------------------------------------

			// Testing User Repository

			// Insert

//			User user = new User();
//			user.setFirstName("Komal");
//			user.setLastName("Naik");
//			user.setIsAdmin(0);
//			user.setEmail("ksnaik@gmail.com");
//			Round round = new Round();
//			round.setRoundNumber(1);
//			user.setRound(round);
//			user.setPhoneNumber("9956745634");
//			userRepository.save(user);
//			System.out.println("saved");

			// find all

//			List<User> users = userRepository.findAll();
//			for(User u:users)
//				System.out.println(u.getFirstName());
//			

			// findById
//			Optional<User> user = userRepository.findById(14);
//			System.out.println(user.get().getFirstName());
//			
//			
//			//findByRound
//			Round round = new Round();
//			round.setRoundNumber(1);
//			users = userRepository.findByRound(round);
//			for(User u:users)
//			System.out.println(u.getFirstName());

//			System.out.println(userRepository.findUserRoundbyId(15).getRoundNumber());

//			----------------------------------------------------------------------------------------------------------------

			// Testing Question Repository

//			Round round = new Round();
//			round.setRoundNumber(1);
//		questionRepository.approveQuestion(3);
//			List<Question> quest=questionRepository.findNext("easy", round, "0-5");

//			questionRepository.editQuestion("DS", "easy", "0-5", round, "question 256",4 );

//			for(Question Q: quest)
//			System.out.println(Q.getQuestion());

//			----------------------------------------------------------------------------------------------------------------

			// Testing System_attributes

//			Optional<SystemAttribute> attribute=attributeRepository.findById(1);
//			System.out.println(attribute.get().getMinimumQuestions());\

//			----------------------------------------------------------------------------------------------------------------

			// Testing Question_asked

//			All system defined function

//			----------------------------------------------------------------------------------------------------------------

			// Testing process_status

//			Optional<Candidate> candidate=candidateRepository.findById(1);
//			Candidate candidate2=new Candidate();
//			candidate2.setCandidateId(1);
//			processStatusRepository.updateRoundAndStatus("ongoing", 2, candidateRepository.findById(1).get().getCandidateId());
//			processStatusRepository.updateStatus("ongoing",candidateRepository.findById(2).get().getCandidateId());

//			int a=questionAskedRepository.questionExists(candidateRepository.findById(3).get(),questionRepository.findById(1).get());

//			System.out.println(a);

//			List<Candidate> c=processStatusRepository.viewCandidate(2);
//			for(Candidate can:c)
//				System.out.println(can.getFirstName());
//			Round round = new Round();
//			round.setRoundNumber(1);
//			List<Question> q=questionRepository.getRoundQuestions(round);
//			for(Question as:q)
//				System.out.println(as.getQuestion());

//			List<QuestionsAsked> asked= questionAskedRepository.getPreviousQuestion(candidateRepository.findById(1).get());
//			System.out.println(asked.get(0).getQuestion().getQuestion());

//			Round round = new Round();
//			round.setRoundNumber(1);
//			List<Question> q=questionRepository.findNext("Hard", round, "5+");
//			for(Question as:q)
//				System.out.println(as.getQuestion());

//			Round round=new Round();
//			round.setRoundNumber(1);
//			
//			List<QuestionsAsked> list=questionAskedRepository.getAllAskedQuestion(candidateRepository.findById(1).get(), round);
//			
//			for(QuestionsAsked as:list)
//				System.out.println(as.getQuestion().getQuestion());

		};
	}

	@Override
	public void run(String... args) throws Exception {
//		storageService.deleteAll();
//		storageService.init();
	}
}
