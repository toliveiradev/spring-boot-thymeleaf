package com.lecomtest.servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lecomtest.servicos.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

}
