package com.etnetera.hr.data.resource;

import com.etnetera.hr.data.HypeLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

/**
 * This resource is used when new framework object is created or updated.
 *
 * It contains the same fields as DB object {@link com.etnetera.hr.data.model.JavaScriptFramework} except for
 * id as it cannot be changed on update and when creating new object id is auto-generated.
 */
@Data
@AllArgsConstructor
public class JavaScriptFrameworkUpdateResource {

    @NotBlank
    String name;

    @NotEmpty
    List<Long> version;

    @NotNull
    Instant deprecationDate;

    @NotNull
    HypeLevel hypeLevel;
}
