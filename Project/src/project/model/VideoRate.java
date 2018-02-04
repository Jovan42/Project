package project.model;

import java.util.Date;

/**
 * Created by jovan on 30-Jan-18.
 */
public class VideoRate {
	private int id;
	private boolean like;
	private Date created;
	private String ownerUserName;
	private int videoID;
	
	public VideoRate(int id, boolean like, Date created, String ownerUserName, int videoID) {
		super();
		this.id = id;
		this.like = like;
		this.created = created;
		this.ownerUserName = ownerUserName;
		this.videoID = videoID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getOwnerUserName() {
		return ownerUserName;
	}
	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
	public int getVideoID() {
		return videoID;
	}
	public void setVideoID(int videoID) {
		this.videoID = videoID;
	}
	
	
}
