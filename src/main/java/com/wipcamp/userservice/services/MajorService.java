package com.wipcamp.userservice.services;

import com.wipcamp.userservice.models.Major;
import com.wipcamp.userservice.repositories.MajorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService {
	@Autowired
	MajorRepository majorRepository;

	public MajorService(MajorRepository majorRepository){
		this.majorRepository = majorRepository;
	}

	public Major findMajorById(Long majorId){
		return majorRepository.findById(majorId).get();
	}

	public Optional<Major> findByOptionalId(long userId) { return majorRepository.findById(userId); }

	public List<Major> getAllMajor(){
		return majorRepository.findAll();
	}
}
