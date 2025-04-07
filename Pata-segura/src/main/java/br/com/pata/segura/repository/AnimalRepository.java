package br.com.pata.segura.repository;

import br.com.pata.segura.domain.animal.Animal;
import br.com.pata.segura.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Page<Animal> findByUsuario(Usuario usuario, Pageable pageable);
}
