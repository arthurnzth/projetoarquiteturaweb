package com.arquiteturaweb.estoque.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.arquiteturaweb.estoque.entities.Usuario;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioResponseDTO;
import com.arquiteturaweb.estoque.repositories.UsuarioRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve listar todos os usuários")
    void findAllDeveRetornarListaDeUsuarios() {
        Usuario usuario1 = new Usuario(1L, "Usuário A", "usuarioa@email.com", "Administrador", true);
        Usuario usuario2 = new Usuario(2L, "Usuário B", "usuariob@email.com", "Vendedor", true);

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<UsuarioResponseDTO> resultado = usuarioService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar usuário por id quando existir")
    void findByIdDeveRetornarUsuarioQuandoExistir() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Usuário A", "usuarioa@email.com", "Administrador", true);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO resultado = usuarioService.findById(id);

        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar usuário inexistente")
    void findByIdDeveLancarExcecaoQuandoUsuarioNaoExistir() {
        Long id = 99L;

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.findById(id));
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve inserir usuário com sucesso")
    void insertDeveInserirUsuarioComSucesso() {
        UsuarioRequestDTO request = mock(UsuarioRequestDTO.class);
        when(request.getNome()).thenReturn("Usuário A");
        when(request.getEmail()).thenReturn("usuarioa@email.com");
        when(request.getCargo()).thenReturn("Administrador");
        when(request.getAtivo()).thenReturn((byte) 1);

        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuarioSalvo = invocation.getArgument(0);
            usuarioSalvo.setId(1L);
            return usuarioSalvo;
        });

        UsuarioResponseDTO resultado = usuarioService.insert(request);

        assertNotNull(resultado);

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository, times(1)).save(captor.capture());

        Usuario usuarioEnviadoParaSalvar = captor.getValue();
        assertEquals("Usuário A", usuarioEnviadoParaSalvar.getNome());
        assertEquals("usuarioa@email.com", usuarioEnviadoParaSalvar.getEmail());
        assertEquals(true, usuarioEnviadoParaSalvar.isAtivo());
    }

    @Test
    @DisplayName("Deve converter ativo para false quando o DTO enviar valor diferente de 1")
    void insertDeveSalvarUsuarioInativoQuandoAtivoForDiferenteDeUm() {
        UsuarioRequestDTO request = mock(UsuarioRequestDTO.class);
        when(request.getNome()).thenReturn("Usuário Inativo");
        when(request.getEmail()).thenReturn("inativo@email.com");
        when(request.getCargo()).thenReturn("Vendedor");
        when(request.getAtivo()).thenReturn((byte) 0);

        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        usuarioService.insert(request);

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository, times(1)).save(captor.capture());
        assertEquals(false, captor.getValue().isAtivo());
    }

    @Test
    @DisplayName("Deve atualizar usuário com sucesso")
    void updateDeveAtualizarUsuarioComSucesso() {
        Long id = 1L;
        Usuario usuarioExistente = new Usuario(id, "Nome antigo", "antigo@email.com", "Cargo antigo", true);

        UsuarioRequestDTO request = mock(UsuarioRequestDTO.class);
        when(request.getNome()).thenReturn("Nome novo");
        when(request.getEmail()).thenReturn("novo@email.com");
        when(request.getCargo()).thenReturn("Cargo novo");
        when(request.getAtivo()).thenReturn((byte) 0);

        when(usuarioRepository.getReferenceById(id)).thenReturn(usuarioExistente);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UsuarioResponseDTO resultado = usuarioService.update(id, request);

        assertNotNull(resultado);
        assertEquals("Nome novo", usuarioExistente.getNome());
        assertEquals("novo@email.com", usuarioExistente.getEmail());
        assertEquals("Cargo novo", usuarioExistente.getCargo());
        assertEquals(false, usuarioExistente.isAtivo());
        verify(usuarioRepository, times(1)).getReferenceById(id);
        verify(usuarioRepository, times(1)).save(usuarioExistente);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar usuário inexistente")
    void updateDeveLancarExcecaoQuandoUsuarioNaoExistir() {
        Long id = 99L;
        UsuarioRequestDTO request = mock(UsuarioRequestDTO.class);

        when(usuarioRepository.getReferenceById(id)).thenThrow(new EntityNotFoundException());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.update(id, request));
        verify(usuarioRepository, times(1)).getReferenceById(id);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve deletar usuário com sucesso")
    void deleteDeveRemoverUsuarioComSucesso() {
        Long id = 1L;

        doNothing().when(usuarioRepository).deleteById(id);

        assertDoesNotThrow(() -> usuarioService.delete(id));
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao deletar usuário inexistente")
    void deleteDeveLancarExcecaoQuandoUsuarioNaoExistir() {
        Long id = 99L;

        doThrow(new EmptyResultDataAccessException(1)).when(usuarioRepository).deleteById(id);

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.delete(id));
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar DatabaseException quando houver erro de integridade ao deletar")
    void deleteDeveLancarDatabaseExceptionQuandoUsuarioEstiverRelacionado() {
        Long id = 1L;

        doThrow(new DataIntegrityViolationException("Usuário relacionado a outra entidade"))
                .when(usuarioRepository).deleteById(id);

        assertThrows(DatabaseException.class, () -> usuarioService.delete(id));
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
