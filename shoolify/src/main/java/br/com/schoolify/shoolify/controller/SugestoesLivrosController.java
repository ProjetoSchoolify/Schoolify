package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.model.SugestoesLivros;
import br.com.schoolify.shoolify.repository.SugestoesLivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sugestoesLivros")
public class SugestoesLivrosController {

    @Autowired
    private SugestoesLivrosRepository sugestoesLivrosRepository;

    // GET - Listar todas as sugestões de livros
    @GetMapping
    public List<SugestoesLivros> listarSugestoesLivros() {
        return sugestoesLivrosRepository.findAll();
    }

    // GET - Buscar sugestão de livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<SugestoesLivros> buscarSugestaoLivrosPorId(@PathVariable Long id) {
        Optional<SugestoesLivros> sugestaoLivros = sugestoesLivrosRepository.findById(id);
        return sugestaoLivros.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Criar uma nova sugestão de livro
    @PostMapping
    public SugestoesLivros criarSugestaoLivros(@RequestBody SugestoesLivros sugestoesLivros) {
        return sugestoesLivrosRepository.save(sugestoesLivros);
    }

    // PUT - Atualizar uma sugestão de livro existente
    @PutMapping("/{id}")
    public ResponseEntity<SugestoesLivros> atualizarSugestaoLivros(@PathVariable Long id, @RequestBody SugestoesLivros sugestoesLivrosAtualizada) {
        Optional<SugestoesLivros> sugestaoLivrosOptional = sugestoesLivrosRepository.findById(id);
        if (sugestaoLivrosOptional.isPresent()) {
            SugestoesLivros sugestoesLivros = sugestaoLivrosOptional.get();
            sugestoesLivros.setNome(sugestoesLivrosAtualizada.getNome());
            sugestoesLivros.setCapa(sugestoesLivrosAtualizada.getCapa());
            sugestoesLivros.setLinkLivros(sugestoesLivrosAtualizada.getLinkLivros());
            SugestoesLivros sugestoesLivrosSalva = sugestoesLivrosRepository.save(sugestoesLivros);
            return ResponseEntity.ok(sugestoesLivrosSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Excluir uma sugestão de livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSugestaoLivros(@PathVariable Long id) {
        if (sugestoesLivrosRepository.existsById(id)) {
            sugestoesLivrosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}