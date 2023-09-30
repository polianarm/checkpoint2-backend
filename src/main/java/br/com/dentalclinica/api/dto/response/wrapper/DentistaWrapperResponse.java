package br.com.dentalclinica.api.dto.response.wrapper;

import br.com.dentalclinica.api.dto.response.list.DentistaListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DentistaWrapperResponse {
    private List<DentistaListResponse> dentistas;

    public List<DentistaListResponse> getDentistas() {
        return dentistas;
    }

    public void setDentistas(List<DentistaListResponse> dentistas) {
        this.dentistas = dentistas;
    }
}
