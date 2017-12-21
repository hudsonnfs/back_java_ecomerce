package com.prog.web.domain.categoria;

import com.prog.web.domain.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public Categoria findByNome(String nome);

}
