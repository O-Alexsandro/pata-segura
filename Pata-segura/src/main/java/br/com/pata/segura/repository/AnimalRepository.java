package br.com.pata.segura.repository;

import br.com.pata.segura.domain.animal.Animal;
import br.com.pata.segura.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {
    // List<Animal> findByUsuario(Usuario usuario);


    Page<Animal> findByUsuario(Usuario usuario, Pageable pageable);


    Page<Animal> findAll(Specification<Animal> specs, Pageable pageable);
}
