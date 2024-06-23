package io.github.maatheusmartins.clientes.rest;

import io.github.maatheusmartins.clientes.model.entity.Cliente;
import io.github.maatheusmartins.clientes.model.entity.ServicoPrestado;
import io.github.maatheusmartins.clientes.model.repository.ClienteRepository;
import io.github.maatheusmartins.clientes.model.repository.ServicoPrestadoRepository;
import io.github.maatheusmartins.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.maatheusmartins.clientes.rest.util.FuncoesUteis;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository repository;
    private final FuncoesUteis funcoesUteis;

    public ServicoPrestadoController(ClienteRepository clienteRepository, ServicoPrestadoRepository repository, FuncoesUteis funcoesUteis) {
        this.clienteRepository = clienteRepository;
        this.repository = repository;
        this.funcoesUteis = funcoesUteis;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto) {
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(funcoesUteis.converterStringEmBigDecimal(dto.getPreco()));

        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes) {

        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}
