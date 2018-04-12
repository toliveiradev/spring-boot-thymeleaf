package com.lecomtest.servicos.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
import com.lecomtest.servicos.model.Servico;
import com.lecomtest.servicos.model.ServicoCliente;
import com.lecomtest.servicos.repository.Clientes;
import com.lecomtest.servicos.repository.Servicos;
import com.lecomtest.servicos.repository.ServicosCliente;

@Controller
@RequestMapping("servicoscliente")
public class ServicoClienteController {

	private static final String CADASTRO_SERVICO_CLIENTE_VIEW = "CadastroServicoCliente";

	@Autowired
	private ServicosCliente servicosCliente;

	@Autowired
	private Clientes clientes;

	@Autowired
	private Servicos servicos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_SERVICO_CLIENTE_VIEW);
		mv.addObject(new ServicoCliente());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated ServicoCliente servicoCliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_SERVICO_CLIENTE_VIEW;
		}
		
		// Valores para calculo de percentual e valor de percentual
		Float descontoPercent = 0f;
		Float descontoValorFloat = 0f;
		Float valorTotalFloat = 0f;
		
		// Converter Date para LocalDate
		LocalDate dataFinalServico = servicoCliente.getDataFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		// Obter data atual em LocalDate
		LocalDate hoje = LocalDate.now();
		
		// Obter diferença em dias para data final do serviço comparado a data atual
		long diasRestantesFimServico = ChronoUnit.DAYS.between(hoje, dataFinalServico);
		
		// Obter a diferença entre a data final do serviço e o prazo de pagamento em dias para calculo de desconto
		long diferencaEntrePagamentoAndFimServico = diasRestantesFimServico - servicoCliente.getPrazoPagamento();
		
		if (servicoCliente.getCliente().getTipo().getDescricao().equals("OURO")) {
			descontoPercent += 10;
		} else {
			descontoPercent += 5;
		}
		
		if (diferencaEntrePagamentoAndFimServico >= 10) {
			descontoPercent += 5;
		}
		
		// Obter valor do desconto para calculo do valor total final (VALOR TOTAL DO SERVICO * DESCONTO EM PORCENTAGEM / 100)
		descontoValorFloat = servicoCliente.getServico().getValor().floatValue() * descontoPercent / 100;
		
		// Calculo de valor final total com desconto
		valorTotalFloat = servicoCliente.getServico().getValor().floatValue() - descontoValorFloat;
		
		// Passando para BigDecimal os valores de deconto e valor total
		BigDecimal desconto = new BigDecimal(Float.toString(descontoPercent));
		BigDecimal valorTotal = new BigDecimal(Float.toString(valorTotalFloat));
		
		// Setando valores de desconto e valor total
		servicoCliente.setDesconto(desconto);
		servicoCliente.setValorTotal(valorTotal);

		servicosCliente.save(servicoCliente);
		attributes.addFlashAttribute("mensagem", "Serviço de cliente cadastrado com sucesso.");
		return "redirect:/servicoscliente/novo";

	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<ServicoCliente> todosServicosClientes = servicosCliente.findAll();
		ModelAndView mv = new ModelAndView("PesquisaServicoCliente");
		mv.addObject("servicosCliente", todosServicosClientes);
		return mv;
	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") ServicoCliente servicoCliente) {
		ModelAndView mv = new ModelAndView(CADASTRO_SERVICO_CLIENTE_VIEW);
		mv.addObject(servicoCliente);
		return mv;
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		servicosCliente.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Serviço de cliente removido com sucesso.");
		return "redirect:/servicoscliente";
	}

	@ModelAttribute("todosClientes")
	public List<Cliente> todosClientes() {
		return clientes.findAll();
	}

	@ModelAttribute("todosServicos")
	public List<Servico> todosServicos() {
		return servicos.findAll();
	}
}
