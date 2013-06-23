package com.eng.gp.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_type")
public class ProjectTypeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name ="project_type_id") 
	private Long projectTypeId;
	

	@Column(name ="project_type")
	private String projectType;

	
	public Long getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Long projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((projectTypeId == null) ? 0 : projectTypeId.hashCode());
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
		ProjectTypeEntity other = (ProjectTypeEntity) obj;
		if (projectTypeId == null) {
			if (other.projectTypeId != null)
				return false;
		} else if (!projectTypeId.equals(other.projectTypeId))
			return false;
		return true;
	}*/

	

}
