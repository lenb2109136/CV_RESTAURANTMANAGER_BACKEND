package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.RAMWEBSOCKET.RamWebsocket;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.YeuCauService;

@RestController
@RequestMapping("/yeucau")
public class YeuCauController {
	
	@Autowired
	private YeuCauService yeuCauService;
	@PostMapping("/save")
	public ResponseEntity<response> save(@RequestBody RamWebsocket r){
		yeuCauService.save(r);
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, r),HttpStatus.OK);
	}
	
	@GetMapping("/huy")
	public ResponseEntity<response> huy(@RequestParam("id") int id){
		yeuCauService.huyDon(id);
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, null),HttpStatus.OK);
	}
	
	@PostMapping("/capnhat")
	public ResponseEntity<response> capNhat(@RequestBody RamWebsocket r){
		yeuCauService.capNhat(r);
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, r),HttpStatus.OK);
	}
	@PostMapping("/update")
	public ResponseEntity<response> update(@RequestBody RamWebsocket r){
		yeuCauService.update(r);
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, r),HttpStatus.OK);
	}
	
	
}
