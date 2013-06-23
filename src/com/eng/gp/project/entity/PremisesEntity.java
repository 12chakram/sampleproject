package com.eng.gp.project.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "premises")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PremisesEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 /**
	    * Unique primary key for this entity.
	    */
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gpupSeq")
	    @SequenceGenerator(name = "gpupSeq", sequenceName = "postgres_high_value", allocationSize = 5)
	    @Column(name = "premises_id")
	    private long premisesId;
	    
		@ManyToOne
	    @JoinColumn(name = "tenant_id")
	    private TenantEntity tenant;
	    
	    @Column(name="name")
	    private String name;
	    
	    @Column(name="legacy_site_id")
	    private String legacySiteId;
	    
	    @Column(name="description")
	    private String description;
	    
	    @Column(name="timezone")
	    private String timeZone;
	    
	    @Column(name="creation_date")
	    private Date creationDate;
	    
	    @Column(name="modification_date")
	    private Date modificationdate;
	    
	    @Column(name="address1")
	    private String address1;
	    
	    @Column(name="address2")
	    private String address2;
	    
	    @Column(name="city")
	    private String city;
	    
	    @Column(name="province")
	    private String province;
	    
	    @Column(name="zipcode")
	    private String zipCode;
	    
	    @Column(name="country")
	    private String country;
	    
	    @Column(name="phone1")
	    private String phone1;

		public long getPremisesId() {
			return premisesId;
		}

		public void setPremisesId(long premisesId) {
			this.premisesId = premisesId;
		}

		public TenantEntity getTenant() {
			return tenant;
		}

		public void setTenant(TenantEntity tenant) {
			this.tenant = tenant;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLegacySiteId() {
			return legacySiteId;
		}

		public void setLegacySiteId(String legacySiteId) {
			this.legacySiteId = legacySiteId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}

		public Date getCreationDate() {
			return creationDate;
		}

		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}

		public Date getModificationdate() {
			return modificationdate;
		}

		public void setModificationdate(Date modificationdate) {
			this.modificationdate = modificationdate;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getPhone1() {
			return phone1;
		}

		public void setPhone1(String phone1) {
			this.phone1 = phone1;
		}
	    
	    
	    
}
