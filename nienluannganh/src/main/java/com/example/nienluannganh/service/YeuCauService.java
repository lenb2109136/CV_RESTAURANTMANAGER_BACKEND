package com.example.nienluannganh.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.RAMWEBSOCKET.RamWebsocket;
import com.example.nienluannganh.model.Ban;
import com.example.nienluannganh.model.ChiTietYeuCau;
import com.example.nienluannganh.model.ChiTietYeuCauBan;
import com.example.nienluannganh.model.CoSoLuongComBo;
import com.example.nienluannganh.model.CoSoLuongMonAn;
import com.example.nienluannganh.model.CoSoLuongThucUong;
import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.model.YeuCau;
import com.example.nienluannganh.model.embededid.ChiTietYeuCauBanId;
import com.example.nienluannganh.model.embededid.CoSoLuongComBoId;
import com.example.nienluannganh.model.embededid.CoSoLuongMonAnId;
import com.example.nienluannganh.model.embededid.CoSoLuongThucUongId;
import com.example.nienluannganh.repository.BanRepository;
import com.example.nienluannganh.repository.ChiTietYeuCauBanRepository;
import com.example.nienluannganh.repository.ChiTietYeuCauRepository;
import com.example.nienluannganh.repository.CoSoLuongComBoRepository;
import com.example.nienluannganh.repository.CoSoLuongMonAnRepository;
import com.example.nienluannganh.repository.CoSoLuongThucUongRepository;
import com.example.nienluannganh.repository.ComBoRepository;
import com.example.nienluannganh.repository.MonAnRepository;
import com.example.nienluannganh.repository.ThucUongRepository;
import com.example.nienluannganh.repository.YeuCauRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class YeuCauService {
	@Autowired
	private YeuCauRepository yeuCauRepository;
	
	@Autowired
	MonAnRepository monAnRepository;
	
	
	@Autowired
	ThucUongRepository thucUongRepository;
	
	@Autowired
	ComBoRepository comBoRepository;
	
	@Autowired
	ChiTietYeuCauRepository chiTietYeuCauRepository;
	
	@Autowired
	CoSoLuongMonAnRepository coSoLuongMonAnRepository;
	
	@Autowired
	CoSoLuongComBoRepository coSoLuongComBoRepository;
	
	@Autowired
	CoSoLuongThucUongRepository coSoLuongThucUongRepository;
	
	@Autowired
	BanRepository banRepository;
	
	@Autowired
	private ChiTietYeuCauBanRepository chiTietYeuCauBanRepository;
	
	
	
	public YeuCau saveYeuCau(YeuCau y) {
		return yeuCauRepository.save(y);
	}
	public Optional<YeuCau> getYeuCauById(int id) {
		return yeuCauRepository.findById(id);
	}
	
	
	@org.springframework.transaction.annotation.Transactional
	public void update(RamWebsocket l) {
		YeuCau y = yeuCauRepository.findById(l.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy yêu cầu cần chỉnh sửa")) ;
		y.setThoiGianYeuCau(LocalDateTime.now());
		y.setTrangThaiYeuCau(0);
		l.getListOrder().forEach(data->{
		});
		l.getListOrder().forEach(d1->{
			ChiTietYeuCau c= new ChiTietYeuCau();
			c.setYeuCau(y);
			chiTietYeuCauRepository.save(c);
			d1.setId(c.getId());
			d1.getBan().forEach(d2->{
				ChiTietYeuCauBan c1= new ChiTietYeuCauBan();
				ChiTietYeuCauBanId c2= new ChiTietYeuCauBanId();
				Ban b1=banRepository.findById(d2.getBanId()).orElseThrow(()-> new EntityNotFoundException("Thông tin không hợp lệ"));
				b1.setTrong(false);
				banRepository.save(b1);
				c1.setBan(b1);
				c1.setChiTietYeuCau(c);
				c2.setBAN_STT(b1.getStt());
				c2.setCTYC_ID(c.getId());
				c1.setId(c2);
				chiTietYeuCauBanRepository.save(c1);
				
			});
			d1.getDsma().forEach(d3->{
				MonAn m= monAnRepository.findById(d3.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin món ăn không hợp lệ"));
				CoSoLuongMonAn c2= new CoSoLuongMonAn();
				c2.setChiTietYeuCau(c);
				c2.setMonAn(m);
				c2.setSoLuong(d3.getSoLuong());
				CoSoLuongMonAnId id= new CoSoLuongMonAnId();
				id.setCTYC_ID(c.getId());
				id.setMA_ID(m.getId());
				c2.setId(id);
				coSoLuongMonAnRepository.save(c2);
			});
			d1.getDstu().forEach(d4->{
				ThucUong m= thucUongRepository.findById(d4.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin thức uống không hợp lệ"));
				CoSoLuongThucUong c2= new CoSoLuongThucUong();
				c2.setChiTietYeuCau(c);
				c2.setThucUong(m);
				c2.setSoLuong(d4.getSoLuong());
				CoSoLuongThucUongId id= new CoSoLuongThucUongId();
				id.setCTYC_ID(c.getId());
				id.setMA_ID(m.getId());
				c2.setId(id);
				coSoLuongThucUongRepository.save(c2);
			});
			d1.getDscb().forEach(d5->{
				ComBo m= comBoRepository.findById(d5.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin combo không hợp lệ"));
				CoSoLuongComBo c2= new CoSoLuongComBo();
				c2.setChiTietYeuCau(c);
				c2.setComBo(m);
				c2.setSoLuong(d5.getSoLuong());
				CoSoLuongComBoId id= new CoSoLuongComBoId();
				id.setCTYC_ID(c.getId());
				id.setCB_ID(m.getId());
				c2.setId(id);
				coSoLuongComBoRepository.save(c2);
			});
		});
	}
	@org.springframework.transaction.annotation.Transactional
	public void save(RamWebsocket l) {
		YeuCau y = new YeuCau();
		y.setThoiGianYeuCau(LocalDateTime.now());
		y.setTrangThaiYeuCau(0);
		yeuCauRepository.save(y);
		// set
		l.setId(y.getId());;
		l.getListOrder().forEach(d1->{
			ChiTietYeuCau c= new ChiTietYeuCau();
			c.setYeuCau(y);
			chiTietYeuCauRepository.save(c);
			//set
			d1.setId(c.getId());
			d1.getBan().forEach(d2->{
				ChiTietYeuCauBan c1= new ChiTietYeuCauBan();
				ChiTietYeuCauBanId c2= new ChiTietYeuCauBanId();
				Ban b1=banRepository.findById(d2.getBanId()).orElseThrow(()-> new EntityNotFoundException("Thông tin không hợp lệ"));
				b1.setTrong(false);
				banRepository.save(b1);
				c1.setBan(b1);
				c1.setChiTietYeuCau(c);
				c2.setBAN_STT(b1.getStt());
				c2.setCTYC_ID(c.getId());
				c1.setId(c2);
				chiTietYeuCauBanRepository.save(c1);
				
			});
			d1.getDsma().forEach(d3->{
				MonAn m= monAnRepository.findById(d3.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin món ăn không hợp lệ"));
				CoSoLuongMonAn c2= new CoSoLuongMonAn();
				c2.setChiTietYeuCau(c);
				c2.setMonAn(m);
				c2.setSoLuong(d3.getSoLuong());
				CoSoLuongMonAnId id= new CoSoLuongMonAnId();
				id.setCTYC_ID(c.getId());
				id.setMA_ID(m.getId());
				c2.setId(id);
				coSoLuongMonAnRepository.save(c2);
			});
			d1.getDstu().forEach(d4->{
				ThucUong m= thucUongRepository.findById(d4.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin thức uống không hợp lệ"));
				CoSoLuongThucUong c2= new CoSoLuongThucUong();
				c2.setChiTietYeuCau(c);
				c2.setThucUong(m);
				c2.setSoLuong(d4.getSoLuong());
				CoSoLuongThucUongId id= new CoSoLuongThucUongId();
				id.setCTYC_ID(c.getId());
				id.setMA_ID(m.getId());
				c2.setId(id);
				coSoLuongThucUongRepository.save(c2);
			});
			d1.getDscb().forEach(d5->{
				ComBo m= comBoRepository.findById(d5.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin combo không hợp lệ"));
				CoSoLuongComBo c2= new CoSoLuongComBo();
				c2.setChiTietYeuCau(c);
				c2.setComBo(m);
				c2.setSoLuong(d5.getSoLuong());
				CoSoLuongComBoId id= new CoSoLuongComBoId();
				id.setCTYC_ID(c.getId());
				id.setCB_ID(m.getId());
				c2.setId(id);
				coSoLuongComBoRepository.save(c2);
			});
		});
	}
	
	@Transactional
	public void capNhat(RamWebsocket l) {
			YeuCau y = yeuCauRepository.findById(l.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy yêu cầu cần tìm"));
			yeuCauRepository.delete(y);
			save(l);
	}
	@Transactional
	public void huyDon(int yeuCauId) {
		banRepository.setBanTrong(yeuCauId);
		YeuCau y= yeuCauRepository.findById(yeuCauId).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy yêu cầu"));
		yeuCauRepository.delete(y);
	}
}
