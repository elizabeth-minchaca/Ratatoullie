package ar.edu.unlp.lifia.ratatoullie.dto;

public class CommentingDto {
	private String name;
	private long id;
	public CommentingDto() {
	}
	public CommentingDto(String name, long id) {
		setId(id);
		setName(name);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}