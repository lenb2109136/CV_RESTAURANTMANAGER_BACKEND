package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.MonAn;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Integer>{
	@Query(value = "select * from monan where lma_id=:loai and ma_tinhtrangsudung=1",nativeQuery = true)
	public List<MonAn> getMonAnByLoai(@Param("loai") int idloai);
	
	
	@Query(value = "select * from monan where ma_tinhtrangsudung=1",nativeQuery = true)
	public List<MonAn> getall();
}
