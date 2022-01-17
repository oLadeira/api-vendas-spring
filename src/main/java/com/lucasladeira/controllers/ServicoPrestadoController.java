package com.lucasladeira.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucasladeira.dto.ServicoPrestadoDTO;
import com.lucasladeira.services.ServicoPrestadoService;

@RestController
@RequestMapping("api/servicos-prestados")
public class ServicoPrestadoController {

	@Autowired
	private ServicoPrestadoService servicoPrestadoService;
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void salvar(@RequestBody ServicoPrestadoDTO dto) {
		servicoPrestadoService.salvar(servicoPrestadoService.fromDTO(dto));
	}
	
}