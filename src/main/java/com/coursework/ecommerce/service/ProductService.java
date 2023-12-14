package com.coursework.ecommerce.service;

import com.coursework.ecommerce.dto.ProductDto;
import com.coursework.ecommerce.exceptions.ProductNotExistsException;
import com.coursework.ecommerce.model.Category;
import com.coursework.ecommerce.model.Product;
import com.coursework.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        return product;
    }

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        product.setId(productID);
        productRepository.save(product);
    }


    public Product getProductById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistsException("Product id is invalid " + productId);
        return optionalProduct.get();
    }

    public List<ProductDto> searchProducts(String query) {
        // Используйте метод findByNameContaining из ProductRepository для поиска по названию продукта
        List<Product> searchResults = productRepository.findByNameContaining(query);


        List<ProductDto> searchResultsDtos = new ArrayList<>();
        for(Product product : searchResults) {
            ProductDto productDto = getDtoFromProduct(product);
            searchResultsDtos.add(productDto);
        }
        return searchResultsDtos;


        // Преобразуйте найденные продукты в DTO
        //return searchResults.stream()
                //.map(ProductService::getDtoFromProduct)
                //.collect(Collectors.toList());
    }


}