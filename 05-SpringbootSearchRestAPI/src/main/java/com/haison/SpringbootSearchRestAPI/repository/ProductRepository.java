package com.haison.SpringbootSearchRestAPI.repository;

import com.haison.SpringbootSearchRestAPI.dto.ProductDTO;
import com.haison.SpringbootSearchRestAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.sound.sampled.Port;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("""
            SELECT p FROM Product p WHERE
            p.name LIKE CONCAT('%',:query,'%')
            OR p.description LIKE CONCAT('%',:query,'%')
            """)
    List<Product> searchProducts(String query);

    @Query(value = """
            SELECT * FROM Product p WHERE
            p.name LIKE CONCAT('%',:query,'%')
            OR p.description LIKE CONCAT('%',:query,'%')
            """, nativeQuery = true)
    List<Product> searchProductsSQL(@Param("query") String query);

    // ----------------- Custom query method -----------------
    List<Product> findByName(String queryName); // Name must be entered correctly
    List<Product> findDistinctProductByNameContaining(String nameQuery);
    List<Product> findByNameContaining(String queryName); // Useful
    List<Product> findByActive(boolean active);
    List<Product> findByDescriptionContaining(String queryDesc);

    List<Product> findByIdGreaterThanEqual(long id); // >=
    List<Product> findByIdLessThanEqual(long id);// <=

}
