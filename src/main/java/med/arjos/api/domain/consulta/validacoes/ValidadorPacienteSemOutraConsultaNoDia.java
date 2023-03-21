package med.arjos.api.domain.consulta.validacoes;

import med.arjos.api.domain.ValidacaoException;
import med.arjos.api.domain.consulta.ConsultaRepository;
import med.arjos.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsulta = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsulta){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia!");
        }
    }
}
