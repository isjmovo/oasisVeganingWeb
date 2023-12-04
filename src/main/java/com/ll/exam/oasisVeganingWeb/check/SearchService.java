package com.ll.exam.oasisVeganingWeb.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private IngredientRepository ingredientRepository;
  @Autowired
  private NonVeganIngredientRepository nonVeganIngredientRepository;
  @Autowired
  private CSVService csvService;

  public Page<Product> getList(String kw, int page) {
    List<Sort.Order> sorts = new ArrayList<>();
    sorts.add(Sort.Order.desc("id"));

    Pageable pageable = PageRequest.of(page, 6, Sort.by(sorts));

    if (kw == null || kw.trim().length() == 0) {
      return productRepository.findAll(pageable);
    }

    return productRepository.findByNameContains(kw, pageable);
  }

//  public class ProductSpecifications {

//    public static Specification<Product> search(String kw) {
//      return new Specification<>() {
//        private static final long serialVersionUID = 1L;
//
//        @Override
//        public Predicate toPredicate(Root<Product> p, CriteriaQuery<?> query, CriteriaBuilder cb) {
//          query.distinct(true);
//
//          Join<Product, Ingredient> pi = p.join("ingredients", JoinType.LEFT);
//
//          // Example: searching by product name or ingredient name
//          Predicate productNamePredicate = cb.like(p.get("name"), "%" + kw + "%");
//          Predicate ingredientNamePredicate = cb.like(pi.get("name"), "%" + kw + "%");
//
//          return cb.or(productNamePredicate, ingredientNamePredicate);
//        }
//      };
//    }
//  }
}
