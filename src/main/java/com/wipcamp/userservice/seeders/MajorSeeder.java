package com.wipcamp.userservice.seeders;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.models.Major;
import com.wipcamp.userservice.repositories.MajorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MajorSeeder {

	private MajorRepository majorRepository;

	private Logger logger = LoggerFactory.getLogger(MajorSeeder.class);

	List<Major> majors;

	@Autowired
	public MajorSeeder(MajorRepository majorRepository) {
		majors = new ArrayList<>(4);
		majors.add(0,new Major(1,"Programmer","เส้นทางที่จะพาไปทำความเข้าใจ Concept ของการเขียนโปรแกรม ผ่านการเรียนภาษาจาวา (Java Programming)"));
		majors.add(1,new Major(2,"Website","เส้นทางที่จะพาไปรู้จักสายอาชีพด้านเว็บไซต์ เข้าใจเกี่ยวกับเว็บไซต์ด้วยการเขียน HTML และ CSS"));
		majors.add(2,new Major(3,"UX&UI","เส้นทางที่จะพาไปเรียนรู้ Concept ของ UX & UI และการออกแบบแอปพลิเคชันหรือเว็บไซต์ของเราให้ตอบโจทย์กับผู้ใช้งาน"));
		majors.add(3,new Major(4,"Network","เส้นทางที่จะพาไปสัมผัสอุปกรณ์เครือข่ายของจริง รู้จักสายอาชีพด้าน Network และตามติด Internet Trends ในปัจจุบัน"));
		this.majorRepository = majorRepository;
	}

	void doSeeder() {
		if (majorRepository.count() == 0) {
			majorRepository.saveAll(majors);
			if(majorRepository.count() == 4){
				logger.info(new Timestamp(System.currentTimeMillis())+": doSeeder: Success: Major seeder is complete seeding the database with "+majorRepository.count()+" rows");
			}else{
				logger.error(new Timestamp(System.currentTimeMillis())+": doSeeder: Failed: Major seeder is not complete database inset only "+majorRepository.count()+" rows");
			}
		}else{
			logger.warn(new Timestamp(System.currentTimeMillis())+":doSeeder:Failed:The database major table already have data!");
		}
	}
}
