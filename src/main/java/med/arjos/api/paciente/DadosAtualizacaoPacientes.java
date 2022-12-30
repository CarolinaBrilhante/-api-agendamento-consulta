package med.arjos.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.arjos.api.endereco.DadosEndereco;

public record DadosAtualizacaoPacientes(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
