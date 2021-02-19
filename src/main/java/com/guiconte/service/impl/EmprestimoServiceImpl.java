package com.guiconte.service.impl;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.domain.entity.Emprestimo;
import com.guiconte.domain.entity.ItemEmprestimo;
import com.guiconte.domain.entity.Livro;
import com.guiconte.domain.enums.StatusEmprestimo;
import com.guiconte.dto.EmprestimoDTO;
import com.guiconte.dto.InformacaoEmprestimoDTO;
import com.guiconte.dto.InformacaoItemEmprestimoDTO;
import com.guiconte.dto.ItemEmprestimoDTO;
import com.guiconte.exception.*;
import com.guiconte.repository.Clientes;
import com.guiconte.repository.Emprestimos;
import com.guiconte.repository.ItemsEmprestimo;
import com.guiconte.repository.Livros;
import com.guiconte.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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
    public InformacaoEmprestimoDTO save(EmprestimoDTO emprestimoDTO) {
        Integer cod_cliente = emprestimoDTO.getCod_cliente();
        Cliente cliente = clientesRepository
                        .findById(cod_cliente)
                        .orElseThrow(() -> new ClienteNotFoundException());

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setDataEmprestimo(emprestimoDTO.getData_emprestimo());
        emprestimo.setStatusEmprestimo(StatusEmprestimo.ABERTO);

        List<ItemEmprestimo> itemsEmprestimo = convertItems(emprestimo,emprestimoDTO.getLivros());
        emprestimosRepository.save(emprestimo);
        itemsEmprestimoRepository.saveAll(itemsEmprestimo);
        emprestimo.setLivros(itemsEmprestimo);
        return convertDTO(emprestimo);
    }

    @Override
    public InformacaoEmprestimoDTO find(Integer cod_emprestimo) {
        return emprestimosRepository.findByIdFetchLivros(cod_emprestimo)
                                    .map( emprestimo -> convertDTO(emprestimo))
                                    .orElseThrow( () -> new EmprestimoNotFoundException());
    }

    @Override
    public List<InformacaoEmprestimoDTO> findAll() {
        return emprestimosRepository.findAll()
                                    .stream()
                                    .map(emprestimo -> convertDTO(emprestimo))
                                    .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Integer cod_eprestimo, String status) {
        if(!status.equals("ABERTO") && !status.equals("CONCLUIDO")){
            throw new InvalidStatusException();
        }
        StatusEmprestimo statusEmprestimo = StatusEmprestimo.valueOf(status);
        emprestimosRepository
                .findById(cod_eprestimo)
                .map(emprestimo -> {
                    emprestimo.setStatusEmprestimo(statusEmprestimo);
                    if(statusEmprestimo.name().equals("CONCLUIDO")){
                        emprestimo.setDataDevolucao(LocalDate.now());
                    }else if (statusEmprestimo.name().equals("ABERTO")){
                        emprestimo.setDataDevolucao(null);
                    }
                    return emprestimosRepository.save(emprestimo);
                })
                .orElseThrow(() -> new EmprestimoNotFoundException());
    }

    private List<ItemEmprestimo> convertItems (Emprestimo emprestimo, List<ItemEmprestimoDTO> items){
        if(items.isEmpty()){
            throw new EmprestimoWithoutItemsException();
        }
        return items
                .stream()
                .map(dto ->{
                    Integer cod_livro = dto.getCod_livro();
                    Livro livro = livrosRepository
                            .findById(cod_livro)
                            .orElseThrow(() -> new LivroNotFoundException());

                    ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
                    itemEmprestimo.setEmprestimo(emprestimo);
                    itemEmprestimo.setLivro(livro);

                    return itemEmprestimo;
                }).collect(Collectors.toList());
    }

    private InformacaoEmprestimoDTO convertDTO(Emprestimo emprestimo){
        return InformacaoEmprestimoDTO
                .builder()
                .codigo(emprestimo.getCod_emprestimo())
                .cpf(emprestimo.getCliente().getCpf())
                .cliente(emprestimo.getCliente().getNome())
                .data_emprestimo(emprestimo.getDataEmprestimo())
                .data_devolucao(emprestimo.getDataDevolucao())
                .status(emprestimo.getStatusEmprestimo().name())
                .livros(convertDTO(emprestimo.getLivros()))
                .build();
    }

    private List<InformacaoItemEmprestimoDTO> convertDTO(List<ItemEmprestimo> itemEmprestimo){
        if (CollectionUtils.isEmpty(itemEmprestimo)){
            return Collections.emptyList();
        }
        return itemEmprestimo
                .stream()
                .map(item -> InformacaoItemEmprestimoDTO
                        .builder()
                        .titulo(item.getLivro().getTitulo())
                        .isbn(item.getLivro().getIsbn())
                        .build()
                ).collect(Collectors.toList());
    }
}
