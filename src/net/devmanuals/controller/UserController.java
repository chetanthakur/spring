package net.devmanuals.controller;

import java.util.HashMap;
import java.util.Map;

import net.devmanuals.model.User;
import net.devmanuals.model.User.PasswordType;
import net.devmanuals.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUser(@ModelAttribute("user") User user ,
			BindingResult result) {
		return new ModelAndView("addUser");
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("user") User  user,
			BindingResult result) {
		user.setPasswordType(PasswordType.ENCRYPTED);
		user.setPassword(user.getPasswordHash());
		userService.addUser(user);
		return new ModelAndView("redirect:/articles.html");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listUser() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("users",  userService.listUsers());
		return new ModelAndView("userList", model);
	}

}