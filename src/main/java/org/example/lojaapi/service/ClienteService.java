package org.example.lojaapi.service;

import org.example.lojaapi.model.Cliente;
import org.example.lojaapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listarTodos(){
        return repository.findAll();
    }

    public Optional<Cliente> buscarPorId(Integer id){
        return repository.findById(id);
    }

    public Cliente salvar(Cliente cliente){
        return repository.save(cliente);
    }

    public Cliente atualizar(Integer id, Cliente clienteAtualizado){
        return repository.findById(id).map(cliente -> {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            return repository.save(cliente);
        }).orElseThrow(()-> new RuntimeException("Cliente com o id "+id+" não encontrado"));
    }

    public void deletar(Integer id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Cliente com o id "+ id+ " não encontrado");
        }
        repository.deleteById(id);
    }

}
