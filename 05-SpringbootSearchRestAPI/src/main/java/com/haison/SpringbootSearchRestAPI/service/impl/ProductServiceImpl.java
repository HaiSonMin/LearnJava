package com.haison.SpringbootSearchRestAPI.service.impl;

import com.haison.SpringbootSearchRestAPI.dto.ProductDTO;
import com.haison.SpringbootSearchRestAPI.entity.Product;
import com.haison.SpringbootSearchRestAPI.exception.ProductNotFoundException;
import com.haison.SpringbootSearchRestAPI.repository.ProductRepository;
import com.haison.SpringbootSearchRestAPI.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> searchProducts(String query) {
        List<Product> listProducts = this.productRepository.searchProducts(query);

        return listProducts.stream()
                           .map(product -> this.modelMapper.map(product,ProductDTO.class))
                           .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(long id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product don't exists")
        );

        return this.modelMapper.map(product,ProductDTO.class);


    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = this.productRepository.findAll();

        return products.stream().map(pro-> this.modelMapper.map(pro,ProductDTO.class))
                                                  .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductByName(String nameQuery) {
        List<Product> listProductByName = this.productRepository.findByNameContaining(nameQuery);

        if(listProductByName.size() ==0) throw new ProductNotFoundException("Does not exist name of product!!!");

        return listProductByName.stream().map(product -> this.modelMapper.map(product,ProductDTO.class))
                                         .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductByDescription(String descQuery) {
        List<Product> listProduct = this.productRepository.findByDescriptionContaining(descQuery);

        return listProduct.stream().map(pro -> this.modelMapper.map(pro,ProductDTO.class))
                                   .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductByActive(boolean active) {

        List<Product> listProduct = this.productRepository.findByActive(active);

        return listProduct.stream().map(pro->this.modelMapper.map(pro,ProductDTO.class))
                                   .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductByNameDistinct(String nameQuery) {

        List<Product> product = this.productRepository.findDistinctProductByNameContaining(nameQuery);

        if(product==null) throw new ProductNotFoundException("Product don't exists");

        return this.modelMapper.map(product.get(0),ProductDTO.class);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        Product product = this.modelMapper.map(productDTO,Product.class);

        Product newProduct = this.productRepository.save(product);

        return this.modelMapper.map(newProduct,ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProductByIdGreaterThanEqual(long id) {
        List<Product> productList = this.productRepository.findByIdGreaterThanEqual(id);

        return productList.stream().map(pro->this.modelMapper.map(pro,ProductDTO.class))
                                   .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductByIdLessThanEqual(long id) {
        List<Product> productList = this.productRepository.findByIdLessThanEqual(id);

        return productList.stream().map(pro->this.modelMapper.map(pro,ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product don't exists")
        );

        product.setActive(productDTO.isActive());
        product.setSku(productDTO.getSku());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());

        this.productRepository.save(product);

        return this.modelMapper.map(product,ProductDTO.class);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product don't exists")
        );

        this.productRepository.delete(product);
    }
    @Override
    public void deleteAllProduct() {
        List<Product> listPro = new ArrayList<>();
        Product pro1 = new Product();
        Product pro2 = new Product();
        listPro.add(pro1);
        listPro.add(pro2);
        this.productRepository.deleteAll(listPro);
        this.productRepository.deleteAll(List.of(pro1,pro2));
    }

}
