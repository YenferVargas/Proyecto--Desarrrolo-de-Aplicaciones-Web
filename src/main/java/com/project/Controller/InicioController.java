package com.project.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.model.Usuario;
import com.project.service.IUserService;

@Controller
public class InicioController {
	@Autowired
	private IUserService serviceUser;
	
	@GetMapping("/")
	public String Inicio(Authentication authentication, Model model) {

		return "inicio";
	}
	

}
