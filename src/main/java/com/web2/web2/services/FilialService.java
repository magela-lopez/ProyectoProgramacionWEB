package com.web2.web2.services;

import com.web2.web2.model.Filial;
import com.web2.web2.model.Funcionarios;

import java.util.List;
import java.util.Optional;

public interface FilialService {

    List<Filial> findAll();
    Filial findById(Integer id);
    Filial save(Filial filial);
    Filial findByName(String name);

   String  deleteByID(Integer id);
}
