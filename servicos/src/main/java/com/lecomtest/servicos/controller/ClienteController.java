package com.lecomtest.servicos.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lecomtest.servicos.model.Cliente;
import com.lecomtest.servicos.model.TipoCliente;
import com.lecomtest.servicos.repository.Clientes;

@Controller
@RequestMapping("clientes")
public class ClienteController {

	private static final String CADASTRO_CLIENTE_VIEW = "CadastroCliente";

	@Autowired
	private Clientes clientes;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_CLIENTE_VIEW);
		mv.addObject(new Cliente());
		mv.addObject("todosTiposCliente", TipoCliente.values());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_CLIENTE_VIEW;
		}

		clientes.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso.");
		return "redirect:/clientes/novo";
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Cliente> todosClientes = clientes.findAll();
		ModelAndView mv = new ModelAndView("PesquisaClientes");
		mv.addObject("clientes", todosClientes);
		return mv;
	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Cliente cliente) {
		ModelAndView mv = new ModelAndView(CADASTRO_CLIENTE_VIEW);
		mv.addObject(cliente);
		return mv;
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		clientes.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Cliente removido com sucesso.");
		return "redirect:/clientes";
	}

	@ModelAttribute("todosTiposCliente")
	public List<TipoCliente> todosTiposCliente() {
		return Arrays.asList(TipoCliente.values());
	}

}
