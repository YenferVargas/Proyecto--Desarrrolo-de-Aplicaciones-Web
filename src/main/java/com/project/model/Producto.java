package com.project.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String codigo;
    @Column
    private Float precio;
    @Column
    private Float existencia;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Ventas> ventas;

    public Producto() {
    }

	public Producto(Integer id, String nombre, String codigo, Float precio, Float existencia, List<Ventas> ventas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.existencia = existencia;
		this.ventas = ventas;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Float getExistencia() {
		return existencia;
	}

	public void setExistencia(Float existencia) {
		this.existencia = existencia;
	}

	public List<Ventas> getVentas() {
		return ventas;
	}

	public void setVentas(List<Ventas> ventas) {
		this.ventas = ventas;
	}

	// Getters y Setters
    
}
