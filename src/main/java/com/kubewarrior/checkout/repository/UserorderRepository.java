package com.kubewarrior.checkout.repository;

import com.kubewarrior.checkout.domain.Userorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserorderRepository extends JpaRepository<Userorder, Long> {
}