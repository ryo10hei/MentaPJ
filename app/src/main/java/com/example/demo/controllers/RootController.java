package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.CreatForm;
import com.example.demo.models.InquiryForm;
import com.example.demo.repositries.CreatRepository;
import com.example.demo.repositries.InquiryRepository;

@Controller
@RequestMapping("/") //ドメイン以降のURLを指定（https://127.0.0.1/←「/」の部分　「//127.0.0.1」がドメイン）
public class RootController {

	@Autowired
	InquiryRepository repository;
	@Autowired
	CreatRepository creatRepository;

	@GetMapping //RequestMappingで指定したvalue以降のURLを指定（何も指定していない場合「/」までのURLはこのメソッドを起動する）
	public String index() {
		return "root/index";
	}

	@GetMapping("/form") //URLの「/」以降が「/form」となっている場合にこのメソッドが起動
	public String form(InquiryForm inquiryForm) {
		return "root/form";
	}

	@PostMapping("/form")
	public String form(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form";
		}

		// RDBと連携できることを確認しておきます。
		repository.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form";
	}
	
	@GetMapping("/creat")
	public String form(CreatForm creatForm) {
		return "root/creat";
	}

	@PostMapping("/creat")
	public String creatForm(@Validated CreatForm creatForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/creat";
		}

		// RDBと連携できることを確認しておきます。
		creatRepository.saveAndFlush(creatForm);
		creatForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/creat";
	}
	
	@GetMapping("/read")
	public String readForm(Model model) {

		// RDBと連携できることを確認しておきます。
		Iterable<CreatForm> creatList = creatRepository.findAll();
		model.addAttribute("creatList", creatList);
		return "root/read";
	}
}