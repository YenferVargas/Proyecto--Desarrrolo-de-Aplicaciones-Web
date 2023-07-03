package com.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.Producto;
import com.project.model.Usuario;
import com.project.model.Ventas;
import com.project.service.IProductoService;
import com.project.service.IUserService;
import com.project.service.IVentaService;


@Controller
@RequestMapping("/ventas")
public class VentasController {
	@Autowired
	private IVentaService ventaServicio;
	@Autowired
	private IProductoService productoServicio;
	@Autowired
	private IUserService clienteService;
	
	@GetMapping("/mostrar")
	public String Mostrar(Model model) {
		model.addAttribute("listaVentas", ventaServicio.ListAll());
		return "ventas/ver_ventas";
	}
	@GetMapping("/agregar")
	public String MostrarFormulario(Model model) {
		List<Producto> listaProductos = productoServicio.ListProducto();
		List<Usuario> listaUsuarios = clienteService.listaUser();
		
		model.addAttribute("listaUsuarios", listaUsuarios);
		Ventas venta = new Ventas();
		model.addAttribute("listaProductos", listaProductos);
		model.addAttribute("venta", venta);
		return "ventas/agregar_ventas";
	}
	
	@PostMapping("/save")
	public String Save(Ventas venta) {
		ventaServicio.Guardar(venta);
		return "redirect:/ventas/mostrar";
	}
}
