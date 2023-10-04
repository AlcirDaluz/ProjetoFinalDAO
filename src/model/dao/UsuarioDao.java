package model.dao;

import model.entities.Usuario;

public interface UsuarioDao {

    void menu();

    void insertUsuario(Usuario obj);

    void insertUsuarioAdm(Usuario obj);

    void validarSenha(Usuario obj);

    void loginUsuario(String login, String senha);

    void loginAdm(String login, String senha);
}
