package br.com.paulo.Models.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.paulo.Models.entity.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{

	
}
