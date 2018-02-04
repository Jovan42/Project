package project.model;

import java.util.Date;

public class CommentRate {
	private int id;
	private boolean like;
	private Date created;
	private String ownerUserName;
	private int commentID;
	
	public CommentRate(int id, boolean like, Date created, String ownerUserName, int commentID) {
		super();
		this.id = id;
		this.like = like;
		this.created = created;
		this.ownerUserName = ownerUserName;
		this.commentID = commentID;
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
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}	
}
