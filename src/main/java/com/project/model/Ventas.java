package com.project.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@Entity
@Table(name = "ventas")
public class Ventas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@CreationTimestamp
	private LocalDate fecha;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "subtotal")
	private double subtotal;
	@Column(name = "igv")
	private double igv;
	@Column(name = "precio")
	private double precio;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	private Usuario cliente;
	
	@ManyToOne
    @JoinColumn(name = "codigo_producto")
    private Producto producto;
	
	public Ventas() {
		
	}

	public Ventas(Integer codigo, LocalDate fecha, int cantidad, double subtotal, double igv, double precio,
			Usuario cliente, Producto producto) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.igv = igv;
		this.precio = precio;
		this.cliente = cliente;
		this.producto = producto;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
