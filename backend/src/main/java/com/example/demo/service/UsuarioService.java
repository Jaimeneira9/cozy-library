package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.modelDTO.LoginRequestDTO;
import com.example.demo.modelDTO.LoginResponseDTO;
import com.example.demo.modelDTO.UsuarioRequestDTO;
import com.example.demo.repository.ReseniaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ReseniaRepository reseniaRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          ReseniaRepository reseniaRepository,
                          BCryptPasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.reseniaRepository = reseniaRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UsuarioRequestDTO mapToRequestDTO(Usuario usuario) {
        return UsuarioRequestDTO.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .fechaRegistro(usuario.getFechaRegistro())
                .url_imagen_perfil(usuario.getUrl_imagen_perfil())
                .valoraciones(usuario.getResenias().size())
                .build();
    }

    @Transactional
    public String crearUsuario(Usuario usuario) {
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        usuarioRepository.save(usuario);
        return "Usuario creado con exito";
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"));
        if (!passwordEncoder.matches(request.getContraseña(), usuario.getContraseña())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }
        String token = jwtUtil.generateToken(usuario.getId(), usuario.getEmail());
        return new LoginResponseDTO(token, usuario.getId(), usuario.getNombre());
    }
}
