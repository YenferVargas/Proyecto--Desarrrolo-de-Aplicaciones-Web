package com.project.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	@Column(name = "correo")
	private String correo;
	private String password;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Ventas> venta;
	
	public Usuario() {
		
	}

	public Usuario(Integer id, String nombre, String apellido, String correo, String password, List<Ventas> venta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.password = password;
		this.venta = venta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Ventas> getVenta() {
		return venta;
	}

	public void setVenta(List<Ventas> venta) {
		this.venta = venta;
	}

}
