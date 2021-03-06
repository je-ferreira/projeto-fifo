package com.squad5.fifo.controller;

import com.squad5.fifo.dto.*;
import com.squad5.fifo.service.FilaService;
import com.squad5.fifo.service.VezService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController @RequestMapping("/filas")
@RequiredArgsConstructor
public class FilaController {

    private static final String MSG_CONVIDANTE_NAO_PODE_SER_CONVIDADO = "O Convidante não pode ser um dos convidados.";
    private static final String MSG_CONVIDADOS_REPETIDOS = "Há ids repetidos na lista de convidados.";

    private final FilaService filaService;

    private final VezService vezService;

    @GetMapping("/{dispositivoId}")
    public List<UsuarioDTO> gerarFila(@PathVariable Long dispositivoId){
        return filaService.gerarFila(dispositivoId);
    }

    @GetMapping("/{dispositivoId}/dadosPagina")
    public FilaPaginaDTO dadosPagina(@PathVariable Long dispositivoId){
        return filaService.dadosPagina(dispositivoId);
    }

    @PostMapping("/convidar")
    public VezDTO convidar(@RequestBody @Valid ConviteInsertDTO conviteInsertDTO){
        if(conviteInsertDTO.getConvidadoList() != null && conviteInsertDTO.getConvidadoList().contains(conviteInsertDTO.getConvidante()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_CONVIDANTE_NAO_PODE_SER_CONVIDADO);
        if(conviteInsertDTO.getConvidadoList() != null && conviteInsertDTO.getConvidadoList().stream()
                .distinct().count()
                < conviteInsertDTO.getConvidadoList().size())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_CONVIDADOS_REPETIDOS);

        return vezService.convidar(conviteInsertDTO);
    }

    @PostMapping("/aceitar")
    public VezDTO aceitarConvite(@RequestBody @Valid ConviteDTO conviteDTO){
        return vezService.aceitarConvite(conviteDTO);
    }

    @PostMapping("/recusar")
    public VezDTO recusarConvite(@RequestBody @Valid ConviteDTO conviteDTO){
        return vezService.recusarConvite(conviteDTO);
    }

    @GetMapping("/confirmarEntrada/{usuarioId}")
    public VezDTO entrarNaFila(@PathVariable Long usuarioId){
        return vezService.entrarNaFila(usuarioId);
    }


    @GetMapping("/confirmarEntrada/{usuarioId}/dadosPagina")
    public ConfirmacaoDTO confimacaoVez(@PathVariable Long usuarioId){
        return filaService.vezDadosConfirmacao(usuarioId);
    }

}
