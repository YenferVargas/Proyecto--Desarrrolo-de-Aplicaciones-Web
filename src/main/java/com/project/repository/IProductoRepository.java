package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{
	public Producto findFirstByCodigo(String codigo);
}
