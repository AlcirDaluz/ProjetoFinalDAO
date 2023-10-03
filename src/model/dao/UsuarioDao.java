package model.dao;

import model.entities.Usuario;

public interface UsuarioDao {

    void menu();

    void insertUsuario(Usuario obj);

    void insertUsuarioAdm(Usuario obj);

    void validarSenha(Usuario obj);

    void loginUsuario(Usuario obj);

    void loginAdm(Usuario obj);
}
