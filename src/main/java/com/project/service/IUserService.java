package com.project.service;

import java.util.List;

import com.project.model.Usuario;

public interface IUserService{
	public List<Usuario> listaUser();
	public void SaveUser(Usuario u);
	public void deleteById(Integer id);
	
	Usuario findByCorreo(String correo);
    boolean autenticarUsuario(String correo, String contraseña);
	
}
