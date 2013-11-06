package redneck.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class BlogPostDaoMongoImpl implements BlogPostDao {

	protected static final String COLLECTION = "blog_posts";

	private final static Logger log = Logger
			.getLogger(BlogPostDaoMongoImpl.class);

	private MongoClient mongoClient;
	private DBCollection blogPosts;

	public BlogPostDaoMongoImpl(DB db) throws UnknownHostException {
		if (!db.collectionExists(COLLECTION))
			db.createCollection(COLLECTION, null);

		blogPosts = db.getCollection(COLLECTION);
	}

	@Override
	public void insert(BlogPostVo newPost) {
		log.debug(String.format("Inserting: %s", newPost.toString()));
		BasicDBObject o = voToDbObject(newPost);
		blogPosts.insert(o);
	}

	@Override
	public void delete(BlogPostVo oldPost) {
		log.debug(String.format("Deleting: %s", oldPost.toString()));
		blogPosts.remove(voToDbObject(oldPost));
	}

	@Override
	public void update(BlogPostVo criteria, BlogPostVo updatedPost) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BlogPostVo> findAll() {
		log.debug("Querying ALL: ");
		List<BlogPostVo> results = new ArrayList<BlogPostVo>();
		DBCursor cursor = blogPosts.find();
		while (cursor.hasNext()) {
			results.add(dbObjectToVo(cursor.next()));
		}
		return results;
	}

	@Override
	public List<BlogPostVo> findByExample(BlogPostVo criteria) {
		log.debug(String.format("Querying by example: %s", criteria.toString()));
		log.debug(String.format("New DBObject will be: %s", voToDbObject(criteria)));
		List<BlogPostVo> results = new ArrayList<BlogPostVo>();
		DBCursor cursor = blogPosts.find(voToDbObject(criteria));
		while (cursor.hasNext()) {
			results.add(dbObjectToVo(cursor.next()));
		}
		return results;
	}

	private static BlogPostVo dbObjectToVo(DBObject o) {
		BlogPostVo vo = new BlogPostVo();
		vo.setAuthor((String) o.get("author"));
		vo.setPostBodyContents((String) o.get("postContents"));
		vo.setPostedDate((Date) o.get("createdDate"));
		vo.setTags((String) o.get("tags"));
		return vo;
	}

	private static BasicDBObject voToDbObject(BlogPostVo newPost) {
		BasicDBObject o = new BasicDBObject();
		if (newPost.getAuthor() != null)
			o.append("author", newPost.getAuthor());
		if (newPost.getPostBodyContents() != null)
			o.append("postContents", newPost.getPostBodyContents());
		if (newPost.getPostedDate() != null)
			o.append("createdDate", newPost.getPostedDate());
		if (newPost.getTags() != null)
			o.append("tags", newPost.getTags());
		
		//log.debug(String.format("New DBObject: %s", o));
		return o;
	}
}
