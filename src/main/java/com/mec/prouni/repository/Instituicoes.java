package com.mec.prouni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mec.prouni.model.Instituicao;

public interface Instituicoes extends JpaRepository<Instituicao, Long> {

	Instituicao findByNome(String nome);

	Instituicao findByNomeAndHashId(String nome, String hashId);
}
