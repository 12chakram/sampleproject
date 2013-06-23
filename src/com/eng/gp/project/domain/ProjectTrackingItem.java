package com.eng.gp.project.domain;

import java.io.Serializable;
import java.util.Date;

public class ProjectTrackingItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long projectId;
	private Long premisesId;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private Long projectTypeId;
    private String channels;
    private String projectStatus;
    private boolean deprecated;


	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getPremisesId() {
		return premisesId;
	}

	public void setPremisesId(Long premisesId) {
		this.premisesId = premisesId;
	}

	public Long getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Long projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public boolean getDeprecated() {
  		return deprecated();
  	}

    public boolean deprecated() {
		return deprecated;
	}

	public void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}
}
