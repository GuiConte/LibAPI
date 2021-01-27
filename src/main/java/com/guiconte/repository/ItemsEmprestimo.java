package com.guiconte.repository;

import com.guiconte.domain.entity.ItemEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsEmprestimo extends JpaRepository<ItemEmprestimo, Integer> {
}
