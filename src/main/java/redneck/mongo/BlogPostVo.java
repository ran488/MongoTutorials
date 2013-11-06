package redneck.mongo;

import java.io.Serializable;
import java.util.Date;

/**
 * Simple bean to represent a blog posting. Will be persisted using MongoDB
 * 
 * @author Robb
 * 
 */
public class BlogPostVo implements Serializable {

	private static final long serialVersionUID = -3607460895866972402L;

	private String author;
	private String postBodyContents;
	private String tags;
	private Date postedDate;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPostBodyContents() {
		return postBodyContents;
	}

	public void setPostBodyContents(String postBodyContents) {
		this.postBodyContents = postBodyContents;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getPostedDate() {
		if (postedDate != null)
			return (Date) postedDate.clone();
		else
			return null;
	}

	public void setPostedDate(Date postedDate) {
		if (postedDate != null)
			this.postedDate = (Date) postedDate.clone();
	}

	@Override
	public String toString() {
		return String
				.format("BlogPostVo [author=%s, postBodyContents=%s, tags=%s, postedDate=%s]",
						author, postBodyContents, tags, postedDate);
	}
}
