package med.arjos.api.domain.consulta.validacoes;

import med.arjos.api.domain.ValidacaoException;
import med.arjos.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void  validar(DadosAgendamentoConsulta dados){

        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("A Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
