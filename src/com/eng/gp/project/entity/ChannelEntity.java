package com.eng.gp.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.eng.gp.project.domain.IntervalSize;


/**
 *
 */
@Entity
@Table(name = "channel")
public class ChannelEntity implements java.io.Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gpupSeq")
    @SequenceGenerator(name = "gpupSeq", sequenceName = "gpup_high_value", allocationSize = 5)
    @Column(name = "channel_id")
    private long channelId;

    /** the identifier of the channel within its device. */
    // For GEEP channel reference IDs, see http://wiki.gridpoint.com/display/gridpoint/GEEP+Message+Id+Assignments.
    @Column(name = "reference_id", nullable = true)
    private String referenceId;

    @Column(name = "channel_name", nullable = false)
    private String channelName;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = true)
    private DeviceEntity device;

    @Column(name = "device_id", insertable = false, updatable = false)
    private Long deviceId; // explicitly expose the foreign key

    @Column(name = "virtual_channel", nullable = false, updatable = false)
    private boolean virtualChannel;

    @Column(name = "scale", nullable = false)
    private double scale = 1.0;

    /**
     * the interval size represented in byte format
     * @see IntervalSize
     */
    @Column(name= "granularity", nullable = false)
    private byte granularity = IntervalSize.UNKNOWN.getByteId();

    @Column(name = "display_name", nullable = true)
    private String displayName;

    @Column(name = "locked", nullable = false)
    private boolean locked;

    /** for virtual channels, the channel_id from which it is obtaining data. */
    @Column(name = "data_source_id", nullable = true)
    private Long dataSourceId;

    @Column(name = "disabled", nullable = false)
    private boolean disabled;

    @Column(name = "subcategory", nullable = true)
    private String subcategory;

    @Column(name = "unit_identifier", nullable = true)
    private String unitIdentifier;

    /** optimistic locking version */
    private int version;

    @Column(name = "channel_metadata_id", insertable = false, updatable = false)
    private Long channelMetadataId;
    

    @Column(name = "data_dictionary_id", insertable = false, updatable = false)
    private long dataTypeId; // explicitly expose the foreign key
    
    /** Gets the optimistic locking version */
    @Version
    @Column(name = "version", nullable = false)
    protected int getVersion() {
        return version;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    public boolean getVirtualChannel() {
        return this.virtualChannel;
    }

    public void setVirtualChannel(boolean virtualChannel) {
        this.virtualChannel = virtualChannel;
    }

    public double getScale() {
        return this.scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String getEffectiveName(){
        return this.displayName == null || this.displayName.isEmpty() ? this.channelName : this.displayName;
    }

    public boolean getLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Long getDataSourceId() {
        return this.dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public long getEffectiveDataSourceId() {
        return ( this.dataSourceId != null ) ? this.dataSourceId : this.channelId;
    }

    /**
     * Gets the interval size represented in byte format.
     * @return the interval size represented in byte format
     * @see IntervalSize
     */
    public IntervalSize getGranularity() {
        return IntervalSize.fromByte(granularity);
    }

    /**
     * Gets the interval size represented in byte format.
     * @param granularity the interval size represented in byte format
     * @see IntervalSize
v     */
    public void setGranularity(IntervalSize granularity) {
        this.granularity = granularity.getByteId();
    }
    
    /**
     * Gets the identifier of the channel within its device.
     * @return the identifier of the channel within its device
     */
    // For GEEP channel reference IDs, see http://wiki.gridpoint.com/display/gridpoint/GEEP+Message+Id+Assignments.
    public String getReferenceId() {
        return this.referenceId;
    }

    /**
     * Sets the identifier of the channel within its device.
     * @param referenceId the identifier of the channel within its device
     */
    // For GEEP channel reference IDs, see http://wiki.gridpoint.com/display/gridpoint/GEEP+Message+Id+Assignments.
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
    	this.disabled = disabled;
    }

    public String getSubcategory() {
        return this.subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getUnitIdentifier() {
        return this.unitIdentifier;
    }

    public void setUnitIdentifier(String unitIdentifier) {
        this.unitIdentifier = unitIdentifier;
    }
   

}
