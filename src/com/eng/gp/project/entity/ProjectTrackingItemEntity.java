package com.eng.gp.project.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;



@Entity
@Table(name = "project_tracking_item")
public class ProjectTrackingItemEntity implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
   @Column(name = "project_id")
	private Long projectId;

	@OneToOne
	@JoinColumn(name ="premises_id")
	private PremisesEntity premises;

	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "start_date")
	@Type(type = "com.eng.gp.project.ext.UtcTimestampType")
	private Date startDate;

	@Column(name = "end_date")
	//@Type(type = "com.eng.gp.project.ext.UtcTimestampType")
	private Date endDate;

	@OneToOne
	@JoinColumn(name = "project_type_id")
	private ProjectTypeEntity projectType;
	
	@Column(name="is_deprecated")
	private boolean deprecated = false;

	@Column(name = "channels")
	private String channels;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	

	public PremisesEntity getPremises() {
		return premises;
	}

	public void setPremises(PremisesEntity premises) {
		this.premises = premises;
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

	public ProjectTypeEntity getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectTypeEntity projectType) {
		this.projectType = projectType;
	}


	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}
	
	 public boolean deprecated() {
	        return deprecated;
	    }

	    
	    public void setDeprecated(boolean deprecated) {
	        this.deprecated = deprecated;
	    }


	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((projectId == null) ? 0 : projectId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectTrackingItemEntity other = (ProjectTrackingItemEntity) obj;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		return true;
	}
*/
	
}
