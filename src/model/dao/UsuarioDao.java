package model.dao;

import model.entities.Usuario;

public interface UsuarioDao {

    void insert(Usuario obj);

    void loginUsuario(Usuario obj);

    void loginAdm(Usuario obj);
}
