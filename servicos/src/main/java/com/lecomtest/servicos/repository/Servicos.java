package com.lecomtest.servicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lecomtest.servicos.model.Servico;

public interface Servicos extends JpaRepository<Servico, Long> {

}
