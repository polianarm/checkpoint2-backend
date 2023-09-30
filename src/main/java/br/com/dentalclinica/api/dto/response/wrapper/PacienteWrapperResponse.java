package br.com.dentalclinica.api.dto.response.wrapper;

import br.com.dentalclinica.api.dto.response.list.PacienteListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PacienteWrapperResponse {
    private List<PacienteListResponse> pacientes;

    public List<PacienteListResponse> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<PacienteListResponse> pacientes) {
        this.pacientes = pacientes;
    }
}
