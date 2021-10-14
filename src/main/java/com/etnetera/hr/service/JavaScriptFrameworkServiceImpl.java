package com.etnetera.hr.service;

import com.etnetera.hr.data.resource.JavaScriptFrameworkFilterResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkUpdateResource;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {
    private JavaScriptFrameworkRepository repository;
    private JavaScriptFrameworkMapper mapper;

    @Override
    public JavaScriptFrameworkResource createJavaScriptFramework(JavaScriptFrameworkUpdateResource resource) {
        var framework = mapper.toJavaScriptFramework(resource);
        return mapper.toJavaScriptFrameworkResource(repository.save(framework));
    }

    @Override
    public JavaScriptFrameworkResource getJavaScriptFrameworkById(Long id) {
        return mapper.toJavaScriptFrameworkResource(
                repository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Java script framework with id " + id + " was not found.")
                ));
    }

    @Override
    public List<JavaScriptFrameworkResource> getJavaScriptFrameworksByFilter(JavaScriptFrameworkFilterResource resource) {
        return mapper.toJavaScriptFrameworkResourceList(
                repository.findByNameContainingOrDeprecationDateOrHypeLevel(resource.getName(), resource.getDeprecationDate(), resource.getHypeLevel()));
    }

    @Override
    public JavaScriptFrameworkResource updateJavaScriptFrameworkById(Long id, JavaScriptFrameworkUpdateResource updateResource) {
        var framework = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Java script framework with id " + id + " was not found.")
        );

        if (updateResource.getName() != null && !updateResource.getName().isBlank()) {
            framework.setName(updateResource.getName());
        }

        if (updateResource.getVersion() != null) {
            var updatedVersion = CollectionUtils.union(framework.getVersion(), updateResource.getVersion())
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());
            framework.setVersion(updatedVersion);
        }

        if (updateResource.getDeprecationDate() != null) {
            framework.setDeprecationDate(updateResource.getDeprecationDate());
        }

        if (updateResource.getHypeLevel() != null) {
            framework.setHypeLevel(updateResource.getHypeLevel());
        }

        return mapper.toJavaScriptFrameworkResource(repository.save(framework));
    }

    @Override
    public void deleteJavaScriptFrameworkById(Long id) {
        repository.deleteById(id);
    }
}
