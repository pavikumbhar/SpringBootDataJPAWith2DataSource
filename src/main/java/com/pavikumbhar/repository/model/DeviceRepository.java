package com.pavikumbhar.repository.model;

import org.springframework.stereotype.Repository;

import com.pavikumbhar.model.entity.Device;
import com.pavikumbhar.repository.ModelBaseEntityRepository;

@Repository
public interface DeviceRepository extends ModelBaseEntityRepository<Device,Long>{

}
