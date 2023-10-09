package com.gm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gm.domain.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	Usuario findByUsername(String username);
}
