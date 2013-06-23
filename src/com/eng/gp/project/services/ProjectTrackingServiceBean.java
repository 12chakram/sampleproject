package com.eng.gp.project.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.eng.gp.project.convertors.EntityConverter;
import com.eng.gp.project.convertors.EntityConverterImpl;
import com.eng.gp.project.domain.ProjectTrackingItem;
import com.eng.gp.project.domain.ProjectType;
import com.eng.gp.project.domain.Role;
import com.eng.gp.project.domain.exception.InvalidPremisesException;
import com.eng.gp.project.domain.exception.InvalidProjectCreationException;
import com.eng.gp.project.domain.exception.InvalidProjectException;
import com.eng.gp.project.domain.exception.InvalidProjectTypeException;
import com.eng.gp.project.entity.PremisesEntity;
import com.eng.gp.project.entity.ProjectTrackingItemEntity;
import com.eng.gp.project.entity.ProjectTypeEntity;
import com.eng.gp.project.entity.RoleEntity;
import com.eng.gp.project.impl.JPAPremisesManager;
import com.eng.gp.project.impl.JPAProjectTrackingItemManager;
import com.eng.gp.project.impl.JPAProjectTypeManager;
import com.eng.gp.project.impl.JPARoleManager;
import com.eng.gp.project.managers.PremisesManager;
import com.eng.gp.project.managers.ProjectTrackingItemManager;
import com.eng.gp.project.managers.ProjectTypeManager;
import com.eng.gp.project.managers.RoleManager;
import com.eng.gp.project.util.date.NotNull;



public class ProjectTrackingServiceBean implements  ProjectTrackingService {

	private static final Logger log = Logger.getLogger(ProjectTrackingServiceBean.class);

	ProjectTypeManager projectTypeManager;
	PremisesManager premisesManager;
	ProjectTrackingItemManager projectTrackingItemManager;
	private EntityConverter entityConverter;

	public void saveProject (ProjectTrackingItem projectTracking) throws Exception {
		ProjectTrackingItemEntity entity = new ProjectTrackingItemEntity();

		if(projectTracking.getProjectName()==null || projectTracking.getProjectName().contains(" ") || projectTracking.getProjectName().isEmpty()){
			throw new Exception("ProjectName cannot be null");
		}

		if(projectTracking.getProjectTypeId()!=null){
			projectTypeManager =new JPAProjectTypeManager();
			ProjectTypeEntity projectTypeEntity = projectTypeManager.findByPrimaryKey(projectTracking.getProjectTypeId());
			if(projectTypeEntity==null){
				throw new Exception("ProjectName cannot be null");
			}
			entity.setProjectType(projectTypeEntity);
		}else{
			throw new Exception("Project Type cannot be null");
		}

		if(projectTracking.getPremisesId()!=null){
			premisesManager = new JPAPremisesManager();
			PremisesEntity premisesEntity = premisesManager.findByPrimaryKey(projectTracking.getPremisesId());
			if(premisesEntity==null){
				throw new Exception("Invalid Premises");
			}
			entity.setPremises(premisesEntity);
		} else{
			throw new Exception("Premises ID cannot be null");
		}
		entity.setProjectName(projectTracking.getProjectName());
		entity.setChannels(projectTracking.getChannels());
		entity.setStartDate(projectTracking.getStartDate());
		entity.setEndDate(projectTracking.getEndDate());

		projectTrackingItemManager = new JPAProjectTrackingItemManager();
		projectTrackingItemManager.persist(entity);

		log.debug("Save success");

	}
	
	public ProjectTrackingItem updateProject(ProjectTrackingItem projectTracking) throws InvalidProjectException, InvalidProjectCreationException,InvalidPremisesException, InvalidProjectTypeException {
		
		projectTrackingItemManager = new JPAProjectTrackingItemManager();
			ProjectTrackingItemEntity entity = null;
			if (projectTracking.getProjectId() != null) {
				entity = projectTrackingItemManager.findByPrimaryKey(projectTracking.getProjectId());
			}else{
				throw new InvalidProjectException("Project Id can not be null");
			}
			ProjectTrackingItemEntity projectEntity = null;
			if (entity != null) {
				if(projectTracking.getProjectName()==null || projectTracking.getProjectName().contains(" ") || projectTracking.getProjectName().isEmpty() ){
					throw new InvalidProjectCreationException("ProjectName cannot be null");
				}
				
				if(projectTracking.getProjectTypeId()!=null){
					ProjectTypeEntity projectTypeEntity = projectTypeManager
							.findByPrimaryKey(projectTracking.getProjectTypeId());
					if(projectTypeEntity==null){
						throw new InvalidProjectTypeException(projectTracking.getProjectTypeId());
					}
					entity.setProjectType(projectTypeEntity);
				}else{
					throw new InvalidProjectCreationException("Project Type cannot be null");
				}
				
				if(projectTracking.getPremisesId()!=null){
					PremisesEntity premisesEntity = premisesManager
							.findByPrimaryKey(projectTracking.getPremisesId());
					if(premisesEntity==null){
						throw new InvalidPremisesException(projectTracking.getPremisesId());
					}
					entity.setPremises(premisesEntity);
				} else{
					throw new InvalidProjectCreationException("Premises ID cannot be null");
				}
				entity.setProjectName(projectTracking.getProjectName());
				entity.setStartDate(projectTracking.getStartDate());
				entity.setEndDate(projectTracking.getEndDate());
				entity.setDeprecated(projectTracking.deprecated());
			} else {
				throw new InvalidProjectException(projectTracking.getProjectId());
			}
			projectEntity = projectTrackingItemManager.upDateProject(entity);
			entityConverter = new EntityConverterImpl();
			ProjectTrackingItem projectItem = entityConverter.fromEntity(projectEntity);
			log.debug("Merge success");
			if (projectEntity != null)
				return projectItem;
			else
				return null;
 }
	

	@Override
	public List<ProjectTrackingItem> getAllProjects() {
		entityConverter = new EntityConverterImpl();
		projectTrackingItemManager = new JPAProjectTrackingItemManager();
		List<ProjectTrackingItemEntity> projects = projectTrackingItemManager.getAllProjects();
		List<ProjectTrackingItem> projectItemList = new ArrayList<ProjectTrackingItem>();
		for(ProjectTrackingItemEntity projectEntity: projects){
			if(!projectEntity.deprecated()){
				projectItemList.add(entityConverter.fromEntity(projectEntity));
			}
		}
		return projectItemList;
		
	}
	
	
	public ProjectTrackingItem getProjectByProjectId(Long projectId) throws InvalidProjectException {
		entityConverter = new EntityConverterImpl();
		NotNull.verify(projectId, "projectId");
		ProjectTrackingItemEntity project = projectTrackingItemManager.findByPrimaryKey(projectId);
		if(project==null){
			throw new InvalidProjectException(projectId);
		}
		if(!project.deprecated()){
			return  (entityConverter.fromEntity(project));
		}else{
			throw new InvalidProjectException("This Project Is Deprecated:"+project.getProjectId());
		}
	}

	@Override
	public int removeAllProjects() {
		int reults =0;
		projectTrackingItemManager = new JPAProjectTrackingItemManager();
		reults = projectTrackingItemManager.removeAllProjects();
		return reults;
	}
	
	@Override
	public List<ProjectType> getAllProjectTypes() {
		List<ProjectType> projectTypeList = null;
		projectTrackingItemManager = new JPAProjectTrackingItemManager();
		projectTypeList = projectTrackingItemManager.getAllProjectTypes();
		return projectTypeList;
	}
	

	public boolean deleteProject(Long projectId) throws InvalidProjectException{
		NotNull.verify(projectId, "projectId");
		projectTrackingItemManager = new JPAProjectTrackingItemManager();
		ProjectTrackingItemEntity entity = projectTrackingItemManager.findByPrimaryKey(projectId);
		boolean result ;
		if(entity!=null){
			result = projectTrackingItemManager.removeById(projectId);
		}else{
			throw new InvalidProjectException(projectId);
		}
		log.debug("Delete success");
		return result;
	}

	@Override
	public List<Role> getRoles() {
		RoleManager roleManager = new JPARoleManager();
		List<RoleEntity> roleentityList = roleManager.getAllRoles();
		entityConverter = new EntityConverterImpl();
		List<Role>roles = new ArrayList<Role>();
		for(RoleEntity roleentity : roleentityList){
			roles.add(entityConverter.fromEntity(roleentity));
		}
		return roles;
	}

}
