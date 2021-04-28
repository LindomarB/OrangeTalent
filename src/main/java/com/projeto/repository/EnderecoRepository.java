package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.models.Endereco;
import com.projeto.models.Usuario;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{


	Iterable<Endereco> findByUsuario(Usuario usuario);

}
