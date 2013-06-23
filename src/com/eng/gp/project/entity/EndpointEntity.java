package com.eng.gp.project.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Represents a hardware endpoint, such as a controller.
 */
@Entity
@Table(name = "endpoint")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EndpointEntity implements java.io.Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * Virtual endpoints start with this string.
     */
    public static final String VIRTUAL_REFERENCE_ID_PREFIX = ":VIRTUAL:";
	public static final String VIRTUAL_SOLAR_REFERENCE_ID_PREFIX = ":SOLAR:";
	

	/** Unique primary key for this entity. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gpupSeq")
	@SequenceGenerator(name = "gpupSeq", sequenceName = "gpup_endpoint_value", allocationSize = 5)
        @Column(name = "endpoint_id", unique = true)
	private long endpointId;

	/** The serial_number of the endpoint. */
	@Column(name = "reference_id", nullable = false, unique = true)
	private String referenceId;
	
	@Column(name = "password", nullable = true, unique = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "endpoint_type_id")
	private EndpointTypeEntity type;

	@ManyToOne
	@JoinColumn(name = "premises_id")
	private PremisesEntity premises;
	
    @Column(name="premises_id", insertable=false, updatable=false)
    private Long premisesId; //explicitly expose the foreign key

	@OneToMany(mappedBy = "endpoint", cascade = CascadeType.ALL)
	private final Set<DeviceEntity> devices = new HashSet<DeviceEntity>();
	
    /**
     * This is a hack to get around the "Hibernate forces INNER-like query on multiple many-to-one relations" bug.
     * (GPUP-6060, GPUP-6028, GPUP-6209, etc.)
     *
     * Going forward, we should use #labelVersion, instead
     */
 
    @Column
    private Integer versionMajorNumber;

    @Column
    private Integer versionMinorNumber;

    @Column
	private Integer versionRevisionNumber;
  
    /**
     * Represents the 6-byte MAC address of this endpoint.
     * @see MacAddress
     */
    @Column(name = "mac_address", unique = true)
    private Long macAddress;

    /**
     * A case-insensitive serial identifier (a.k.a "serial number", a.k.a. "S/N") that uniquely identifies this endpoint.
     * The serial number is typically assigned by the manufacturer.
     */
    @Column(name = "serial", length = 255)
    private String serial;

	public long getEndpointId() {
		return endpointId;
	}

	public void setEndpointId(long endpointId) {
		this.endpointId = endpointId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EndpointTypeEntity getType() {
		return type;
	}

	public void setType(EndpointTypeEntity type) {
		this.type = type;
	}

	public PremisesEntity getPremises() {
		return premises;
	}

	public void setPremises(PremisesEntity premises) {
		this.premises = premises;
	}

	public Long getPremisesId() {
		return premisesId;
	}

	public void setPremisesId(Long premisesId) {
		this.premisesId = premisesId;
	}

	public Integer getVersionMajorNumber() {
		return versionMajorNumber;
	}

	public void setVersionMajorNumber(Integer versionMajorNumber) {
		this.versionMajorNumber = versionMajorNumber;
	}

	public Integer getVersionMinorNumber() {
		return versionMinorNumber;
	}

	public void setVersionMinorNumber(Integer versionMinorNumber) {
		this.versionMinorNumber = versionMinorNumber;
	}

	public Integer getVersionRevisionNumber() {
		return versionRevisionNumber;
	}

	public void setVersionRevisionNumber(Integer versionRevisionNumber) {
		this.versionRevisionNumber = versionRevisionNumber;
	}

	public Long getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(Long macAddress) {
		this.macAddress = macAddress;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public static String getVirtualReferenceIdPrefix() {
		return VIRTUAL_REFERENCE_ID_PREFIX;
	}

	public static String getVirtualSolarReferenceIdPrefix() {
		return VIRTUAL_SOLAR_REFERENCE_ID_PREFIX;
	}

	public Set<DeviceEntity> getDevices() {
		return devices;
	}
	
}
