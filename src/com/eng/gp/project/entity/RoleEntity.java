package com.eng.gp.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 */
@Entity
@Table(name = "role")
@SuppressWarnings("serial")
public class RoleEntity implements java.io.Serializable {

    /** Unique primary key for this entity. */
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gpupSeq")
    @SequenceGenerator(name="gpupSeq",sequenceName="gpup_high_value", allocationSize=5)
    @Column(name = "role_id", unique = true)
    private long roleId;

    /** The name of the role. */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /** The description of the role. */
    @Column(name = "description", nullable = false)
    private String description;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString () {
        return "RoleEntity{" + "roleId=" + roleId + '}';
    }
}
