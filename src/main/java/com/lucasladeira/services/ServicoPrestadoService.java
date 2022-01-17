package com.lucasladeira.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lucasladeira.dto.ServicoPrestadoDTO;
import com.lucasladeira.entities.Cliente;
import com.lucasladeira.entities.ServicoPrestado;
import com.lucasladeira.repositories.ClienteRepository;
import com.lucasladeira.repositories.ServicoPrestadoRepository;

@Service
public class ServicoPrestadoService {

	@Autowired
	private ServicoPrestadoRepository servicoPrestadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void salvar(ServicoPrestado servicoPrestado) {
		clienteRepository.findById(servicoPrestado.getCliente().getId())
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
		servicoPrestadoRepository.save(servicoPrestado);
	}
	
	public ServicoPrestado fromDTO(ServicoPrestadoDTO dto) {		
		LocalDate date = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));	
		//Optional<Cliente>cliente = clienteRepository.findById(dto.getIdCliente());
		
		Optional<Cliente> cliente = clienteRepository.findById(dto.getIdCliente());	
		cliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente Inexistente!"));
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setValor(Double.parseDouble(dto.getValor()));
		servicoPrestado.setData(date);
		servicoPrestado.setCliente(cliente.get());
		return servicoPrestado;
	}
}