package com.movie.post.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PostController {


	@RequestMapping("/post/post.wow")
	public String post() throws Exception {
		return "post/post";
	}



}
