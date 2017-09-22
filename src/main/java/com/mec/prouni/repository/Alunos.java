package com.mec.prouni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mec.prouni.model.Aluno;
import com.mec.prouni.model.Curso;

public interface Alunos extends JpaRepository<Aluno, Long> {

	Aluno findByCurso(Curso curso);

	@Query(" FROM Aluno a WHERE  a.curso.instituicao.nome =  :instituicao")
	public List<Aluno> findByQueryInstituicao(@Param("instituicao") String instituicao);
}
