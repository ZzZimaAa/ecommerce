package com.coursework.ecommerce.repository;

import com.coursework.ecommerce.model.Cart;
import com.coursework.ecommerce.model.Order;
import com.coursework.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);

}