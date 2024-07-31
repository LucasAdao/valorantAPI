package dev.lucas.valorantapi.controller;

import dev.lucas.valorantapi.model.Agente;
import dev.lucas.valorantapi.service.AgenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/valorant")
public class AgenteController {

    @Autowired
    private AgenteService agenteService;

    @PostMapping("/agentes")
    public ResponseEntity<Agente> adicionarAgente(@RequestBody Agente agente){
         agenteService.adicionarAgente(agente);
        return new ResponseEntity<>(agente, HttpStatus.CREATED);
    }

    @GetMapping("/agentes")
    public ResponseEntity<List<Agente>> buscarAgentes(){
        List<Agente> todosAgentes = agenteService.buscarAgentes();
        return new ResponseEntity<>(todosAgentes, HttpStatus.OK);
    }

    @GetMapping("/agentes/{id}")
    public ResponseEntity<?> buscarAgentePorId(@PathVariable Long id){
        Optional<Agente> agenteBuscado = agenteService.buscarAgentePorId(id);

        return agenteBuscado.isPresent() ?
                new ResponseEntity<>(agenteBuscado.get(), HttpStatus.OK) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agente não encontrado");
    }

    @DeleteMapping("/agentes/{id}")
    public ResponseEntity<?> deletarAgente(@PathVariable Long id){
        try {
            Optional<Agente> agenteBuscado = agenteService.buscarAgentePorId(id);
            agenteService.deletarAgente(id);
            return new ResponseEntity<>(agenteBuscado.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/agentes/{id}")
    public ResponseEntity<?> atualizarAgentePorId(@PathVariable Long id, @RequestBody Agente agente){
        try{
            Agente agenteBuscadoParaAtualizar = agenteService.atualizarAgentePorId(id, agente);
            return new ResponseEntity<>(agenteBuscadoParaAtualizar, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
