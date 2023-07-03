package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.project.model.Usuario;
import com.project.repository.IUsuarioRespository;

@Service
public class UsuarioServiceImpl implements IUserService{

	@Autowired
	private IUsuarioRespository userRepo;
	
	@Override
	public List<Usuario> listaUser() {
		List<Usuario> lista = userRepo.findAll();
		return lista;
	}

	@Override
	public void SaveUser(Usuario u) {
		userRepo.save(u);
	}

	@Override
	public void deleteById(Integer id) {
		userRepo.deleteById(id);
	}

	@Override
    public Usuario findByCorreo(String correo) {
        return userRepo.findByCorreo(correo);
    }

    @Override
    public boolean autenticarUsuario(String correo, String password) {
        Usuario usuario = findByCorreo(correo);
        return usuario != null && usuario.getPassword().equals(password);
    }
	
}
