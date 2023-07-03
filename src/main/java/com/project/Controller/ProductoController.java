package com.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import com.project.repository.IProductoRepository;
import com.project.service.IProductoService;
import com.project.service.IUserService;

@Controller
@RequestMapping(path = "/productos")
public class ProductoController {
	@Autowired
	private IUserService uservice;
	@Autowired
	private IProductoRepository IProductoRepository;
	@Autowired
	private IProductoService productoService;

	@GetMapping("/mostrar")
	public String ListaProductos(Authentication authentication, Model model) {
		List<Producto> listaProductos =productoService.ListProducto(); 
		model.addAttribute("productos", listaProductos);
		
		return "productos/ver_productos";
	}
	
	@GetMapping("/agregar")
    public String agregarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/agregar_producto";
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        IProductoRepository.deleteById(producto.getId());
        return "redirect:/productos/mostrar";
    }
    
    @PostMapping("/agregarProducto")
    public String guardarProducto(@ModelAttribute @Validated Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "productos/agregar_producto";
        }
        if (IProductoRepository.findFirstByCodigo(producto.getCodigo()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        IProductoRepository.save(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/agregar";
    }
    
    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("producto", IProductoRepository.findById(id).orElse(null));
        return "productos/editar_producto";
    }
    
    @PostMapping(value = "/editar/{id}")
    public String actualizarProducto(@ModelAttribute @Validated Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (producto.getId() != null) {
                return "productos/editar_producto";
            }
            return "redirect:/productos/mostrar";
        }
        Producto posibleProductoExistente = IProductoRepository.findFirstByCodigo(producto.getCodigo());

        if (posibleProductoExistente != null && !posibleProductoExistente.getId().equals(producto.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        IProductoRepository.save(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/mostrar";
    }
}
