package com.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Usuario;
import com.project.repository.IUsuarioRespository;
import com.project.service.IUserService;

@Controller
@RequestMapping("/bellezaPeruana")
public class LoginController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private IUsuarioRespository repositoryUser;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/login")
	public String Login(
			@RequestParam(required = false) String logout,
			@RequestParam(required = false) String error,
			@RequestParam(required = false) String authentication_failed,
			@RequestParam(required = false) String success, 
			Model model) {
		if (logout != null) {
			model.addAttribute("showNotificationWarning", true);
			model.addAttribute("notificationMessage", "Sesión cerrada correctamente");
		}
		if (error != null) {
			model.addAttribute("showNotificationDanger", true);
			model.addAttribute("notificationMessage", "Credenciales inválidas. Por favor, inténtalo nuevamente.");
		}
		if (authentication_failed != null) {
			model.addAttribute("showNotificationDanger", true);
			model.addAttribute("notificationMessage", "Fallo en la autenticación, intentalo de nuevo");
		}
		if (success != null) {
			model.addAttribute("showNotificationSuccess", true);
			model.addAttribute("notificationMessage", "Usuario creado correctamente");
		}
		return "login";
	}
	
	@GetMapping("/registro")
	public String registro(
			@RequestParam(required = false) String error_field,
			Model model) {

		if (error_field != null) {
			model.addAttribute("showNotificationDanger", true);
			model.addAttribute("notificationMessage", "El campo " + error_field + " esta vacio");
		}
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "registro";
	}
	
	@PostMapping("/registrar")
	public String RegistrarUsuario(Usuario u) {
		u.setPassword(encoder.encode(u.getPassword()));
		repositoryUser.save(u);
		return "redirect:/bellezaPeruana/login?exito";
	}
	
}
