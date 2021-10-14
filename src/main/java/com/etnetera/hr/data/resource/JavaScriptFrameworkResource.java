package com.etnetera.hr.data.resource;

import com.etnetera.hr.data.HypeLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

/**
 * This resource is used as return value to possible frontend or other systems communicating with this app.
 * It is always a good practise not to return database object directly.
 *
 * In this case it contains the same values as the database object {@link com.etnetera.hr.data.model.JavaScriptFramework} does,
 * but sometimes some DB fields can be hidden from the FE/other app.
 */
@Data
@AllArgsConstructor
public class JavaScriptFrameworkResource {

    @NonNull
    Long id;

    @NotBlank
    String name;

    @NotEmpty
    List<Long> version;

    @NotNull
    Instant deprecationDate;

    @NotNull
    HypeLevel hypeLevel;
}
