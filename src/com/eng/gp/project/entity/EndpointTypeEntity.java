package com.eng.gp.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Endpoint metadata
 */
@Entity
@Table(name = "endpoint_type")
@SuppressWarnings("serial")
public class EndpointTypeEntity implements java.io.Serializable {

    /** Unique primary key for this entity. */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gpupSeq")
    @SequenceGenerator(name="gpupSeq",sequenceName="gpup_high_value", allocationSize=5)
    @Column(name = "endpoint_type_id")
    private long endpointTypeId;

    /** The name of the endpoint type. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "virtual", nullable = false)
    private Boolean virtual = false;

    public String getDescription() {
        return description;
    }

    public long getEndpointTypeId() {
        return endpointTypeId;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndpointTypeId(final long designatedEndpointTypeId)
    {
        endpointTypeId = designatedEndpointTypeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVirtual() {
        return virtual;
    }

    public void setVirtual(Boolean virtual) {
        this.virtual = virtual;
    }

    @Override
    public String toString() {
        return "EndpointTypeEntity{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndpointTypeEntity)) return false;

        EndpointTypeEntity that = (EndpointTypeEntity) o;

        if (endpointTypeId != that.endpointTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (endpointTypeId ^ (endpointTypeId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
