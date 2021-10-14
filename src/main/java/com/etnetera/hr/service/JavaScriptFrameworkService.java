package com.etnetera.hr.service;

import com.etnetera.hr.data.resource.JavaScriptFrameworkFilterResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkUpdateResource;

import java.util.List;

public interface JavaScriptFrameworkService {

    /**
     * Creates new framework object and saves it to DB
     * @param resource resource object
     */
    JavaScriptFrameworkResource createJavaScriptFramework(JavaScriptFrameworkUpdateResource resource);

    /**
     * Finds framework based on id
     * @param id id of the framework
     */
    JavaScriptFrameworkResource getJavaScriptFrameworkById(Long id);

    /**
     * Finds frameworks based on a filter
     * @param resource filter resource
     */
    List<JavaScriptFrameworkResource> getJavaScriptFrameworksByFilter(JavaScriptFrameworkFilterResource resource);

    /**
     * Updates framework object with fields from update resource object
     * @param id id of the original object
     * @param updateResource update resource with fields to be updated
     */
    JavaScriptFrameworkResource updateJavaScriptFrameworkById(Long id, JavaScriptFrameworkUpdateResource updateResource);

    /**
     * Deletes framework object based on id
     * @param id id of the framework
     */
    void deleteJavaScriptFrameworkById(Long id);
}
