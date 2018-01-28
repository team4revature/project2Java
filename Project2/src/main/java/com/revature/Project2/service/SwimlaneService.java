package com.revature.Project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Project2.beans.Story;
import com.revature.Project2.beans.Swimlane;
import com.revature.Project2.dto.StoryDTO;
import com.revature.Project2.repository.SwimlaneRepo;

@Service
public class SwimlaneService {
	
	@Autowired
	SwimlaneRepo swimRepo;
	
	public Swimlane getSwimlane(int id) {
		
		return swimRepo.findOne(id);
	}
	
	public Swimlane createSwimlane(Swimlane swim) {
		
		return swimRepo.save(swim);
	}
	
	public Story addStory(StoryDTO story) {
		
		Swimlane swimlane = swimRepo.findOne(story.getSwimlaneId());
		swimlane.getStories().add(story.getStory());
		swimlane = swimRepo.save(swimlane);
		return swimlane.getStories().get(swimlane.getStories().size() - 1);
	}
	
	public boolean deleteSwimlane(int swimlaneId) {
		swimRepo.delete(swimlaneId);
		return true;
	}
}
