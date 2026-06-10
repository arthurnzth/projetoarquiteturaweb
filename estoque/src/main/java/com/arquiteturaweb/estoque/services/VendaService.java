package com.arquiteturaweb.estoque.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.repositories.ClienteRepository;
import com.arquiteturaweb.estoque.repositories.UsuarioRepository;
import com.arquiteturaweb.estoque.repositories.VendaRepository;

@Service
public class VendaService {
    
    @Autowired
    private VendaRepository repositorio;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    //@Autowired
    //private MovimentacaoRepository movimentacaoRepository;

}
