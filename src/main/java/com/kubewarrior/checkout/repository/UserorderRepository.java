package com.kubewarrior.checkout.repository;

import com.kubewarrior.checkout.domain.Userorder;

import org.springframework.data.repository.CrudRepository;

public interface UserorderRepository extends CrudRepository<Userorder, Long> {}