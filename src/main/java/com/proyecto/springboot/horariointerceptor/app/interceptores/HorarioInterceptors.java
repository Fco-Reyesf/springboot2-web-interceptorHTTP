package com.proyecto.springboot.horariointerceptor.app.interceptores;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HorarioInterceptors implements HandlerInterceptor {

	@Value("${config.horario.abrir}")
	private Integer abrir;
	@Value("${config.horario.cerrar}")
	private Integer cerrar;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		
		if (hora >= abrir && hora < cerrar) {
			StringBuilder mensaje = new StringBuilder("Horario de atención desde las ");
			mensaje.append(abrir);
			mensaje.append(" hasta las ");
			mensaje.append(cerrar);
			mensaje.append(" hrs.");
			request.setAttribute("mensaje", mensaje.toString());
			return true;
		}
		
		response.sendRedirect(request.getContextPath().concat("/cerrdo"));
		
		return false;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String mensaje =  (String) request.getAttribute("mensaje");
		modelAndView.addObject("MensajeHorario", mensaje);
	}

	
	
}
