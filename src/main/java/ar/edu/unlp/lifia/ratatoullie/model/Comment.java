package ar.edu.unlp.lifia.ratatoullie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;


@Entity
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	private String text;
	private long date;	
	@Any(metaColumn = @Column(name="commenting_type"))
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = { 
			 @MetaValue(targetEntity = Menu.class, value = "M"),
			 @MetaValue(targetEntity = Restaurant.class, value = "R")})
	@JoinColumn( name = "commenting_id")
	private Commenting commenting; 
	
	@Enumerated(EnumType.STRING)
	private Vote vote;
	public Comment() {
	}
	protected Comment(User user, String text, long date, Commenting commenting, Vote vote) {
		setCommenting(commenting);
		setDate(date);
		setText(text);
		setUser(user);
		setVote(vote);
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
	public Commenting getCommenting() {
		return commenting;
	}
	public void setCommenting(Commenting commenting) {
		this.commenting = commenting;
	}
	public Vote getVote() {
		return vote;
	}
	public void setVote(Vote vote) {
		this.vote = vote;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Ranking getRanking(){
		return getUser().getRanking();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}