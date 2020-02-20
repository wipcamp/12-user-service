package com.wipcamp.userservice.seeders;

import com.wipcamp.userservice.models.Major;
import com.wipcamp.userservice.models.Question;
import com.wipcamp.userservice.repositories.MajorRepository;

import com.wipcamp.userservice.repositories.QuestionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class QuestionSeeder {

	private QuestionRepository questionRepository;

	private MajorRepository majorRepository;

	private Logger logger = LoggerFactory.getLogger(QuestionSeeder.class);

	List<Question> questions;

	@Autowired
	public QuestionSeeder(QuestionRepository questionRepository, MajorRepository majorRepository) {
		questions = new ArrayList<>(7);
		questions.add(0,new Question("จงอธิบายการทำงานของ Flowchart ข้างต้น"));
		questions.add(1,new Question("ณ ค่ายไอทีชื่อดังแห่งหนึ่ง น้องเป็นนักเรียนมัธยมปลายที่กำลังเดินทางไปค่ายนี้เพื่อมาเรียนศาสตร์ด้านการเขียนโปรแกรม ถ้าน้องได้พบกับโปรแกรมเมอร์ระดับปรมาจารย์ที่มีทักษะเชี่ยวชาญเฉพาะด้าน และน้องได้มีโอกาสถามคำถามกับเขา น้องจะถามอะไร"));
		questions.add(2,new Question("น้องคิดว่าส่วนใดของเว็บไซต์สำคัญที่สุด เพราะอะไร"));
		questions.add(3,new Question("ณ ค่ายไอทีชื่อดังแห่งหนึ่ง น้องเป็นนักเรียนมัธยมปลายที่กำลังเดินทางไปค่ายนี้เพื่อมาเรียนศาสตร์ด้านการเขียนเว็บไซต์ ถ้าน้องได้พบกับนักเขียนเว็บไซต์ระดับปรมาจารย์ที่มีทักษะเชี่ยวชาญเฉพาะด้าน และน้องได้มีโอกาสถามคำถามกับเขา น้องจะถามอะไร"));
		questions.add(4,new Question("สำหรับน้อง คิดว่า UX & UI คืออะไร"));
		questions.add(5,new Question("ณ ค่ายไอทีชื่อดังแห่งหนึ่ง น้องเป็นนักเรียนมัธยมปลายที่กำลังเดินทางไปค่ายนี้เพื่อมาเรียนศาสตร์ด้าน UX & UI ถ้าน้องได้พบกับนักออกแบบ UX & UI ระดับปรมาจารย์ที่มีทักษะเชี่ยวชาญเฉพาะด้าน และน้องได้มีโอกาสถามคำถามกับเขา น้องจะถามอะไร"));
		questions.add(6,new Question("ถ้าไม่มีระบบเน็ตเวิร์ก น้องคิดว่าชีวิตประจำของน้องจะต่างไปจากเดิมแค่ไหน อย่างไร"));
		questions.add(7,new Question("ณ ค่ายไอทีชื่อดังแห่งหนึ่ง น้องเป็นนักเรียนมัธยมปลายที่กำลังเดินทางไปค่ายนี้เพื่อมาเรียนศาสตร์ด้านเน็ตเวิร์ก ถ้าน้องได้พบกับผู้ที่ทำงานด้านเน็ตเวิร์กระดับปรมาจารย์ที่มีทักษะเชี่ยวชาญเฉพาะด้าน และน้องได้มีโอกาสถามคำถามกับเขา น้องจะถามอะไร"));
		this.questionRepository = questionRepository;
		this.majorRepository = majorRepository;
	}

	void doSeeder() {
		List<Major> majors = majorRepository.findAll();
		if(majors.size() == 4 && questionRepository.count() == 0){
			int countQuestion = 0;
			for (int i = 0; i < majors.size(); i++) {
				for (int j = 0; j < 2; j++) {
					questions.get(countQuestion).setMajor(majors.get(i));
					questionRepository.save(questions.get(countQuestion++));
				}
			}
			if(questionRepository.count() == 8){
				logger.info(new Timestamp(System.currentTimeMillis())+": doSeeder: Success: Question seeder is complete seeding the database with "+questionRepository.count()+" rows");
			}else{
				logger.error(new Timestamp(System.currentTimeMillis())+": doSeeder: Failed: Question seeder is not complete database inset only "+questionRepository.count()+" rows");
			}
		}else{
			logger.warn(new Timestamp(System.currentTimeMillis())+": doSeeder: Failed: The database question table already have data!");
		}
	}
}
