package pl.jonasz.springboot_core_docker.model;

public class ModelClassOne {

	private Long id;


	public ModelClassOne(Long id) {
		this.id = id;
	}

	public ModelClassOne() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
