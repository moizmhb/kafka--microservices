package com.eta.stock.repository;

import com.eta.stock.entity.StockItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface StockRepository extends MongoRepository<StockItem, String> {


    List<StockItem>findByItemNumberIn(List<String> itemNumbers);

}
