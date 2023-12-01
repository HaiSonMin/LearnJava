package com.haison.Spring;

import com.haison.Spring.test.AppConfig;
import com.haison.Spring.test.Customer;
import com.haison.Spring.test.PeopleImpl;
import com.haison.Spring.test.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}


 	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(applicationContext.getBean(Customer.class).talk());
		System.out.println(applicationContext.getBean(Student.class).talk());
		applicationContext.getBean(PeopleImpl.class).getInfoPeople();
	}

}
