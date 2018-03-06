package ar.edu.unlp.lifia.ratatoullie.dto;

import java.util.List;

public class CategoryDto {
	private String name;
	private List<String> benefits;
	public CategoryDto() {
	}
	public CategoryDto(String name,List<String> benefits) {
		setBenefits(benefits);
		setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getBenefits() {
		return benefits;
	}
	public void setBenefits(List<String> benefits) {
		this.benefits = benefits;
	}
	
}