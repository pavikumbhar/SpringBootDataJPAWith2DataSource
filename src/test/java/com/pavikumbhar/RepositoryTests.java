package com.pavikumbhar;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.pavikumbhar.model.entity.Device;
import com.pavikumbhar.model.entity.DeviceSpec;
import com.pavikumbhar.repository.model.DeviceRepository;
import com.pavikumbhar.repository.model.DeviceSpecRepository;
import com.pavikumbhar.repository.shared.MetadataRepository;
import com.pavikumbhar.shared.entity.Metadata;

@RunWith(SpringRunner.class)
@SpringBootTest

/**
 * 
 * @author pavikumbhar
 *
 */
public class RepositoryTests {

	@Autowired
    private DeviceSpecRepository deviceSpecRepository;
    
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    @Autowired
    private MetadataRepository metadataRepository;
    
    @Test
    @Transactional(transactionManager = "modelTransactionManager")
    @Rollback(false)
	public void persistsDevice() {
    	
    	DeviceSpec deviceSpec=new DeviceSpec();
		deviceSpec.setDeviceSpecName("IL2-SPEC TEST");
		DeviceSpec deviceSpecSaved=deviceSpecRepository.save(deviceSpec);
		
		Device device=new Device();
		device.setDeviceName("IL2TS-TEST");
		device.setDeviceSpec(deviceSpecSaved);
		deviceRepository.save(device);
		

	}
    
    @Test
    @Transactional(transactionManager = "modelTransactionManager")
    @Rollback(false)
	public void persistsMetadaat() {
    	Metadata metadata =new Metadata();
		metadata.setName("Metadata");
		metadataRepository.save(metadata);

	}
    
    
    
    @Test
    @Transactional(transactionManager = "sharedTransactionManager",readOnly=true)
    @Rollback(false)
	public void readMetadata() {
    	Metadata metadata=  metadataRepository.findByNameCustom("Metadata");
  		System.err.println(metadata.getName());

	}
    
        
    
    @Test
    @Transactional(transactionManager = "modelTransactionManager",readOnly=true)
    @Rollback(false)
	public void readDeviceAssoc() {
    	Map<String, Object> conditions=new HashMap<String, Object>();
  		conditions.put("deviceName", "IL2TS-TEST");
  		Device device=deviceRepository.findAssocEntity(conditions, "deviceSpec");
  		System.err.println(device.getDeviceSpec().getDeviceSpecName());

	}
    
    
    

}
