package com.guiconte.service.impl;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.domain.entity.Emprestimo;
import com.guiconte.domain.entity.ItemEmprestimo;
import com.guiconte.domain.entity.Livro;
import com.guiconte.domain.enums.StatusEmprestimo;
import com.guiconte.dto.EmprestimoDTO;
import com.guiconte.dto.ItemEmprestimoDTO;
import com.guiconte.repository.Clientes;
import com.guiconte.repository.Emprestimos;
import com.guiconte.repository.ItemsEmprestimo;
import com.guiconte.repository.Livros;
import com.guiconte.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final Emprestimos emprestimosRepository;
    private final Clientes clientesRepository;
    private final Livros livrosRepository;
    private final ItemsEmprestimo itemsEmprestimoRepository;

    @Override
    @Transactional
    public Emprestimo save(EmprestimoDTO emprestimoDTO) {
        Integer cod_cliente = emprestimoDTO.getCod_cliente();
        Cliente cliente = clientesRepository
                        .findById(cod_cliente)
                        .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Codigo de cliente invalido !")
                        );

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setDataEmprestimo(emprestimoDTO.getData_emprestimo());
        emprestimo.setStatusEmprestimo(StatusEmprestimo.ABERTO);

        List<ItemEmprestimo> itemsEmprestimo = convertItems(emprestimo,emprestimoDTO.getLivros());
        emprestimosRepository.save(emprestimo);
        itemsEmprestimoRepository.saveAll(itemsEmprestimo);
        emprestimo.setLivros(itemsEmprestimo);
        return emprestimo;
    }

    @Override
    public Optional<Emprestimo> find(Integer cod_emprestimo) {
        return emprestimosRepository.findByIdFetchLivros(cod_emprestimo);
    }

    @Override
    public void updateStatus(Integer cod_eprestimo, StatusEmprestimo statusEmprestimo) {
        emprestimosRepository
                .findById(cod_eprestimo)
                .map(emprestimo -> {
                    emprestimo.setStatusEmprestimo(statusEmprestimo);
                    if(statusEmprestimo.name().equals("CONCLUIDO")){
                        emprestimo.setDataDevolucao(LocalDate.now());
                    }else{
                        emprestimo.setDataDevolucao(null);
                    }
                    return emprestimosRepository.save(emprestimo);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "Emprestimo não encontrado !"));

    }

    private List<ItemEmprestimo> convertItems (Emprestimo emprestimo, List<ItemEmprestimoDTO> items){
        if(items.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não é possivel realizar emprestimo sem itens!");
        }
        return items
                .stream()
                .map(dto ->{
                    Integer cod_livro = dto.getCod_livro();
                    Livro livro = livrosRepository
                            .findById(cod_livro)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                            "Codigo de livro invalido !"));

                    ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
                    itemEmprestimo.setEmprestimo(emprestimo);
                    itemEmprestimo.setLivro(livro);

                    return itemEmprestimo;
                }).collect(Collectors.toList());
    }
}
