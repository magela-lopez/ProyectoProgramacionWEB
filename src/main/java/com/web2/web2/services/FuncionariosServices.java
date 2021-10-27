package com.web2.web2.services;

import java.util.List;
import java.util.Optional;

import com.web2.web2.model.Funcionarios;
public interface FuncionariosServices {

	List<Funcionarios> findAll();
	List<Funcionarios> findFuncionariosByFilialId(Integer idFilial);
	Funcionarios findById(int id);
	Funcionarios save(Funcionarios funcionario);
}
