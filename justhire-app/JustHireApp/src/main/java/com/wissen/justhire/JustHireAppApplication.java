package com.wissen.justhire;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.wissen.justhire.model.Candidate;
import com.wissen.justhire.model.Round;
import com.wissen.justhire.model.User;
import com.wissen.justhire.repository.CandidateRepository;
import com.wissen.justhire.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class JustHireAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustHireAppApplication.class, args);
		
		
	}
	
//	Sort sort = new Sort(new Sort.Order(Direction.ASC, "lastName"));
//	List<Employee> employees = repo.findBySalaryGreaterThan(new Long(10000), sort);
//	for(Employee e : employees){
//		System.out.println(e);
//	}
	
	
	@Bean
	public CommandLineRunner test(UserRepository repository, CandidateRepository candidate) {
		return args ->{
			Optional<User> user=repository.findById(1);
			System.out.println("executed");
			Round round=new Round();
			round.setRoundNumber(1);
			User user2=new User();
			user2.setEmail("xyz@gmail.com");
			user2.setFirstName("Komal");
			user2.setLastName("Naik");
			user2.setIsAdmin(1);
			user2.setRound(round);
			user2.setPhoneNumber("987000453");
//			repository.save(user2);
			System.out.println("save executed");
			List<Candidate> candidatew=candidate.findByStatus("g");
			
			candidate.updateStatusAndScore("helol", 2);
			
			
		};
	}
}
