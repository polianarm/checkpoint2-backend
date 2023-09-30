package br.com.dentalclinica.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoRequest {
    @Email
    private String email;
    @Pattern(regexp = "^\\(?(\\d{2})\\)?[-.\\s]?\\d{5}[-.\\s]?\\d{4}$",
            message = "O telefone deve estar em um formato válido.")
    private String telefone;
}
