package com.yg.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.ProductColor;
import com.yg.portfolio.model.ProductImg;
import com.yg.portfolio.model.ProductSize;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.repository.CommunityRepository;
import com.yg.portfolio.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> productBest() {
		return productRepository.productBest();
	}
	
	public List<Product> productNew() {
		return productRepository.productNew();
	}
	
	public List<Product> productOuter() {
		return productRepository.productOuter();
	}

	public List<Product> productTop() {
		return productRepository.productTop();
	}

	public List<Product> productPants() {
		return productRepository.productPants();
	}
	
	public Product productInfo(int productNo) {
		return productRepository.productInfo(productNo);
	}

	public List<ProductImg> productImg(int productNo) {
		return productRepository.productImg(productNo);
	}

	public List<ProductColor> productColor(int productNo) {
		return productRepository.productColor(productNo);
	}

	public List<ProductSize> productSize(int productNo) {
		return productRepository.productSize(productNo);
	}

	public List<ProductSize> search(String search) {
		return productRepository.search(search);
	}
	
}
