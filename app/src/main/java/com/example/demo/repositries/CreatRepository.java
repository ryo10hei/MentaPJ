package com.example.demo.repositries;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CreatForm;

@Repository
public interface CreatRepository extends JpaRepository<CreatForm, String>{
	Optional<CreatForm> findById(String id);
	List<CreatForm> findAll();
	@Transactional
	void deleteById(long id);
}