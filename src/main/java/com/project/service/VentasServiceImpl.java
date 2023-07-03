package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Ventas;
import com.project.repository.IVentasRepository;
@Service
public class VentasServiceImpl implements IVentaService{

	@Autowired
	private IVentasRepository ventaRepository;
	
	@Override
	public List<Ventas> ListAll() {
		List<Ventas> ventas = ventaRepository.findAll();
		return ventas;
	}

	@Override
	public void Guardar(Ventas venta) {
		ventaRepository.save(venta);
	}

}
