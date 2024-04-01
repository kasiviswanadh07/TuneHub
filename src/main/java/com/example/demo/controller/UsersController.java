package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UsersController {
	@Autowired
	UsersService service;
	
	@Autowired
	SongService songService;
 @PostMapping("/registration")
 public String addUsers(@ModelAttribute Users user) {
	 System.out.println(user);
//		 @RequestParam("username") String username,
//		 @RequestParam("email") String email,
//		 @RequestParam("password") String password,
//		 @RequestParam("gender") String gender,
//		 @RequestParam("role") String role,
//		 @RequestParam("address") String address
		 
		 
//	 System.out.println(user.getUsername()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getGender()+" "+user.getRole()+" "+user.getAddress());
	 boolean userStatus=service.emailExists(user.getEmail());
	 if(userStatus ==false) {
	 service.addUser(user);	
	 System.out.println("User Added Successfully");
 }else {
	 System.out.println("User Already Exists");
 }	
	 return "home";
	 
 }
 @PostMapping("/validate")
 public String validate(@RequestParam("email")String email,@RequestParam("password")String password,Model model,
     
	 HttpSession session) {
	 
	 if(service.validateUser(email,password)==true) {
		 String role=service.getRole(email);
		 
		 session.setAttribute("email", email);
		 
		 if(role.equals("admin")) {
			 return "adminHome";
		 }else {
			 Users user = service.getUser(email);
				boolean userStatus = user.isPremium();
				List<Song> songsList = songService.fetchAllSongs();
				model.addAttribute("songs", songsList);
				
				model.addAttribute("isPremium", userStatus);
			 return "customerHome";
		 }
	 }else {
		 String msg="Enter valid credientials";
		 model.addAttribute("msg", msg);
		 return "login";
	 }
	
 }
// @GetMapping("/pay")
// public String pay(@RequestParam String email) {
//	 boolean paymentStatus=false;// payment api used here
//
//	 if(paymentStatus==true) {
//		 Users user=service.getUser(email);
//	     user.setPremium(true);
//	     service.updateUser(user);
//	 }
//     return "login";
// }
@GetMapping("/logout")
 public String logout (HttpSession session){
	 session.invalidate();
     return "login";
 }
 
 
 
}
