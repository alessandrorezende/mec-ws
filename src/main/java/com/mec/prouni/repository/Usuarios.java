package com.mec.prouni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mec.prouni.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {

	Usuario findByUsernameAndPassword(String username, String password);

}
