package ar.edu.unlp.lifia.ratatoullie.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.DigestUtils;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ForeignKey;

@Entity
@Inheritance( strategy = InheritanceType.JOINED)
@Table(name="user_regular")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="ratatoullie_id")
	private Ratatoullie ratatoullie;
	private String name;
	private String lastName;
	@Column(unique = true)
	private String mail;
	@Access(AccessType.FIELD)
	private String password;
	private boolean enable;
	@OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "location_id")
	private Location location;	//One-way association
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Comment> myComments;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ranking_id", foreignKey = @ForeignKey(name = "ranking_id_fk"))
	private Ranking ranking; 	//One-way association
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy="followings")
	private Set<User> followers; 
	@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="following_follower",
		joinColumns={@JoinColumn(name="follower_id")},
		inverseJoinColumns={@JoinColumn(name="following_id")})
    private Set<User> followings;
	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="recommended",cascade=CascadeType.ALL)
    private List<Recommendation> recommendations;
	
	protected User() {
	}
	
	protected User(String name,String lastName, String mail, String password, Location location){
		setFollowers(new HashSet<User>());
		setFollowings(new HashSet<User>());
		setLocation(location);
		setMyComments(new ArrayList<Comment>());
		setName(name);
		setRanking(new Visitor());
		setRecommendations(new ArrayList<Recommendation>());
		setRatatoullie(Ratatoullie.getInstance());
		setEnable(true);
		setMail(mail);
		setPassword(password);
		setLastName(lastName);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}	

	public List<Comment> getMyComments() {
		return myComments;
	}
	public void setMyComments(List<Comment> myComments) {
		this.myComments = myComments;
	}
	public int myCommentsSize(){
		return getMyComments().size();
	}
	public Ranking getRanking() {
		return ranking;
	}
	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}
	public void updateRanking() {
		getRanking().update(this);
	}
	public Comment comment(String text, long date, Commenting commenting, Vote vote){
		Comment comment = new Comment(this, text, date, commenting, vote);
		if(!getMyComments().contains(comment)){			
			getMyComments().add(comment);
			updateRanking();
			comment.getCommenting().comment(comment);
			return comment;
		}		
		return null;
	}
	/**
	 * The {@link User} i follow.
	 * @return
	 */
	public Set<User> getFollowings() {
		return followings;
	}
	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}
	/**
	 * The {@link User} who follow me
	 * @return
	 */
	public Set<User> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	/**
	 * Follow a {@link User}
	 * @param user
	 */
	public void follow(User user){
		if(!this.equals(user)){
			if(!getFollowings().contains(user)){
				getFollowings().add(user);
				user.followMe(this);
			}
		}
		
	}
	/**
	 * Used to follow a {@link User}
	 * @param user
	 */
	private void followMe(User user) {
		if(!getFollowers().contains(user)){
			getFollowers().add(user);
		}		
	}
	/**
	 * Stop following a {@link User}
	 * @param user
	 */
	public void stopFollowing(User user){
		if(getFollowings().contains(user)){
			getFollowings().remove(user);
			user.stopFollowMe(this);
		}		
	}
	/**
	 * Used to stop following a {@link User}
	 * @param user
	 */
	private void stopFollowMe(User user) {
		getFollowers().remove(user);
	}
	/**
	 * Recommends a {@link Menu} to a {@link User}
	 * @param menu
	 * @param user
	 * @param text
	 * @param date
	 */
	public void recommend(Menu menu, User user, String text, long date){
		Recommendation recommendation = new Recommendation(this,user,menu,date,text);
		sendRecommend(recommendation);
	}
	/**
	 * Used to send a {@link Recommendation}
	 * @param recommendation
	 */
	private void sendRecommend(Recommendation recommendation) {
		recommendation.getRecommended().addRecommendation(recommendation);
	}
	private void addRecommendation(Recommendation recommendation) {
		if(!getRecommendations().contains(recommendation)){
			getRecommendations().add(recommendation);
		}				
	}
	public List<Recommendation> getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}
	@Override
	public boolean equals(Object obj) {
		return this.getMail().compareTo(((User)obj).getMail())==0;
	}
	@Override
	public int hashCode() {
		return getMail().hashCode();
	}
	/**
	 * Recommends a {@link Menu} to all my followers. 
	 * @param menu
	 * @param text
	 * @param date
	 */
	public void recommendFollowers(Menu menu, String text, long date){
		for (User user : getFollowers()) {
			recommend(menu, user, text, date);
		}
	}
	public Ratatoullie getRatatoullie() {
		return ratatoullie;
	}
	public void setRatatoullie(Ratatoullie ratatoullie) {
		this.ratatoullie = ratatoullie;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = toMd5(password);
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	private String toMd5(String aString) {
		return DigestUtils.md5DigestAsHex(aString.getBytes());
	}
	public boolean isYourPassword(String aString) {
		return this.getPassword().equals(this.toMd5(aString));		
	}
}