package com.web2.web2.servicesImpl;

import java.util.List;
import java.util.Optional;

import com.web2.web2.model.Filial;
import com.web2.web2.repository.FilialRepository;
import com.web2.web2.services.FuncionariosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.web2.model.Funcionarios;
import com.web2.web2.repository.FuncionariosRepository;
import com.web2.web2.services.FuncionariosServices;

@Service
public class FuncionariosServicesImpl implements FuncionariosServices {

	@Autowired
	FuncionariosRepository funcionariosRepository;
	@Autowired
	FilialRepository filialRepository;
	
	@Override
	public List<Funcionarios> findAll() {
		// TODO Auto-generated method stub
		return funcionariosRepository.findAll();
	}

	@Override
	public List<Funcionarios> findFuncionariosByFilialId(Integer idFilial) {
		Optional<Filial> filial = filialRepository.findById(idFilial);
		List<Funcionarios> funcionariosByFilial = funcionariosRepository.findFuncionariosByIdFilial(filial.get());
		return funcionariosByFilial;
	}


	@Override
	public Funcionarios findById(int id) {
		// TODO Auto-generated method stub
		return funcionariosRepository.findById(id).get();
	}

	@Override
	public Funcionarios save(Funcionarios funcionario) {
		Filial filial = filialRepository.findByNome(funcionario.getIdFilial().getNome());
		funcionario.setIdFilial(filial);
		return funcionariosRepository.save(funcionario);
	}

}
