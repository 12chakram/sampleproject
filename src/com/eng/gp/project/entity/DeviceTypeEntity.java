package com.eng.gp.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity representing a device type (ie: Thermostat, Relay Module, Battery Inverter, etc)
 */
@Entity
@Table(name = "device_type", uniqueConstraints =
    @UniqueConstraint(columnNames={"name"}))
public class DeviceTypeEntity {

	/** Unique primary key for this entity */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gpupSeq")
	@SequenceGenerator(name = "gpupSeq", sequenceName = "gpup_high_value", allocationSize = 5)
	@Column(name = "device_type_id")
	private long deviceTypeId;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	/**
	 * @return the deviceTypeId
	 */
	public long getDeviceTypeId() {
		return deviceTypeId;
	}
	
	// required by testing framework
	public void setDeviceTypeId(long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	
	public DeviceTypeEntity() {
	}
	
	public DeviceTypeEntity(long deviceTypeId, String name) {
		setDeviceTypeId(deviceTypeId);
		setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
    @Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + (int) ( deviceTypeId ^ ( deviceTypeId >>> 32 ) );
	    result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
	    return result;
    }

    @Override
    public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    DeviceTypeEntity other = (DeviceTypeEntity) obj;
	    if (deviceTypeId != other.deviceTypeId) return false;
	    if (name == null) {
		    if (other.name != null) return false;
	    } else if (!name.equals(other.name)) return false;
	    return true;
    }

	@Override
    public String toString() {
	    return "DeviceTypeEntity {deviceTypeId=" + deviceTypeId + ", name=" + name + "}";
    }	
}
