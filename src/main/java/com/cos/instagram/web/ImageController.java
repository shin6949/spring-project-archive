package com.cos.instagram.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.dto.LoginUser;
import com.cos.instagram.domain.image.Image;
import com.cos.instagram.service.ImageService;
import com.cos.instagram.web.dto.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;
	
	@GetMapping({"", "/", "/image/feed"})
	public String feed(
			String tag,
			@LoginUserAnnotation LoginUser loginUser,
			Model model) {
		System.out.println("loginUser : "+loginUser);
		model.addAttribute("images", imageService.feedPhoto(loginUser.getId(), tag));
		return "image/feed";
	}
	
	@GetMapping("/test/image/feed")
	public @ResponseBody List<Image> testFeed(
			String tag,
			@LoginUserAnnotation LoginUser loginUser) {
		return imageService.feedPhoto(loginUser.getId(), tag);
	}
	
	@GetMapping("/image/uploadForm")
	public String imageUploadForm(@RequestParam(name = "location", required = false) String location, Model model) {
		if(location == null) {
			return "redirect:/location/find";
		}

		model.addAttribute("location", location);
		return "image/image-upload";
	}
	
	@PostMapping("/image")
	public String imageUpload(
			@LoginUserAnnotation LoginUser loginUser,
			ImageReqDto imageReqDto) {
		
		imageService.photoUpload(imageReqDto, loginUser.getId());
		
		return "redirect:/user/"+loginUser.getId();
	}
	
	@GetMapping("/image/explore")
	public String imageExplore(@LoginUserAnnotation LoginUser loginUser, Model model) {
		model.addAttribute("images", imageService.popularPhoto(loginUser.getId()));
		return "image/explore";
		
	}
}




