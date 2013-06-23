package com.eng.gp.project.entity;

import java.util.Collection;
import java.util.HashSet;

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

@Entity
@Table(name = "device")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeviceEntity implements java.io.Serializable {

    public static final long serialVersionUID = 1L;

    /** virtual devices of an unknown type use this name */
    public static final String VIRTUAL_DEVICE_NAME = "Virtual Device";

    /** virtual devices of can use this address prefeix, followed by the containing endpoint id */
    public static final String VIRTUAL_DEVICE_ADDRESS_PREFIX = ":VIRTUAL:";

	/** Unique primary key for this entity */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gpupSeq")
	@SequenceGenerator(name = "gpupSeq", sequenceName = "postgres_high_value", allocationSize = 5)
	@Column(name = "device_id")
	private long deviceId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address")
	private String address;

    @Column(name = "legacy_id", nullable = true)
    private Long legacyId;

	@ManyToOne
	@JoinColumn(name = "endpoint_id", nullable = false)
	private EndpointEntity endpoint;	
	
	@Column(name = "model_number")
	private String modelNumber;
	
	@ManyToOne
	@JoinColumn(name = "device_type_id")
	private DeviceTypeEntity deviceType;

    @OneToMany(mappedBy = "device")
    private Collection<ChannelEntity> channels = new HashSet<ChannelEntity>(); 
	    
    @Column(name = "brand")
    private String brand;
	
	@Column(name = "main_amperage")
	private Double mainAmperage;
	
	@Column(name = "tonnage")
	private Integer tonnage;
	
	
	
   
    
   
}
