package project.model;

import java.util.Comparator;
import java.util.Date;

import project.enums.Visibility;;

public class Video {
	private int id;
	private String name;
	private String url;
	private String tumbnailURL;
	private String description;
	private Visibility visibility;
	private boolean comments;
	private boolean rate;
	private boolean blocked;
	private int views;
	private Date created;
	private String ownersUserName;
	private boolean deleted;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnersUserName() {
		return ownersUserName;
	}

	public void setOwnersUserName(String ownersUserName) {
		this.ownersUserName = ownersUserName;
	}

	public Video(int id, String url, String name, String tumbnailURL, String description, Visibility visibility, boolean comments,
			boolean rate, boolean blocked, int views, Date created, String ownersUserName, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.tumbnailURL = tumbnailURL;
		this.description = description;
		this.visibility = visibility;
		this.comments = comments;
		this.rate = rate;
		this.blocked = blocked;
		this.views = views;
		this.created = created;
		this.ownersUserName = ownersUserName;
		this.deleted = deleted;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTumbnailURL() {
		return tumbnailURL;
	}
	public void setTumbnailURL(String tumbnailURL) {
		this.tumbnailURL = tumbnailURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public boolean isComments() {
		return comments;
	}
	public void setComments(boolean comments) {
		this.comments = comments;
	}
	public boolean isRate() {
		return rate;
	}
	public void setRate(boolean rate) {
		this.rate = rate;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getOwnerUserName() {
		return ownersUserName;
	}
	public void setOwnerID(String ownersUserName) {
		this.ownersUserName = ownersUserName;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public static Comparator<Video> nameComparatorAsc = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			String name1 = v1.getName().toUpperCase();
			String name2 = v2.getName().toUpperCase();
			
			return name1.compareTo(name2);
		}
	};
	public static Comparator<Video> nameComparatorDesc = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			String name1 = v1.getName().toUpperCase();
			String name2 = v2.getName().toUpperCase();
			
			return name2.compareTo(name1);
		}
	};
	
	public static Comparator<Video> ownerComparatorAsc = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			String owner1 = v1.getOwnerUserName().toUpperCase();
			String owner2 = v2.getOwnersUserName().toUpperCase();
			
			return owner1.compareTo(owner2);
		}
	};
	
	public static Comparator<Video> ownerComparatorDesc = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			String owner1 = v1.getOwnerUserName().toUpperCase();
			String owner2 = v2.getOwnersUserName().toUpperCase();
			
			return owner2.compareTo(owner1);
		}
	};
	
	public static Comparator<Video> viewsComparatorAsc = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			Integer views1 = v1.getViews();
			Integer views2 = v2.getViews();
			
			return views1.compareTo(views2);
		}
	};
	
	public static Comparator<Video> viewsComparatorDesc = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			Integer views1 = v1.getViews();
			Integer views2 = v2.getViews();
			
			return views2.compareTo(views1);
		}
	};
	
	public static Comparator<Video> dateComparatorAsc = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			Date date1 = v1.getCreated();
			Date date2 = v2.getCreated();
			
			return date1.compareTo(date2);
		}
	};
	
	public static Comparator<Video> dateComparatorDesc = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			Date date1 = v1.getCreated();
			Date date2 = v2.getCreated();
			
			return date2.compareTo(date1);
		}
	};
}
