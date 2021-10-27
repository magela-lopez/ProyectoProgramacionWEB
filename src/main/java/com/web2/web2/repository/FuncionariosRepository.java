package com.web2.web2.repository;

import com.web2.web2.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web2.web2.model.Funcionarios;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Integer> {

   List<Funcionarios> findFuncionariosByIdFilial(Filial idFilial);

}
