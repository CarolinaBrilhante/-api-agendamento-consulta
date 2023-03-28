package med.arjos.api.domain.consulta.validacoes.agendamento;

import med.arjos.api.domain.ValidacaoException;
import med.arjos.api.domain.consulta.DadosAgendamentoConsulta;
import med.arjos.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.arjos.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("A Consulta não pode ser agendada com médico que está inativo!");
        }

    }

}
