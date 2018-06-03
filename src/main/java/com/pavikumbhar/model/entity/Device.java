package com.pavikumbhar.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Device implements Serializable {

private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private long id;

	@Column(name = "DEVICE_NAME")
    private String deviceName;
    
    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICESPEC_ID") 
    private DeviceSpec deviceSpec;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public DeviceSpec getDeviceSpec() {
		return deviceSpec;
	}

	public void setDeviceSpec(DeviceSpec deviceSpec) {
		this.deviceSpec = deviceSpec;
	}
}
