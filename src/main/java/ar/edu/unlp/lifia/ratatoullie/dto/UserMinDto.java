package ar.edu.unlp.lifia.ratatoullie.dto;

public class UserMinDto {
	private String name;
	private String lastName;
	private String mail;
	private boolean enable;
	private LocationDto location;		
	private String ranking;
	private int commentSize;
	public UserMinDto() {
	}
	public UserMinDto(String name,String lastName, String mail, boolean enable, LocationDto location, String ranking, int commentSize) {
		setEnable(enable);
		setLastName(lastName);
		setLocation(location);
		setLocation(location);
		setMail(mail);
		setName(name);
		setRanking(ranking);
		setCommentSize(commentSize);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public int getCommentSize() {
		return commentSize;
	}
	public void setCommentSize(int commentSize) {
		this.commentSize = commentSize;
	}
}