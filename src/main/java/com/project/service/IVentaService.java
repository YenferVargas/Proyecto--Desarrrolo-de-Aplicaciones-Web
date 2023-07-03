package com.project.service;

import java.util.List;

import com.project.model.Ventas;

public interface IVentaService {
	public List<Ventas> ListAll();
	public void Guardar(Ventas venta);
}
