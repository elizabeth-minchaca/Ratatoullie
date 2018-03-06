package ar.edu.unlp.lifia.ratatoullie.dto;

public class NotificationDto {
	private long id;	
	private long date;	
	private CommentDto comment;	
	private boolean seen;
	public NotificationDto() {
	}
	public NotificationDto(long id, long date, CommentDto comment, boolean seen) {
		setComment(comment);
		setDate(date);
		setSeen(seen);
		setId(id);
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public CommentDto getComment() {
		return comment;
	}
	public void setComment(CommentDto comment) {
		this.comment = comment;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

}
