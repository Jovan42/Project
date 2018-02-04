package project.model;

import java.util.Comparator;
import java.util.Date;

public class Comment {
	private int id;
	private String content;
	private Date created;
	private String ownerUserName;
	private int videoID;
	private boolean deleted;
	private int likes;
	private int dislikes;
	
	public Comment(int id, String content, Date created, String ownerUserName, int videoID, boolean deleted, int likes, int dislikes) {
		super();
		this.id = id;
		this.content = content;
		this.created = created;
		this.ownerUserName = ownerUserName;
		this.videoID = videoID;
		this.deleted = deleted;
		this.likes = likes;
		this.dislikes = dislikes;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public static Comparator<Comment> ratingComparatorAsc = new Comparator<Comment>() {
		
		@Override
		public int compare(Comment c1, Comment c2) {
			Integer rating1 = c1.getLikes() - c1.getDislikes(); 
			Integer rating2 = c2.getLikes() - c2.getDislikes(); 
			
			return rating1.compareTo(rating2);
		}
	};
	
	public static Comparator<Comment> ratingComparatorDesc = new Comparator<Comment>() {
		
		@Override
		public int compare(Comment c1, Comment c2) {
			Integer rating1 = c1.getLikes() - c1.getDislikes(); 
			Integer rating2 = c2.getLikes() - c2.getDislikes(); 
			
			return rating2.compareTo(rating1);
		}
	};
	
	public static Comparator<Comment> dateComparatorAsc = new Comparator<Comment>() {

		@Override
		public int compare(Comment c1, Comment c2) {
			Date date1 = c1.getCreated();
			Date date2 = c2.getCreated();
			
			return date1.compareTo(date2);
		}
	};
	
	public static Comparator<Comment> dateComparatorDesc = new Comparator<Comment>() {

		@Override
		public int compare(Comment c1, Comment c2) {
			Date date1 = c1.getCreated();
			Date date2 = c2.getCreated();
			
			return date2.compareTo(date1);
		}
	};
}
