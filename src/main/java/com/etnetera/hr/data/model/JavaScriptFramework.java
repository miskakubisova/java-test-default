package com.etnetera.hr.data.model;

import com.etnetera.hr.data.HypeLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Data
@Entity
@NoArgsConstructor
public class JavaScriptFramework {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false)
	@ElementCollection(targetClass = Long.class)
	private List<Long> version;

	@Column(nullable = false)
	private Instant deprecationDate;

	@Column(nullable = false)
	private HypeLevel hypeLevel;

	public JavaScriptFramework(@NotEmpty String name, @NotEmpty List<Long> version, @NotNull Instant deprecationDate, @NotNull HypeLevel hypeLevel) {
		this.name = name;
		this.version = version;
		this.deprecationDate = deprecationDate;
		this.hypeLevel = hypeLevel;
	}
}
