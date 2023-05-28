package com.haison.SpringbootSearchRestAPI.controller;

import com.haison.SpringbootSearchRestAPI.dto.ProductDTO;
import com.haison.SpringbootSearchRestAPI.entity.Product;
import com.haison.SpringbootSearchRestAPI.service.ProductService;
import org.modelmapper.internal.bytebuddy.implementation.InvokeDynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(@Qualifier("productServiceImpl") ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {

        ProductDTO newProduct = this.productService.createProduct(productDTO);

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("search")
    public ResponseEntity<List<ProductDTO>> getAllProduct(@RequestParam("query") String query) {

        List<ProductDTO> listProducts = this.productService.searchProducts(query);

        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @GetMapping("search-name")
    public ResponseEntity<List<ProductDTO>> getProductByName(@RequestParam("name") String nameQuery) {

        List<ProductDTO> listProductGetByName = this.productService.getProductByName(nameQuery);

        return new ResponseEntity<>(listProductGetByName, HttpStatus.OK);
    }

    @GetMapping("search-name-distinct")
    public ResponseEntity<ProductDTO> getProductByNameDistinct(@RequestParam("name") String nameQuery) {

        ProductDTO listProductGetByName = this.productService.getProductByNameDistinct(nameQuery);

        return new ResponseEntity<>(listProductGetByName, HttpStatus.OK);
    }

    @GetMapping("search-desc")
    public ResponseEntity<List<ProductDTO>> getProductByDescription(@RequestParam("desc") String descQuery) {

        List<ProductDTO> listProductGetByDesc = this.productService.getProductByDescription(descQuery);

        return new ResponseEntity<>(listProductGetByDesc, HttpStatus.OK);
    }

    @GetMapping("search-active")
    private ResponseEntity<List<ProductDTO>> getProductByActive(@RequestParam("active") boolean active) {
        List<ProductDTO> listProductByActive = this.productService.getProductByActive(active);

        return new ResponseEntity<>(listProductByActive,HttpStatus.OK);
    }

    @GetMapping("search-id-g-than")
    private ResponseEntity<List<ProductDTO>> getProductIdGreaterThanEqual(@RequestParam("id") long id) {
        List<ProductDTO> listProduct = this.productService.getProductByIdGreaterThanEqual(id);

        return new ResponseEntity<>(listProduct,HttpStatus.OK);
    }

    @GetMapping("search-id-l-than")
    private ResponseEntity<List<ProductDTO>> getProductIdLessThanEqual(@RequestParam("id") long id) {
        List<ProductDTO> listProduct = this.productService.getProductByIdLessThanEqual(id);

        return new ResponseEntity<>(listProduct,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> listProductDTO = this.productService.getAllProducts();

        return new ResponseEntity<>(listProductDTO, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id") long id) {

        ProductDTO productDTO = this.productService.getProductById(id);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable(name = "id") long id,
                                                    @RequestBody ProductDTO productDTO) {

        ProductDTO newProductUpdate = this.productService.updateProduct(id,productDTO);

        return new ResponseEntity<>(newProductUpdate, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable(name = "id") long id) {

        this.productService.deleteProduct(id);

        return ResponseEntity.ok("Delete Successfully");
    }
}
