package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Producto;
import com.project.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepository productoRepo;
	
	@Override
	public List<Producto> ListProducto() {
		List<Producto> listaProductos = productoRepo.findAll();
		return listaProductos;
	}

	@Override
	public void SaveProduct(Producto p) {
		productoRepo.save(p);
	}

	@Override
	public void deleteProduct(Integer id) {
		productoRepo.deleteById(id);;
	}

}
