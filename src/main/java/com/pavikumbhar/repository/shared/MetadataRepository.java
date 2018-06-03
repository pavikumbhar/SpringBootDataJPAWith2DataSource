package com.pavikumbhar.repository.shared;

import org.springframework.stereotype.Repository;

import com.pavikumbhar.repository.SharedBaseEntityRepository;
import com.pavikumbhar.shared.entity.Metadata;


/**
 * 
 * @author pavikumbhar
 *
 */
@Repository
public interface MetadataRepository extends SharedBaseEntityRepository<Metadata,Long>{

}
