package ar.edu.unlp.lifia.ratatoullie.dto;

import java.util.List;

public class UserDto {
	private String name;
	private String lastName;
	private String mail;
	private boolean enable;
	private LocationDto location;	
		
	private String ranking; 		
	private List<UserMinDto> followers;	
    private List<UserMinDto> followings;   
    private List<RecommendationDto> recommendations;
	public UserDto() {
	}
	public UserDto(String name, String lastName, String mail, boolean enable, LocationDto location, String ranking){
		setEnable(enable);
		setLastName(lastName);
		setMail(mail);
		setName(name);
		setRanking(ranking);
		setLocation(location);
	}
	public UserDto(String name, String lastName, String mail, boolean enable, LocationDto location,	
	 String ranking, List<UserMinDto> followers, List<UserMinDto> followings, List<RecommendationDto> recommendations) {
		setEnable(enable);
		setFollowers(followers);
		setFollowings(followings);
		setLastName(lastName);
		setLocation(location);
		setMail(mail);

		setName(name);
		setRanking(ranking);
		setRecommendations(recommendations);
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
	public boolean getEnable() {
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
	public List<UserMinDto> getFollowers() {
		return followers;
	}
	public void setFollowers(List<UserMinDto> followers) {
		this.followers = followers;
	}
	public List<UserMinDto> getFollowings() {
		return followings;
	}
	public void setFollowings(List<UserMinDto> followings) {
		this.followings = followings;
	}
	public List<RecommendationDto> getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List<RecommendationDto> recommendations) {
		this.recommendations = recommendations;
	}
	
}