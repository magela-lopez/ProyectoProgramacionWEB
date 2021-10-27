package com.web2.web2.controller;

import com.web2.web2.model.Filial;
import com.web2.web2.model.Funcionarios;
import com.web2.web2.repository.FilialRepository;
import com.web2.web2.repository.FuncionariosRepository;
import com.web2.web2.services.FilialService;
import com.web2.web2.servicesImpl.FilialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class FilialController {

    @Autowired
    FuncionariosRepository funcionariosRepository;
    @Autowired
    FilialRepository filialRepository;

    @Autowired
    FilialService filialService;

    @RequestMapping(value="/filiais", method= RequestMethod.GET)
    public ModelAndView getFiliais() {
        ModelAndView mv = new ModelAndView("filiais");
        List<Filial> filiais = filialRepository.findAll();
        mv.addObject("filiais",filiais);
        return mv;
    }

    @RequestMapping(value="/filiais/{id}", method=RequestMethod.GET)
    public ModelAndView getFilial(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("filial");
        Optional<Filial> filiais = filialRepository.findById(id);
        mv.addObject("nome",filiais.get().getNome());
        mv.addObject("cidade",filiais.get().getCidade());
        mv.addObject("email", filiais.get().getEmail());
        mv.addObject("telefone", filiais.get().getTelefone());
        return mv;
    }

    @RequestMapping(value = "/includefilial", method = RequestMethod.GET)
    public String newFilial(Model model){
        model.addAttribute("filiais", new Filial());
        return "formularioFilial";
    }

    @RequestMapping(value = "/includefilial", method = RequestMethod.POST)
    public String saveFilial(@Valid @ModelAttribute("filial") Filial filial, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos obrigatorios");
            return "redirect:/includefilial";
        }
        filialRepository.save(filial);
        return "redirect:/filiais";
    }

    @RequestMapping(value = "/deleteFilial/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable Integer id) {
        filialService.deleteByID(id);
        return "redirect:/filiais";
    }

    @RequestMapping(value="/filial/editar/{id}", method=RequestMethod.GET)
    public ModelAndView updateFilial(@PathVariable("id") Integer id, Model model) {
        ModelAndView mv = new ModelAndView("formularioEditarFilial");
        Optional<Filial> filial = filialRepository.findById(id);
        mv.addObject("nome",filial.get().getNome());
        mv.addObject("email", filial.get().getEmail());
        mv.addObject("telefone", filial.get().getTelefone());
        mv.addObject("cidade",filial.get().getCidade());
        return mv;
    }

    @RequestMapping(value = "/filial/editar/{id}", method = RequestMethod.POST)
    public String saveUpdateFilial(Filial filial){

        Optional<Filial> filialUpdate = filialRepository.findById(filial.getId());
        System.out.println(filialUpdate.get().getId());
        filialUpdate.get().setEmail(filial.getEmail());
        filialUpdate.get().setNome(filial.getNome());
        filialUpdate.get().setTelefone(filial.getTelefone());
        filialUpdate.get().setCidade(filial.getCidade());

        filialRepository.save(filialUpdate.get());

        return "redirect:/filiais";
    }
}
