package com.pavikumbhar.shared.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author pavikumbhar
 *
 */
@Entity
public class Metadata implements Serializable {

private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private long id;
 
    @Column(name = "NAME")
    private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
