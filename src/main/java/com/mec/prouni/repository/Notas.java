package com.mec.prouni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mec.prouni.model.Curso;
import com.mec.prouni.model.Nota;

public interface Notas extends JpaRepository<Nota,Long> {
	
	Nota findByCurso(Curso curso);
	@Query(" FROM Nota n WHERE  n.curso.instituicao.nome =  :instituicao")
	public Nota[] findByQueryInstituicao(@Param("instituicao") String instituicao);
}
