package com.minutes.rest.webservices.post;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PostController {

	@Autowired
	PostDaoService service = new PostDaoService();

	@GetMapping("/users/{userId}/posts")
	public List<Post> getPostsByUser(@PathVariable int userId) {
		return service.findPostsByUser(userId);
	}

	@GetMapping("/users/{userId}/posts/{postId}")
	public Post getPost(@PathVariable int userId, @PathVariable int postId) {
		return service.findPost(userId, postId);
	}

	@PostMapping("/users/{userId}/posts")
	public ResponseEntity<Object> savePost(@PathVariable int userId, @RequestBody Post post) {
		Post savedPost = service.savePost(userId, post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}")
				.buildAndExpand(savedPost.getPostId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
