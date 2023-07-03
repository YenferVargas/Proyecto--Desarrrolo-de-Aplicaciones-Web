package com.project.service;

import java.util.List;

import com.project.model.Producto;

public interface IProductoService {
	public List<Producto> ListProducto();
	public void SaveProduct(Producto p);
	public void deleteProduct(Integer id);
}
