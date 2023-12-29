package com.example.jobportal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Project;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exceptionhandling.ProjectNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.repository.ProjectRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.responsedto.ProjectResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository proRepo;
	@Autowired
	private ResumeRepository resumRepo;


	private Project convertToProject(ProjectRequestDto proReq,Project proj)
	{
		proj.setDescription(proReq.getDescription());
		proj.setProjectName(proReq.getProjectName());
		proj.setSourceCode(proReq.getSourceCode());
		proj.setTechStack(convertToStringArrayIgnoreCase(proReq.getTechStack()));
		proj.setWebsite(proReq.getWebsite());

		return proj;
	}

	private Set<String> convertToStringArrayIgnoreCase(Set<String> techStck)
	{
		TreeSet<String> treeSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		
		for(String tech:techStck)
		{
			
			treeSet.add(tech);
			
		}
		
		//String[] array = (String[]) treeSet.toArray();
		
		return treeSet;
	}
	
	private ProjectResponseDto convertToProjectResponse (Project pro)
	{    ProjectResponseDto dto = new ProjectResponseDto();
	dto.setDescription(pro.getDescription());
	dto.setProId(pro.getProId());
	dto.setProjectName(pro.getProjectName());
	dto.setSourceCode(pro.getSourceCode());
	dto.setTechStack(pro.getTechStack());
	dto.setWebsite(pro.getWebsite());
	return dto;

	}




	public ResponseEntity<ResponseStructure<String>> insertProject(ProjectRequestDto proReq,int resumId) throws ResumeNotFoundException 
	{

		Optional<Resume> optResum = resumRepo.findById(resumId);

		if(optResum.isPresent()) {
			Resume resume = optResum.get();

			Project project = convertToProject(proReq, new Project());
			project.setResumeMap(resume);
			proRepo.save(project);

			// resumRepo.save(resume);
			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" Project data saved successfully");
			respStruc.setData("  PROJECT  ADDED  SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);

		}  else throw new ResumeNotFoundException("resume with given id not present");


	}




	public ResponseEntity<ResponseStructure<ProjectResponseDto>> findProjectById(int projId) throws ProjectNotFoundException 
	{
		Optional<Project> optPro = proRepo.findById(projId);

		if(optPro.isPresent()) {
			Project project = optPro.get();


			ProjectResponseDto dto = convertToProjectResponse(project);

			HashMap<String,String> hashMap = new HashMap<>();
			hashMap.put("Applicant", "/resumes/"+project.getResumeMap().getResumeId());
			dto.setOptions(hashMap);
			ResponseStructure<ProjectResponseDto> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" Project data Found successfully");
			respStruc.setData(dto);

			return new ResponseEntity<ResponseStructure<ProjectResponseDto>>(respStruc, HttpStatus.CREATED);

		} else throw new ProjectNotFoundException("Project with given Id Not Found"); 


	}

	public ResponseEntity<ResponseStructure<String>> updateProject(ProjectRequestDto proReq, int projectId) throws ProjectNotFoundException {

		Optional<Project> optpro = proRepo.findById(projectId);

		if(optpro.isPresent()) {

			Project oldProject= optpro.get();

			Project newProject = convertToProject(proReq, oldProject);

			proRepo.save(newProject);

			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" Project data updated successfully");
			respStruc.setData("  PROJECT  UPDATED SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);

		}  else throw new ProjectNotFoundException(" project  with given id not present");

	}

	public ResponseEntity<ResponseStructure<List<ProjectResponseDto>>> findByProjectByResume(int resumeId) throws ResumeNotFoundException, ProjectNotFoundException {


		Optional<Resume> optiResume = resumRepo.findById(resumeId);

		if(optiResume.isPresent()) {

			Resume resume = optiResume.get();

			List<Project> projectList = resume.getProjectMap();

			if(!projectList.isEmpty()) {

				ArrayList<ProjectResponseDto> responseList = new ArrayList<ProjectResponseDto>();
				HashMap<String,String> hashMap = new HashMap<>();
				for(Project pl:projectList)
				{

					ProjectResponseDto dto = convertToProjectResponse(pl);
					hashMap.put("Developer Profile","/resumes/"+resume.getResumeId());
					dto.setOptions(hashMap);
					responseList.add(dto);

				}

				ResponseStructure<List<ProjectResponseDto>> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Project data Found successfully");
				respStruc.setData(responseList);

				return new ResponseEntity<ResponseStructure<List<ProjectResponseDto>>>(respStruc, HttpStatus.CREATED);
			} else throw new ProjectNotFoundException(" this user has no projects to display");

		} else throw new ResumeNotFoundException(" Resume with given Id Not Found"); 


	}

	public ResponseEntity<ResponseStructure<String>> deleteProject(int projectId) throws ProjectNotFoundException {
		Optional<Project> optpro = proRepo.findById(projectId);

		if(optpro.isPresent()) {

			

		  proRepo.deleteById(projectId);

			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
			respStruc.setMessage(" Project data deleted successfully");
			respStruc.setData("  PROJECT  DELETED SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);

		}  else throw new ProjectNotFoundException(" project  with given id not present");

	}






}
