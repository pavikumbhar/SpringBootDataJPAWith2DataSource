package com.pavikumbhar;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import com.pavikumbhar.model.entity.Device;
import com.pavikumbhar.model.entity.DeviceSpec;
import com.pavikumbhar.repository.model.DeviceRepository;
import com.pavikumbhar.repository.model.DeviceSpecRepository;
import com.pavikumbhar.repository.shared.MetadataRepository;
import com.pavikumbhar.shared.entity.Metadata;


/**
 * 
 * @author pavikumbhar
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
public class SpringDataJPAApplication extends SpringBootServletInitializer implements WebApplicationInitializer  , CommandLineRunner{
	
	public static void main(String[] args) {
        SpringApplication.run(SpringDataJPAApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringDataJPAApplication.class);
}

    @Autowired
    private DeviceSpecRepository deviceSpecRepository;
    
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    @Autowired
    private MetadataRepository metadataRepository;
    
	public void run(String... args) throws Exception {
	
	/*	DeviceSpec deviceSpec=new DeviceSpec();
		deviceSpec.setDeviceSpecName("IL2-F12");
		DeviceSpec deviceSpecSaved=deviceSpecRepository.save(deviceSpec);
		
		Device device=new Device();
		device.setDeviceName("IL2TS-1010");
		device.setDeviceSpec(deviceSpecSaved);
		deviceRepository.save(device);
		
		
		
		Metadata metadata =new Metadata();
		metadata.setName("Metadata");
		
		metadataRepository.save(metadata);*/
		
		//Device device=deviceRepository.findById(12L).get();
		
		/*Map<String, Object> conditions=new HashMap<String, Object>();
		conditions.put("id", 12L);
		Device device=deviceRepository.findAssocEntity(conditions, "deviceSpec");
		
		
		System.out.println(device.getDeviceSpec().getDeviceSpecName());
		
		Metadata metadata=  metadataRepository.findByNameCustom("Metadata");
		System.err.println(metadata.getName());*/
		
	}
	
	

}
