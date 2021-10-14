package com.etnetera.hr.controller;

import com.etnetera.hr.data.model.JavaScriptFramework;
import com.etnetera.hr.data.resource.JavaScriptFrameworkFilterResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkUpdateResource;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
@AllArgsConstructor
public class JavaScriptFrameworkController {

    private JavaScriptFrameworkRepository repository;
    private JavaScriptFrameworkService service;

    @GetMapping("/frameworks")
    public Iterable<JavaScriptFramework> frameworks() {
        return repository.findAll();
    }

    @PostMapping(value = "/framework")
    public JavaScriptFrameworkResource createFramework(
            @Validated @RequestBody JavaScriptFrameworkUpdateResource resource) {
        return service.createJavaScriptFramework(resource);
    }

    @GetMapping("framework/{id}")
    public JavaScriptFrameworkResource getFramework(
            @PathVariable("id") Long id) {
        return service.getJavaScriptFrameworkById(id);
    }

    @GetMapping("framework/search")
    public Iterable<JavaScriptFrameworkResource> searchFrameworks(
            @Validated @RequestBody JavaScriptFrameworkFilterResource resource) {
        return service.getJavaScriptFrameworksByFilter(resource);
    }

    @PostMapping("framework/{id}")
    public JavaScriptFrameworkResource updateFramework(
            @PathVariable("id") Long id,
            @Validated @RequestBody JavaScriptFrameworkUpdateResource updateResource) {
        return service.updateJavaScriptFrameworkById(id, updateResource);
    }

    @DeleteMapping("framework/{id}")
    public void deleteFramework(
            @PathVariable("id") Long id) {
        service.deleteJavaScriptFrameworkById(id);
    }

}
