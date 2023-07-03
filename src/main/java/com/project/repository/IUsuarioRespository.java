package com.project.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Usuario;

@Repository
public interface IUsuarioRespository extends JpaRepository<Usuario, Integer>{
	//public Usuario findFirstByCodigo(Integer id);
	public Usuario findByCorreo(String correo);
}
