package controllers.user;

import java.util.List;

import model.user.Usuario;

public interface IUsuarioController {

	// Crud
	public int add(Usuario oUsuario);

	public int remove(Usuario oUsuario);

	public int update(Usuario oUsuario);
	
	public String mostrarUsuario();

	// Queries
	public int existeUsuario(Usuario oUsuario);
	
	public List<Usuario> searchUsuario(Usuario oUsuario);

	public List<Usuario> ususarioPorDireccion(Usuario oUsuario);

	public Usuario search(Usuario oUs);


}
