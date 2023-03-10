package med.arjos.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta (Long id, Long idMedico, Long idPaciene, LocalDateTime data){
}
