package com.gm.servicio;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gm.dao.IUsuarioDao;
import com.gm.domain.Rol;
import com.gm.domain.Usuario;

import lombok.extern.slf4j.Slf4j;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario==null) {
			throw new UsernameNotFoundException(username);
		}
		
		var roles = new ArrayList<GrantedAuthority>();
		for(Rol rol:usuario.getRoles()){
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		return new User(usuario.getUsername(), usuario.getPassword(), roles);
	}
	
}
