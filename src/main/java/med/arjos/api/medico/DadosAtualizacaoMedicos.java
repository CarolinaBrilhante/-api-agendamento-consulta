package med.arjos.api.medico;

import jakarta.validation.constraints.NotNull;
import med.arjos.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedicos(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {


}
