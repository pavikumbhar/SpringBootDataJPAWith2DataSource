package com.pavikumbhar.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DeviceSpec implements Serializable {

private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private long id;
 
    @Column(name = "DEVICESPEC_NAME")
    private String deviceSpecName;
    
    @OneToMany(mappedBy = "deviceSpec")
    private Set<Device> device = new HashSet<Device>();
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeviceSpecName() {
		return deviceSpecName;
	}

	public void setDeviceSpecName(String deviceSpecName) {
		this.deviceSpecName = deviceSpecName;
	}



	
}