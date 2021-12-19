package com.yg.portfolio.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.ProductColor;
import com.yg.portfolio.model.ProductImg;
import com.yg.portfolio.model.ProductSize;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.model.User;


@Mapper
public interface ProductRepository {
	
	public List<Product> productBest();
	
	public List<Product> productNew();
	
	public Product productInfo(int productNo);

	public List<ProductImg> productImg(int productNo);

	public List<ProductColor> productColor(int productNo);

	public List<ProductSize> productSize(int productNo);
	
}
