package com.haison.SpringbootSearchRestAPI.service;

import com.haison.SpringbootSearchRestAPI.dto.ProductDTO;
import com.haison.SpringbootSearchRestAPI.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> searchProducts(String query);
    ProductDTO getProductById(long id);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductByNameDistinct(String nameQuery);
    List<ProductDTO> getProductByName(String nameQuery);
    List<ProductDTO> getProductByDescription(String descQuery);
    List<ProductDTO> getProductByActive(boolean active);
    List<ProductDTO> getProductByIdGreaterThanEqual(long id);
    List<ProductDTO> getProductByIdLessThanEqual(long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(long id, ProductDTO productDTO);
    void deleteProduct(long id);
    void deleteAllProduct();
}
