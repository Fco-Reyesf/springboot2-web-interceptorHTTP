package com.proyecto.springboot.horariointerceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class AppController {
	
	@Value("${config.horario.abrir}")
	private Integer abrir;
	@Value("${config.horario.cerrar}")
	private Integer cerrar;

	@GetMapping({"/","/index"})
	public String index (Model model) {
		model.addAttribute("titulo","Bienvenido, horario de atención: Apertura");
		return "index";
	} 
	
	@GetMapping("/cerrado")
	public String cerrado(Model model) {
		model.addAttribute("titulo", "Se encuentra cerrado");
		StringBuilder mensaje = new StringBuilder("se encuentra cerrado, atención desde las ");
		mensaje.append(abrir);
		mensaje.append(" hasta las ");
		mensaje.append(cerrar);
		mensaje.append(" hrs.");
		model.addAttribute("mensajeAtencion", mensaje);
		return "cerrado";
	}
	
}
