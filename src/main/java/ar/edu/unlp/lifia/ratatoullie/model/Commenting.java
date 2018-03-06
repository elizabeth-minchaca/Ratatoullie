package ar.edu.unlp.lifia.ratatoullie.model;

/**
 * Any class implementing this interface can be commented.
 * @author NATA
 *
 */
public interface Commenting {
	/**
	 * Adds a {@link Comment} to the receiver.
	 * @param comment
	 */
	public void comment(Comment comment);
	/**
	 * {@link Location} of the {@link Comment} receiver.
	 * @return
	 */
	public Location getLocation();
	public long getId();
}