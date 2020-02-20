package com.wipcamp.userservice.seeders;

import com.wipcamp.userservice.utils.LoggerUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SeederRunner implements ApplicationRunner {

	private MajorSeeder seederMajor;

	private QuestionSeeder questionSeeder;

	private Logger logger = LoggerFactory.getLogger(SeederRunner.class);

	@Autowired
	public SeederRunner(MajorSeeder seederMajor, QuestionSeeder questionSeeder) {
		this.seederMajor = seederMajor;
		this.questionSeeder = questionSeeder;
	}

	public void run(ApplicationArguments args) {
		seederMajor.doSeeder();
		questionSeeder.doSeeder();
		LoggerUtility.logSuccessInfo(logger,"SeederRunner Complete seeded the database!","run");
	}
}
