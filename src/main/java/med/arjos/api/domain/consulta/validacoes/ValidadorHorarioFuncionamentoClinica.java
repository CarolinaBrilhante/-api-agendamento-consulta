package med.arjos.api.domain.consulta.validacoes;

import med.arjos.api.domain.ValidacaoException;
import med.arjos.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void  validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if(domingo || antesDaAberturaDaClinica ||depoisDoEncerramentoDaClinica){
            throw  new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }
}
