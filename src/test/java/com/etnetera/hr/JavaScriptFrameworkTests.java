package com.etnetera.hr;

import com.etnetera.hr.data.HypeLevel;
import com.etnetera.hr.data.model.JavaScriptFramework;
import com.etnetera.hr.data.resource.JavaScriptFrameworkFilterResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkResource;
import com.etnetera.hr.data.resource.JavaScriptFrameworkUpdateResource;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.service.JavaScriptFrameworkMapper;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import com.etnetera.hr.service.JavaScriptFrameworkServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Class used for Spring Boot/MVC based tests.
 *
 * @author Etnetera
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkTests {

    @Mock
    JavaScriptFrameworkRepository repository;

    @Mock
    JavaScriptFrameworkMapper mapper;

    private JavaScriptFrameworkService service;

    private Random random;

    @Before
    public void setUp() {
        random = new Random();
        MockitoAnnotations.initMocks(this);
        service = new JavaScriptFrameworkServiceImpl(repository, mapper);
    }

    @Test
    public void createFrameworkTest() {
        var id = random.nextLong();
        var toBeSavedFramework = new JavaScriptFrameworkUpdateResource(
                "My awesome first framework",
                List.of(random.nextLong()),
                Instant.now(),
                HypeLevel.LOW
        );
        var expectedFramework = toJavaScriptFramework(toBeSavedFramework);
        var expectedResource = toJavaScriptFrameworkResource(id, toBeSavedFramework);

        when(mapper.toJavaScriptFramework(any(JavaScriptFrameworkUpdateResource.class))).thenReturn(expectedFramework);
        when(mapper.toJavaScriptFrameworkResource(any(JavaScriptFramework.class))).thenReturn(expectedResource);
        when(repository.save(any(JavaScriptFramework.class))).thenReturn(expectedFramework);

        var actual = service.createJavaScriptFramework(toBeSavedFramework);

        assertion(expectedResource, actual);
    }

    @Test
    public void updateFrameworkTest() {
        var id = random.nextLong();
        var updatedFramework = new JavaScriptFrameworkUpdateResource(
                "My awesome updated framework",
                List.of(random.nextLong(), random.nextLong()),
                Instant.now(),
                HypeLevel.VERY_HIGH
        );
        var toBeUpdatedFramework = new JavaScriptFramework(
                "My awesome first framework",
                List.of(random.nextLong()),
                Instant.now(),
                HypeLevel.LOW);

        when(repository.findById(id)).thenReturn(Optional.of(toBeUpdatedFramework));
        when(repository.save(any(JavaScriptFramework.class))).thenReturn(toJavaScriptFramework(updatedFramework));
        when(mapper.toJavaScriptFrameworkResource(any(JavaScriptFramework.class))).thenReturn(toJavaScriptFrameworkResource(id, updatedFramework));

        var actual = service.updateJavaScriptFrameworkById(id, updatedFramework);

        assertion(toJavaScriptFrameworkResource(id, updatedFramework), actual);
    }

    @Test
    public void searchFrameworkByNameTest() {
        var id = random.nextLong();
        var filter = new JavaScriptFrameworkFilterResource("Awesome", null, null);
        var framework = new JavaScriptFramework(
                "My awesome first framework",
                List.of(random.nextLong()),
                Instant.now(),
                HypeLevel.LOW
        );
        when(repository.findByNameContainingOrDeprecationDateOrHypeLevel(anyString(), any(Instant.class) , any(HypeLevel.class))).thenReturn(List.of(framework));
        when(mapper.toJavaScriptFrameworkResourceList(anyList())).thenReturn(List.of(toJavaScriptFrameworkResource(id, framework)));

        var actual = service.getJavaScriptFrameworksByFilter(filter);

        Assertions.assertThat(List.of(toJavaScriptFrameworkResource(id, framework))).containsExactlyInAnyOrderElementsOf(actual);
    }

    @Test
    public void deleteFrameworkTest() {
        var id = random.nextLong();
        doNothing().when(repository).deleteById(any(Long.class));
        Assertions.assertThatCode(() -> service.deleteJavaScriptFrameworkById(id)).doesNotThrowAnyException();
    }

    ///
    /// Helper methods
    ///

    private void assertion(JavaScriptFrameworkResource toBeSavedFramework, JavaScriptFrameworkResource savedFramework) {
        Assertions.assertThat(savedFramework).extracting(
                JavaScriptFrameworkResource::getName,
                JavaScriptFrameworkResource::getVersion,
                JavaScriptFrameworkResource::getDeprecationDate,
                JavaScriptFrameworkResource::getHypeLevel
        ).containsExactlyInAnyOrder(
                toBeSavedFramework.getName(), toBeSavedFramework.getVersion(), toBeSavedFramework.getDeprecationDate(), toBeSavedFramework.getHypeLevel()
        );
    }

    private JavaScriptFramework toJavaScriptFramework(JavaScriptFrameworkUpdateResource resource) {
        return new JavaScriptFramework(
                resource.getName(),
                resource.getVersion(),
                resource.getDeprecationDate(),
                resource.getHypeLevel()
        );
    }

    private JavaScriptFrameworkResource toJavaScriptFrameworkResource(Long id, JavaScriptFrameworkUpdateResource resource) {
        return new JavaScriptFrameworkResource(
                id,
                resource.getName(),
                resource.getVersion(),
                resource.getDeprecationDate(),
                resource.getHypeLevel()
        );
    }

    private JavaScriptFrameworkResource toJavaScriptFrameworkResource(Long id, JavaScriptFramework resource) {
        return new JavaScriptFrameworkResource(
                id,
                resource.getName(),
                resource.getVersion(),
                resource.getDeprecationDate(),
                resource.getHypeLevel()
        );
    }
}
