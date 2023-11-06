package be.intecbrussel.productmanagementapp.repositories;

import be.intecbrussel.productmanagementapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // default JPQL -> entities en properties.
    Optional<Product> findByName(String name);

    // readBy, queryBy, getBy
    // Limit, distinct, greaterThan
    List<Product> findByPriceGreaterThan(double price);

    // JPQL -> Java Persistence Query Language
    @Query("SELECT p FROM Product p WHERE " +
        "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
        "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> searchProducts(@Param("query") String query);

    // native sql query
    @Query(value = "SELECT * FROM products p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Product> searchProductsNativeSQL(@Param("query") String query);

}



