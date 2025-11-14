package com.example.demo.service;

import com.example.demo.model.Libro;
import com.example.demo.model.Resenia;
import com.example.demo.model.Usuario;
import com.example.demo.modelDTO.ReseniaRequestDTO;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.ReseniaRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReseniaService {
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private ReseniaRepository reseniaRepository;
    private UsuarioRepository usuarioRepository;

    public ReseniaService(LibroRepository libroRepository
            ,AutorRepository autorRepository
            ,UsuarioRepository usuarioRepository
            ,ReseniaRepository reseniaRepository){
        this.libroRepository=libroRepository;
        this.autorRepository=autorRepository;
        this.usuarioRepository=usuarioRepository;
        this.reseniaRepository=reseniaRepository;
    }
    public Resenia mapFromDTOTOResenia(ReseniaRequestDTO reseniaRequestDTO){

        Optional<Libro> libroOptional = libroRepository.findByTituloAndAutorContainingIgnoreCase(reseniaRequestDTO.getTituloLibro(),reseniaRequestDTO.getNombreAutor());
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreContainingIgnoreCase(reseniaRequestDTO.getNombreUsuario());

        if(libroOptional.isPresent()&&usuarioOptional.isPresent()){

            Resenia resenia = new Resenia();
            resenia.setLibro(libroOptional.get());
            resenia.setUsuario(usuarioOptional.get());
            resenia.setComentario(reseniaRequestDTO.getComentario());
            resenia.setFecha(reseniaRequestDTO.getFecha());
            resenia.setValoracion(reseniaRequestDTO.getValoracion());

            return resenia;
        }else return null;
    }
    //Crear Reseña
    @Transactional
    public String postResenia(ReseniaRequestDTO reseniaRequestDTO){
        if(mapFromDTOTOResenia(reseniaRequestDTO)!=null){
            reseniaRepository.save(mapFromDTOTOResenia(reseniaRequestDTO));
            return "Reseña creada con exito";
        }else{
            return "Ha habido un fallo creando la reseña";
        }

    }
}
