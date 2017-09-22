package com.mec.prouni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mec.prouni.model.Curso;
import com.mec.prouni.model.Instituicao;

public interface Cursos extends JpaRepository<Curso, Long> {

	List<Curso> findByInstituicao(Instituicao instituicao);

	Curso findByNome(String nome);

}
