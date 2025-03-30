package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.ChiTietYeuCau;

@Repository
public interface ChiTietYeuCauRepository extends JpaRepository<ChiTietYeuCau, Integer>{

}
