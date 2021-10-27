package com.web2.web2.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.web2.web2.model.Filial;
import com.web2.web2.services.FuncionariosServices;
import com.web2.web2.servicesImpl.FuncionariosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.web2.web2.repository.FuncionariosRepository;
import com.web2.web2.repository.FilialRepository;
import com.web2.web2.model.Funcionarios;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FuncionariosController {

	@Autowired
	FuncionariosRepository funcionariosRepository;
	@Autowired
	FilialRepository filialRepository;

	@Autowired
	FuncionariosServices funcionariosServices;

	@RequestMapping(value="/funcionarios", method=RequestMethod.GET)
	public ModelAndView getFuncionarios() {
		ModelAndView mv = new ModelAndView("funcionarios");
		List<Funcionarios> funcionarios = funcionariosRepository.findAll();
		mv.addObject("funcionarios",funcionarios);
		return mv;
	}

	@RequestMapping(value="/funcionarios/{id}", method=RequestMethod.GET)
	public ModelAndView getFuncionario(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("funcionario");
		Optional<Funcionarios> funcionarios = funcionariosRepository.findById(id);
		mv.addObject("nome",funcionarios.get().getNome());
		mv.addObject("email", funcionarios.get().getEmail());
		mv.addObject("telefone", funcionarios.get().getTelefone());
		mv.addObject("setor",funcionarios.get().getSetor());
		mv.addObject("filial",funcionarios.get().getIdFilial().getNome());
		return mv;
	}

	@GetMapping(value="/funcionarios/filial/{idFilial}")
	@ResponseBody
	public ModelAndView getFuncionariosByFilial(@PathVariable Integer idFilial) {
		Optional<Filial> filial = filialRepository.findById(idFilial);
		ModelAndView mv = new ModelAndView("funcionariosFilial");
		List<Funcionarios> funcionarios = funcionariosServices.findFuncionariosByFilialId(filial.get().getId());
		mv.addObject("funcionariosFilial",funcionarios);
		return mv;
	}

	@RequestMapping(value = "/includefuncionario", method = RequestMethod.GET)
	public String newFuncionario(Model model){
		List<Filial> filiais = filialRepository.findAll();
		model.addAttribute("funcionarios", new Funcionarios());
		model.addAttribute("filiaisList",filiais);
		return "formularioFuncionarios";
	}

	@RequestMapping(value = "/includefuncionario", method = RequestMethod.POST)
	public String saveFuncionario(@Valid @ModelAttribute("funcionarios") Funcionarios funcionarios, BindingResult result, RedirectAttributes attributes){

		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos obrigatorios");
			return "redirect:/includefuncionario";
		}
		funcionariosServices.save(funcionarios);
		return "redirect:/funcionarios";
	}

	@RequestMapping(value = "/deleteFuncionario/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable Integer id, RedirectAttributes attributes) {
		funcionariosRepository.deleteById(id);
		attributes.addFlashAttribute("mensagem", "Funcionario deletado");
		return "redirect:/funcionarios";
	}

	@RequestMapping(value="/funcionarios/editar/funcionario/{id}", method=RequestMethod.GET)
	public ModelAndView updateFuncionario(@PathVariable("id") Integer id, Model model) {
		ModelAndView mv = new ModelAndView("formularioEditarFuncionario");
		Optional<Funcionarios> funcionarios = funcionariosRepository.findById(id);
		mv.addObject("nome",funcionarios.get().getNome());
		mv.addObject("email", funcionarios.get().getEmail());
		mv.addObject("telefone", funcionarios.get().getTelefone());
		mv.addObject("setor",funcionarios.get().getSetor());
		mv.addObject("filial",funcionarios.get().getIdFilial().getNome());
		List<Filial> filiais = filialRepository.findAll();
		model.addAttribute("funcionariosUpdate", funcionarios.get());
		model.addAttribute("filiaisListUpdate",filiais);

		return mv;
	}

	@RequestMapping(value = "/funcionarios/editar/funcionario/{id}", method = RequestMethod.POST)
	public String saveUpdateFuncionario(@Valid @ModelAttribute("funcionariosUpdate") Funcionarios funcionarios){

		Optional<Funcionarios> funcionariosUpdate = funcionariosRepository.findById(funcionarios.getId());
		System.out.println(funcionariosUpdate.get().getId());
		funcionariosUpdate.get().setEmail(funcionarios.getEmail());
		funcionariosUpdate.get().setNome(funcionarios.getNome());
		funcionariosUpdate.get().setTelefone(funcionarios.getTelefone());
		funcionariosUpdate.get().setSetor(funcionarios.getSetor());
		funcionariosUpdate.get().setIdFilial(funcionarios.getIdFilial());

		funcionariosServices.save(funcionariosUpdate.get());

		return "redirect:/funcionarios";
	}
}
