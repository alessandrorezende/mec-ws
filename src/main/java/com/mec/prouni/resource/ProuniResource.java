package com.mec.prouni.resource;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mec.prouni.model.Curso;
import com.mec.prouni.model.Nota;
import com.mec.prouni.model.Usuario;
import com.mec.prouni.repository.Notas;
import com.mec.prouni.repository.Usuarios;

@RestController
public class ProuniResource {

	private Map<Integer, Curso> cursos;
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private Notas notas;

	private static Logger logger = Logger.getLogger(ProuniResource.class);

	public ProuniResource() {
	}

	/*
	 * Service: Solicitar notas do Conceito Preliminar de Curso (CPC)
	 * Interface:/cursos/{instituicao}/{hashId}
	 */
	@RequestMapping(value = "/cursos/{instituicao}/{hashId}", method = RequestMethod.GET)
	public ResponseEntity<Nota[]> notasCPC(@PathVariable("instituicao") String instituicao,
			@PathVariable("hashId") String hashId) {
		try {

			Usuario usuario = usuarios.findByUsernameAndHashId(instituicao, hashId);

			if (usuario != null) {
				Nota[] notasList = notas.findByQueryInstituicao(instituicao);
				return new ResponseEntity<Nota[]>(notasList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Ocorreu um erro ao executar o serviço: " + e.getMessage());
			throw new ServiceException("Ocorreu um erro ao executar o serviço");
		}
	}
	
	/*
	 * Service: Solicitar notas do Conceito Preliminar de Curso (CPC)
	 * Interface:/cursos?{instituicao}&{hashId}
	 */
	@RequestMapping(value = "/cursos", method = RequestMethod.GET)
	public ResponseEntity<Nota[]> notasCPC2(
			@RequestParam(value = "instituicao", required = true) String instituicao,
			@RequestParam(value = "hashid", required = true) String hashId) {
		
		try {

			Usuario usuario = usuarios.findByUsernameAndHashId(instituicao, hashId);

			if (usuario != null) {
				Nota[] notasList = notas.findByQueryInstituicao(instituicao);
				return new ResponseEntity<Nota[]>(notasList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("Ocorreu um erro ao executar o serviço: " + e.getMessage());
			throw new ServiceException("Ocorreu um erro ao executar o serviço");
		}
	}
	/*
	@RequestMapping(value = "/cursos/{id}", method = RequestMethod.GET)
	public ResponseEntity<Curso> buscar(@PathVariable("id") Integer id) {
		Curso curso = cursos.get(id);

		if (curso == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

	@RequestMapping(value = "/cursos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) {
		Curso curso = cursos.remove(id);

		if (curso == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
*/
}
