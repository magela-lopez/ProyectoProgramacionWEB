package com.web2.web2.repository;

import com.web2.web2.model.Filial;
import com.web2.web2.model.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FilialRepository extends JpaRepository<Filial,Integer> {

     Filial findByNome(String nome);

}
