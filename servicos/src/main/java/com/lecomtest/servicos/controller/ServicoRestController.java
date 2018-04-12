package com.lecomtest.servicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lecomtest.servicos.model.Servico;
import com.lecomtest.servicos.repository.Servicos;

@RestController
@RequestMapping("rest/servicos")
public class ServicoRestController {
	
	@Autowired
	private Servicos servicos;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Servico> getAllServicos() {
		return servicos.findAll();
	}

}
