package redneck.mongo;

import java.util.List;

/**
 * What example/tutorial would be complete without the ever-present "blog post"
 * crap? Before looking at what Spring's MongoDB integration gives me, I want to
 * write one myself with some basic CRUD capabilities.
 * 
 * @author Robb
 * 
 */
public interface BlogPostDao {
	
	public void insert(BlogPostVo newPost);
	public void delete(BlogPostVo oldPost);
	public void update(BlogPostVo criteria, BlogPostVo updatedPost);
	public List<BlogPostVo> findAll();
	public List<BlogPostVo> findByExample(BlogPostVo criteria);	
}
