package com.choreTracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.choreTracker.models.Job;
import com.choreTracker.repositories.JobRepository;


@Service
public class JobService {

	// adding the Job repository as a dependency
	private final JobRepository jobRepository;

	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	// returns all the Jobs
	public List<Job> allJobs() {
		return jobRepository.findAll();
	}

	// creates a Job
	public Job createJob(Job b) {
		return jobRepository.save(b);
	}

	// retrieves a Job
	public Job findJob(Long id) {
		Optional<Job> optionalJob = jobRepository.findById(id);
		if (optionalJob.isPresent()) { // isPresent() : method to check if our object exists.
			return optionalJob.get();
		} else {
			return null;
		}
	}

	// update a Job
	public Job updateJob(Job b) {
		return jobRepository.save(b);
	}

	// find by containing
	public List<Job> JobsContaining(String search) {
		return jobRepository.findByTitleContaining(search);
	}

	public void deleteJob(Long id) {
		jobRepository.deleteById(id);
	}

}