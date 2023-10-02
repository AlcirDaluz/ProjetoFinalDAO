package application;

import model.dao.UsuarioDao;
import model.dao.DaoFactory;

public class Program {

    public static void main(String[] args) {
        
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

        usuarioDao.menu();
    }
}
