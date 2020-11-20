package com.minutes.rest.webservices.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PostDaoService {
	private static List<Post> posts = new ArrayList<Post>();
	private static int postsCount = 4;

	static {
		posts.add(new Post(1, 1, "Good Morning Post"));
		posts.add(new Post(1, 2, "Good Night Post"));
		posts.add(new Post(2, 3, "Good Morning"));
		posts.add(new Post(3, 4, "Good Night"));
	}

	public List<Post> findPostsByUser(int userId) {
		List<Post> userPosts = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getUserId() == userId) {
				userPosts.add(post);
			}
		}
		return userPosts;
	}

	public Post savePost(int userId, Post post) {
		post.setUserId(userId);
		//System.out.println(post.getPostId() + " - " + postsCount);
		if (post.getPostId() == 0) {
			post.setPostId(++postsCount);
		}
		posts.add(post);
		return post;
	}

	public Post findPost(int userId, int postId) {
		for (Post post : posts) {
			if (post.getUserId() == userId && post.getPostId() == postId) {
				return post;
			}
		}
		return null;
	}

}

//logging.level.org.springframework = debug