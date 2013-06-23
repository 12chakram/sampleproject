package com.eng.gp.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




@Entity
@Table(name = "tenant")
@SuppressWarnings("serial")
public class TenantEntity implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gpupSeq")
	@SequenceGenerator(name = "gpupSeq", sequenceName = "postgres_high_value", allocationSize = 5)
	@Column(name = "tenant_id")
	private long tenantId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "tenant_url")
	private String tenantUrl;

	@OneToMany(mappedBy = "tenant", cascade = { CascadeType.ALL })
	List<PremisesEntity> premises;

	@Column(name = "inherit_color_configs")
	private boolean colorConfigsInherited = false;

	@Column(name = "eula_inherited")
	private Boolean inheritEulavalue = false;

	@Column(name = "eula_required", nullable = true)
	private Boolean required;

	@Column(name = "eula_exception", nullable = true)
	private Boolean exceptional;
	
	@Column(name = "has_project_tracking")
	private Boolean hasProjectTracking = false;

	public long getTenantId() {
		return tenantId;
	}

	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTenantUrl() {
		return tenantUrl;
	}

	public void setTenantUrl(String tenantUrl) {
		this.tenantUrl = tenantUrl;
	}

	public boolean isColorConfigsInherited() {
		return colorConfigsInherited;
	}

	public void setColorConfigsInherited(boolean colorConfigsInherited) {
		this.colorConfigsInherited = colorConfigsInherited;
	}

	public Boolean getInheritEulavalue() {
		return inheritEulavalue;
	}

	public void setInheritEulavalue(Boolean inheritEulavalue) {
		this.inheritEulavalue = inheritEulavalue;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getExceptional() {
		return exceptional;
	}

	public void setExceptional(Boolean exceptional) {
		this.exceptional = exceptional;
	}

	public Boolean getHasProjectTracking() {
		return hasProjectTracking;
	}

	public void setHasProjectTracking(Boolean hasProjectTracking) {
		this.hasProjectTracking = hasProjectTracking;
	}

	public List<PremisesEntity> getPremises() {
		return premises;
	}

	public void setPremises(List<PremisesEntity> premises) {
		this.premises = premises;
	}

	

}
