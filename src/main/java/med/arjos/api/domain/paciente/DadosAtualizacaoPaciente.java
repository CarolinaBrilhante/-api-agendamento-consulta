package med.arjos.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.arjos.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
