package com.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.Producto;
import com.project.model.Usuario;
import com.project.repository.IUsuarioRespository;
import com.project.service.IUserService;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUserService clienteService;
	
	@Autowired
	private IUsuarioRespository userRepo;
	
	@GetMapping("/mostrar")
	public String clientes(Model model) {
		List<Usuario> listaUsuarios = clienteService.listaUser();
		
		model.addAttribute("listaUsuarios", listaUsuarios);
		
		return "usuarios/ver_usuarios";
	}
	
	@GetMapping("/agregar")
	public String formulario(Model model){
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "usuarios/agregar_usuario";
	}
	
	@PostMapping("/save")
	public String Agregar(Usuario cliente) {
		clienteService.SaveUser(cliente);
		return "redirect:/usuarios/mostrar";
	}
	
	@PostMapping("/eliminar")
    public String eliminarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        clienteService.deleteById(usuario.getId());
        return "redirect:/usuarios/mostrar";
    }
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("usuario", userRepo.findById(id).orElse(null));
        return "usuarios/editar_usuario";
    }
	
	@PostMapping("/editar/{id}")
    public String actualizarUsuario(@ModelAttribute @Validated Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (usuario.getId() != null) {
                return "usuarios/editar_usuario";
            }
            return "redirect:/usuarios/mostrar";
        }
        
        userRepo.save(usuario);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/usuarios/mostrar";
    }
}
