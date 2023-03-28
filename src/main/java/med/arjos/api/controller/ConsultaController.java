package med.arjos.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.arjos.api.domain.consulta.AgendaDeConsultas;
import med.arjos.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultas agenda;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
      var dto = agenda.agendar(dados);
      return ResponseEntity.ok(dto);
    }

}
