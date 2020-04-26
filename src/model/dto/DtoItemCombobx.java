package model.dto;

public class DtoItemCombobx {

	private int id;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DtoItemCombobx(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}

}
