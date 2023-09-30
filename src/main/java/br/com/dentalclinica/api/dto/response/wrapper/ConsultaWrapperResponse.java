package br.com.dentalclinica.api.dto.response.wrapper;

import br.com.dentalclinica.api.dto.response.list.ConsultaListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConsultaWrapperResponse {
    private List<ConsultaListResponse> consultas;

    public List<ConsultaListResponse> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaListResponse> consultas) {
        this.consultas = consultas;
    }
}
