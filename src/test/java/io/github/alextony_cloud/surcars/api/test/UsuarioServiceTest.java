package io.github.alextony_cloud.surcars.api.test;
import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.UsuarioDTO;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;
import io.github.alextony_cloud.surcars.api.service.UsuarioService;
import io.github.alextony_cloud.surcars.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_ShouldThrowException_WhenIdDoesNotExist() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            usuarioService.findById(1L);
        });
    }

    @Test
    void create_ShouldSaveUser() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setPassword("password");
        when(encoder.encode(anyString())).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

        Usuario result = usuarioService.create(usuarioDTO);
        assertNotNull(result);
    }

    @Test
    void update_ShouldUpdateUser() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setPassword("password");
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(encoder.encode(anyString())).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.update(1L, usuarioDTO);
        assertNotNull(result);
    }

    @Test
    void delete_ShouldDeleteUser_WhenIdExists() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);

        usuarioService.delete(1L);
        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    void delete_ShouldThrowException_WhenIdDoesNotExist() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            usuarioService.delete(1L);
        });
    }
}
