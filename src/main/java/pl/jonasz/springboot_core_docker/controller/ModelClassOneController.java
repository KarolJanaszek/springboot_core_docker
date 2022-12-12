package pl.jonasz.springboot_core_docker.controller;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.jonasz.springboot_core_docker.model.ModelClassOne;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/one", produces = MediaType.APPLICATION_JSON_VALUE)
public class ModelClassOneController {

	private List<ModelClassOne> database;

	@GetMapping
	public List<ModelClassOne> getAll() {
		return database;
	}

	@PostMapping
	public void post(@ModelAttribute ModelClassOne one) {
		database.add(one);
	}

	@GetMapping("/{id}")
	public ModelClassOne get(@PathVariable Long id) {
		return database.stream()
			.filter(o -> o.getId().equals(id))
			.findFirst()
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		ModelClassOne modelClassOne = get(id);
		database.remove(modelClassOne);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void init() {
		this.database = Arrays.asList(
			new ModelClassOne(1L),
			new ModelClassOne(2L),
			new ModelClassOne(3L)
		);
	}
}
