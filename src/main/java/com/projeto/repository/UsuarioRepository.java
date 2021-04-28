package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.models.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	Usuario findById(long id);

}
