package com.lecomtest.servicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lecomtest.servicos.model.Servico;
import com.lecomtest.servicos.repository.Servicos;

@Controller
@RequestMapping("servicos")
public class ServicoController {

	private static final String CADASTRO_SERVICO_VIEW = "CadastroServico";

	@Autowired
	private Servicos servicos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_SERVICO_VIEW);
		mv.addObject(new Servico());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Servico servico, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_SERVICO_VIEW;
		}

		servicos.save(servico);
		attributes.addFlashAttribute("mensagem", "Serviço cadastrado com sucesso.");
		return "redirect:/servicos/novo";
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Servico> todosServicos = servicos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaServicos");
		mv.addObject("servicos", todosServicos);
		return mv;
	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Servico servico) {
		ModelAndView mv = new ModelAndView(CADASTRO_SERVICO_VIEW);
		mv.addObject(servico);
		return mv;
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		servicos.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Serviço removido com sucesso.");
		return "redirect:/servicos";
	}

}
