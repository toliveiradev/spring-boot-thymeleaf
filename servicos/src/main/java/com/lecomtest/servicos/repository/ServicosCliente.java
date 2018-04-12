package com.lecomtest.servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lecomtest.servicos.model.ServicoCliente;

public interface ServicosCliente extends JpaRepository<ServicoCliente, Long> {

}
