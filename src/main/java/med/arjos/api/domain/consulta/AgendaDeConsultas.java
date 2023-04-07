package med.arjos.api.domain.consulta;

import med.arjos.api.domain.ValidacaoException;
import med.arjos.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.arjos.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.arjos.api.domain.medico.Medico;
import med.arjos.api.domain.medico.MedicoRepository;
import med.arjos.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente infomado não existe! ");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico infomado não existe! ");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

@Transactional
public void cancelar(DadosCancelamentoConsulta dados) {
    if (!consultaRepository.existsById(dados.idConsulta())) {
        throw new ValidacaoException("Id da consulta informado não existe!");
    }
     if(!consultaRepository.motivoCancelamentoIsNull(dados.idConsulta())) {
        throw new ValidacaoException("Consulta informada já está cancelada!");

    }
    validadoresCancelamento.forEach(v -> v.validar(dados));

    Consulta consulta = consultaRepository.getOne(dados.idConsulta());
    consulta.setMotivoCancelamento(dados.motivoCancelamento());
    consultaRepository.save(consulta);
}


}