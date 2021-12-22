package com.yg.portfolio.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.Product;

@Mapper
public interface CategoryRepository {
	
	int categoryNewCnt();

	List<Product> categoryNew(int startPage, int endPage);

	int categoryBestCnt();
	
	List<Product> categoryBest(int startPage, int endPage);
	
	int categoryCnt(String category);

	List<Product> categoryOuter(int startPage, int endPage);

	List<Product> categoryTop(int startPage, int endPage);

	List<Product> categoryPants(int startPage, int endPage);

	List<Product> categoryShoes(int startPage, int endPage);

}
