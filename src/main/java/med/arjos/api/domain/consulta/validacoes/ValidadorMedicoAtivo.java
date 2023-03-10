package med.arjos.api.domain.consulta.validacoes;

import med.arjos.api.domain.ValidacaoException;
import med.arjos.api.domain.consulta.DadosAgendamentoConsulta;
import med.arjos.api.domain.medico.MedicoRepository;

public class ValidadorMedicoAtivo {

    private MedicoRepository repository;

    private void validar(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());

        if (!medicoEstaAtivo){
            throw new ValidacaoException("A Consulta não pode ser agendada com médico que está inativo!");
        }

    }

}
