package com.gm.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gm.domain.Persona;
import com.gm.servicio.PersonaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorInicio {
	
	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/")
	public String inicio(Model model, @AuthenticationPrincipal User user) {
		List<Persona> personas = (List<Persona>) personaService.listaPersona();
		log.info("Ejecutando el controlador Spring MVC");
		log.info("Usuario que hiz login: " + user );
		model.addAttribute("personas", personas);
		var saldoTotal=0D;
		for(var p:personas) {
			saldoTotal+=p.getSaldo();
		}
		model.addAttribute("saldoTotal", saldoTotal);
		model.addAttribute("totalClientes", personas.size());
		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregar(Persona persona) {
		return "modificar";
		
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid Persona persona, Errors errores) {
		if(errores.hasErrors()){
			return "modificar";
		}
		personaService.guardar(persona);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{idPersona}")
	public String editar(Persona persona, Model model) {
		persona = personaService.encontrarPersona(persona);
		model.addAttribute("persona",persona);
		return "modificar";
	}
	@GetMapping("/eliminar")
		public String eliminar(Persona persona) {
		personaService.eliminar(persona);
		return "redirect:/";	
	}
}
