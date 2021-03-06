package com.squad5.fifo.repository;

import com.squad5.fifo.model.CargoUsuario;
import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.model.Usuario;
import com.squad5.fifo.model.Vez;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    
    List<Usuario> findByVezNullAndAtivo(Boolean ativo);

    List<Usuario> findByVez(Vez vez);

    Long countByVezDispositivoAndVezEntradaNotNullAndVezSaidaNull(Dispositivo dispositivo);

    List<Usuario> findByVezDispositivoAndVezEntradaNotNullAndVezSaidaNullOrderByVezEntradaAsc(Dispositivo dispositivod);

    Optional<Object> findByCargoUsuario(CargoUsuario cargoUsuario);

}
