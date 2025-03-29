package com.example.nienluannganh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETYEUCAU")
public class ChiTietYeuCau {

    @Id
    @Column(name = "CTYC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "YC_ID")
    private YeuCau yeuCau;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public YeuCau getYeuCau() {
		return yeuCau;
	}

	public void setYeuCau(YeuCau yeuCau) {
		this.yeuCau = yeuCau;
	}

	
    
}
