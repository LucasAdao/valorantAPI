package dev.lucas.valorantapi.service;

import dev.lucas.valorantapi.model.Agente;
import dev.lucas.valorantapi.repository.AgenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteService {

    @Autowired
    private AgenteRepository agenteRepository;

    public Agente adicionarAgente(Agente agente){
        return agenteRepository.save(agente);
    }

    public List<Agente> buscarAgentes(){
        return agenteRepository.findAll();
    }

    public Optional<Agente> buscarAgentePorId(Long id){
       return agenteRepository.findById(id);

    }

    public void deletarAgente(Long id){
        Optional<Agente> agenteQueSeraDeletado = buscarAgentePorId(id);
        if(agenteQueSeraDeletado.isPresent()){
            agenteRepository.deleteById(id);
        }else{
            throw new RuntimeException("O agente com esse ID não existe");
        }

    }

    public Agente atualizarAgentePorId(Long id,Agente agente){
        Optional<Agente> agenteExistente = buscarAgentePorId(id);
        if(agenteExistente.isPresent()){
            Agente agenteAtualizado = agenteExistente.get();
            if(agente.getNome() != null){
                agenteAtualizado.setNome(agente.getNome());
            }
            if(agente.getDescricao() != null){
                agenteAtualizado.setDescricao(agente.getDescricao());
            }
            if (agente.getClasse() != null){
                agenteAtualizado.setClasse(agente.getClasse());
            }
            
            return agenteRepository.save(agenteAtualizado);
        }else{
            throw new RuntimeException("Agente com id " + id + " não encontrado!");
        }
    }

}
