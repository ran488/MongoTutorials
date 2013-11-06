package redneck.mongo;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class BlogPostDaoTest {

	private BlogPostDao dao;
	private static MongoClient mongoClient;
	private static DB db;
	private final static Date POST_DATE = new Date();

	@org.junit.BeforeClass
	public static void init() throws UnknownHostException {
		mongoClient = new MongoClient("localhost", 27017);
		db = mongoClient.getDB("test");
	}

	@Before
	public void setUp() throws Exception {
		dao = new BlogPostDaoMongoImpl(db);
		db.getCollection(BlogPostDaoMongoImpl.COLLECTION).drop();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testInsert() {
		BlogPostVo newPost = getNewPost("testInsert");
		dao.insert(newPost);
		DBCursor cursor = db.getCollection(BlogPostDaoMongoImpl.COLLECTION)
				.find();
		assertEquals(1, cursor.count());
	}

	@Test
	public void testFindAll() {
		for (int x = 0; x < 10; x++) {
			dao.insert(getNewPost(String.format("testFindAll+%s", x)));
		}
		List<BlogPostVo> posts = dao.findAll();
		for (BlogPostVo post : posts) {
			System.out.println(post);
		}
		assertEquals(10, posts.size());
	}

	private BlogPostVo getNewPost(String name) {
		BlogPostVo newPost = new BlogPostVo();
		newPost.setAuthor(name);
		newPost.setPostBodyContents("A bunch of stuff nobody cares about");
		newPost.setPostedDate(POST_DATE);
		newPost.setTags("tests,stuff,crap");
		return newPost;
	}

}