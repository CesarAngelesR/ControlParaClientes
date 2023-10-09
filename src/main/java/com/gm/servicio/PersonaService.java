package com.gm.servicio;

import java.util.List;

import com.gm.domain.Persona;

public interface PersonaService {
	
	public List<Persona> listaPersona();
	public void guardar(Persona persona);
	public void eliminar(Persona persona);
	public Persona encontrarPersona(Persona persona);
	
}
