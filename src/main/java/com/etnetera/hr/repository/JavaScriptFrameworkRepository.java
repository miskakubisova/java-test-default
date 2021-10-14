package com.etnetera.hr.repository;

import com.etnetera.hr.data.HypeLevel;
import org.springframework.data.repository.CrudRepository;

import com.etnetera.hr.data.model.JavaScriptFramework;

import java.time.Instant;
import java.util.List;

/**
 * Spring data repository interface used for accessing the data in database.
 * 
 * @author Etnetera
 *
 */
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {
    List<JavaScriptFramework> findByNameContainingOrDeprecationDateOrHypeLevel(String name, Instant deprecationDate, HypeLevel hypeLevel);

}
