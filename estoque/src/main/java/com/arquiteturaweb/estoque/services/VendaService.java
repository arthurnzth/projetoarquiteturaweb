package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Venda;
import com.arquiteturaweb.estoque.entities.dto.venda.VendaResponseDTO;
import com.arquiteturaweb.estoque.repositories.ClienteRepository;
import com.arquiteturaweb.estoque.repositories.UsuarioRepository;
import com.arquiteturaweb.estoque.repositories.VendaRepository;

@Service
public class VendaService {
    
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    //@Autowired
    //private MovimentacaoRepository movimentacaoRepository;

    

}
