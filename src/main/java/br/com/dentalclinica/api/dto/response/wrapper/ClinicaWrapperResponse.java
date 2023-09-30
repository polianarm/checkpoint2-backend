package br.com.dentalclinica.api.dto.response.wrapper;

import br.com.dentalclinica.api.dto.response.list.ClinicaListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ClinicaWrapperResponse {
    private List<ClinicaListResponse> clinicas;
}