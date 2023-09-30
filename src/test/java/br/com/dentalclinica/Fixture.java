package br.com.dentalclinica;
import br.com.dentalclinica.api.dto.request.ContatoRequest;
import br.com.dentalclinica.api.dto.request.EnderecoRequest;
import br.com.dentalclinica.api.dto.request.PacienteRequest;
import br.com.dentalclinica.domain.entity.GeneroEnum;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public final class Fixture {
    private static final Faker FAKER = new Faker(new Locale("pt", "BR"));

    public static class PacienteFake {
        public static PacienteRequest anyPaciente() {
            PacienteRequest paciente = new PacienteRequest();
            paciente.setNome(FAKER.rickAndMorty().character());
            paciente.setDataNascimento(LocalDate.now());
            paciente.setGenero(GeneroEnum.FEMININO);
            ContatoRequest contatoRequest = new ContatoRequest();
            contatoRequest.setEmail(FAKER.internet().emailAddress());
            contatoRequest.setTelefone("(99) 99999-9999");
            paciente.setContato(contatoRequest);
            EnderecoRequest enderecoRequest = new EnderecoRequest();
            enderecoRequest.setLogradouro(FAKER.address().streetAddress());
            enderecoRequest.setBairro(FAKER.address().secondaryAddress());
            enderecoRequest.setCidade(FAKER.address().cityName());
            enderecoRequest.setEstado(FAKER.address().state());
            enderecoRequest.setCep(FAKER.address().zipCode());
            paciente.setEndereco(enderecoRequest);
            return paciente;
        }
    }
}

