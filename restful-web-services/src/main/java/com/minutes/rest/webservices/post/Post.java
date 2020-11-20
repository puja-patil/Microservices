package com.minutes.rest.webservices.post;

public class Post {
	private Integer userId;
	private Integer postId;
	private String post;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Post(int userId, int postId, String post) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.post = post;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

}
