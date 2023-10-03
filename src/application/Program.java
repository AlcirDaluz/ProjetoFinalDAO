package application;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;

public class Program {

    public static void main(String[] args) {

        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

        usuarioDao.menu();
    }
}
