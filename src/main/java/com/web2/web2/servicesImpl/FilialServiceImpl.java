package com.web2.web2.servicesImpl;

import com.web2.web2.model.Filial;
import com.web2.web2.model.Funcionarios;
import com.web2.web2.repository.FilialRepository;
import com.web2.web2.repository.FuncionariosRepository;
import com.web2.web2.services.FilialService;
import com.web2.web2.services.FuncionariosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialServiceImpl implements FilialService
{
    @Autowired
    FilialRepository filialRepository;

    @Autowired
    FuncionariosRepository funcionariosRepository;

    @Autowired
    FuncionariosServices funcionariosServices;

    @Override
    public List<Filial> findAll() {
        return filialRepository.findAll();
    }

    @Override
    public Filial findById(Integer id) {
        return filialRepository.findById(id).get();
    }

    @Override
    public Filial save(Filial filial) {
        return filialRepository.save(filial);
    }

    @Override
    public Filial findByName(String name) {
        return filialRepository.findByNome(name);
    }


    @Override
    public String deleteByID(Integer id) {

        Optional<Filial> filial = filialRepository.findById(id);

        List<Funcionarios> funcionariosList = funcionariosServices.findFuncionariosByFilialId(filial.get().getId());

        if(funcionariosList.isEmpty()){
            filialRepository.deleteById(id);
            return "redirect:/filiais";
        }else{
            for (Funcionarios funcionario: funcionariosList) {
              funcionariosRepository.deleteById(funcionario.getId());
            }
        }
        funcionariosRepository.deleteById(id);
        return "redirect:/filiais";
    }

}
