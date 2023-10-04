package model.dao;

import model.entities.Produto;

public interface ProdutoDao {

    void insert(Produto obj);

    void SelectByNome(String nome);

    void deleteByNome(Produto obj);

    void update(Produto obj);
}
