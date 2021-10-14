package com.etnetera.hr.service;

import com.etnetera.hr.config.MapStructConfig;
import com.etnetera.hr.data.model.JavaScriptFramework;
import com.etnetera.hr.data.resource.JavaScriptFrameworkResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkUpdateResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface JavaScriptFrameworkMapper {

    @Mapping(target = "id", ignore = true)
    JavaScriptFramework toJavaScriptFramework(JavaScriptFrameworkUpdateResource resource);
    List<JavaScriptFrameworkResource> toJavaScriptFrameworkResourceList(List<JavaScriptFramework> javaScriptFrameworks);
    JavaScriptFrameworkResource toJavaScriptFrameworkResource(JavaScriptFramework javaScriptFramework);
}
