package ar.edu.unlp.lifia.ratatoullie.dto;
import ar.edu.unlp.lifia.ratatoullie.model.Vote;

public class CommentDto {
	private UserMinDto user;
	private String text;
	private long date;
	private CommentingDto commenting;
	private Vote vote;
	public CommentDto() {
	}
	public CommentDto(UserMinDto user, String text, long date, CommentingDto commenting, Vote vote) {
		setCommenting(commenting);
		setDate(date);
		setText(text);
		setUser(user);
		setVote(vote);
	}
	
	public UserMinDto getUser() {
		return user;
	}
	public void setUser(UserMinDto user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public CommentingDto getCommenting() {
		return commenting;
	}
	public void setCommenting(CommentingDto commenting) {
		this.commenting = commenting;
	}
	public Vote getVote() {
		return vote;
	}
	public void setVote(Vote vote) {
		this.vote = vote;
	}
	
}
