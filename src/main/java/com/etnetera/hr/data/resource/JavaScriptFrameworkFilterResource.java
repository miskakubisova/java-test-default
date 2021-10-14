package com.etnetera.hr.data.resource;

import com.etnetera.hr.data.HypeLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.time.Instant;

/**
 * Filter resource used to filter through frameworks.
 * All fields are nullable as it is possible to filter through either field by filed or all fields together.
 */
@Value
@AllArgsConstructor
public class JavaScriptFrameworkFilterResource {
    @Nullable
    String name;

    @Nullable
    Instant deprecationDate;

    @Nullable
    HypeLevel hypeLevel;
}
