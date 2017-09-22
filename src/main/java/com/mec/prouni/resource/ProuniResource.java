package com.mec.prouni.resource;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mec.prouni.model.Aluno;
import com.mec.prouni.model.Curso;
import com.mec.prouni.model.Instituicao;
import com.mec.prouni.model.Nota;
import com.mec.prouni.repository.Alunos;
import com.mec.prouni.repository.Cursos;
import com.mec.prouni.repository.Instituicoes;
import com.mec.prouni.repository.Notas;

@RestController
public class ProuniResource {

	private static final Logger log = Logger.getLogger(ProuniResource.class);

	@Autowired
	private Instituicoes instituicoes;

	@Autowired
	private Cursos cursos;

	@Autowired
	private Notas notas;

	@Autowired
	private Alunos alunos;

	public ProuniResource() {

	}

	/*
	 * Service: Receber vagas ociosas das instituições para ofertar no PROUNI
	 * Interface:/vagaOciosaService
	 */
	@RequestMapping(value = "/vagaOciosaService", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Curso[]> vagasOciosas(@RequestBody Curso[] cursosList) {
		try {
			log.info("Executando serviço: vagaOciosaService");

			if (cursosList != null) {
				Instituicao instituicao = instituicoes.findByNome(cursosList[0].getInstituicao().getNome());
				for (int i = 0; i < cursosList.length; i++) {
					cursosList[i].setInstituicao(instituicao);
					cursos.save(cursosList[i]);
				}
				return new ResponseEntity<Curso[]>(cursosList, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Ocorreu um erro ao executar o serviço: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/*
	 * Service: Solicitar aprovados no PROUNI
	 * Interface:/aprovadoService?{instituicao}&{hashId}
	 */
	@RequestMapping(value = "/aprovadoService", method = RequestMethod.GET)
	public ResponseEntity<List<Aluno>> aprovadosProuni(
			@RequestParam(value = "instituicao", required = true) String instituicaoNome,
			@RequestParam(value = "hashid", required = true) String hashId) {

		try {
			log.info("Executando serviço: aprovadoService");
			Instituicao instituicao = instituicoes.findByNomeAndHashId(instituicaoNome, hashId);

			if (instituicao != null) {
				List<Aluno> aprovadosList = alunos.findByQueryInstituicao(instituicaoNome);
				return new ResponseEntity<List<Aluno>>(aprovadosList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			log.error("Ocorreu um erro ao executar o serviço: " + e.getMessage());
			throw new ServiceException("Ocorreu um erro ao executar o serviço " + e.getMessage());
		}
	}

	/*
	 * Service: Solicitar notas do Conceito Preliminar de Curso (CPC)
	 * Interface:/notaService?{instituicao}&{hashId}
	 */
	@RequestMapping(value = "/notaService", method = RequestMethod.GET)
	public ResponseEntity<List<Nota>> notasCPC(
			@RequestParam(value = "instituicao", required = true) String instituicaoNome,
			@RequestParam(value = "hashid", required = true) String hashId) {

		try {
			log.info("Executando serviço: notaService");
			Instituicao instituicao = instituicoes.findByNomeAndHashId(instituicaoNome, hashId);

			if (instituicao != null) {
				List<Nota> notasList = notas.findByQueryInstituicao(instituicaoNome);
				return new ResponseEntity<List<Nota>>(notasList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			log.error("Ocorreu um erro ao executar o serviço: " + e.getMessage());
			throw new ServiceException("Ocorreu um erro ao executar o serviço " + e.getMessage());
		}
	}

	/*
	 * Service: Solicitar notas do Conceito Preliminar de Curso (CPC)
	 * Interface:/cursos/{instituicao}/{hashId}
	 */
	/*
	 * @RequestMapping(value = "/cursos/{instituicao}/{hashId}", method =
	 * RequestMethod.GET) public ResponseEntity<List<Nota>>
	 * notasCPC(@PathVariable("instituicao") String instituicaoNome,
	 * 
	 * @PathVariable("hashId") String hashId) { try {
	 * 
	 * Instituicao instituicao = instuicoes.findByNomeAndHashId(instituicaoNome,
	 * hashId);
	 * 
	 * if (instituicao != null) { List<Nota> notasList =
	 * notas.findByQueryInstituicao(instituicaoNome); return new
	 * ResponseEntity<List<Nota>>(notasList, HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * } catch (Exception e) { log.error("Ocorreu um erro ao executar o serviço: " +
	 * e.getMessage()); throw new
	 * ServiceException("Ocorreu um erro ao executar o serviço"); } }
	 */

	/*
	 * @RequestMapping(value = "/cursos/{id}", method = RequestMethod.GET) public
	 * ResponseEntity<Curso> buscar(@PathVariable("id") Integer id) { Curso curso =
	 * cursos.get(id);
	 * 
	 * if (curso == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * return new ResponseEntity<Curso>(curso, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/cursos/{id}", method = RequestMethod.DELETE) public
	 * ResponseEntity<?> deletar(@PathVariable("id") int id) { Curso curso =
	 * cursos.remove(id);
	 * 
	 * if (curso == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 */
}
