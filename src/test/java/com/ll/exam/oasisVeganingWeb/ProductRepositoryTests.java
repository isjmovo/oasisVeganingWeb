package com.ll.exam.oasisVeganingWeb;

import com.ll.exam.oasisVeganingWeb.check.Ingredient;
import com.ll.exam.oasisVeganingWeb.check.IngredientRepository;
import com.ll.exam.oasisVeganingWeb.check.Product;
import com.ll.exam.oasisVeganingWeb.check.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductRepositoryTests {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private IngredientRepository ingredientRepository;

  private static long lastSampleDataId;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  public static long createSampleData(IngredientRepository ingredientRepository, ProductRepository productRepository) {
    Ingredient i1 = new Ingredient();
    i1.setName("정제수");
    ingredientRepository.save(i1);

    Ingredient i2 = new Ingredient();
    i2.setName("원액두유");
    ingredientRepository.save(i2);

    Ingredient i3 = new Ingredient();
    i3.setName("설탕");
    ingredientRepository.save(i3);

    Ingredient i4 = new Ingredient();
    i4.setName("기타과당");
    ingredientRepository.save(i4);

    Ingredient i5 = new Ingredient();
    i5.setName("아몬드 페이스트");
    ingredientRepository.save(i5);

    Ingredient i6 = new Ingredient();
    i6.setName("이소말토올리고당");
    ingredientRepository.save(i6);

    Ingredient i7 = new Ingredient();
    i7.setName("바나나농축액");
    ingredientRepository.save(i7);

    Ingredient i8 = new Ingredient();
    i8.setName("글리세린지방산에스테르");
    ingredientRepository.save(i8);

    Ingredient i9 = new Ingredient();
    i9.setName("카라기난");
    ingredientRepository.save(i9);

    Ingredient i10 = new Ingredient();
    i10.setName("구아검");
    ingredientRepository.save(i10);

    Ingredient i11 = new Ingredient();
    i11.setName("정제소금");
    ingredientRepository.save(i11);

    Ingredient i12 = new Ingredient();
    i12.setName("탄산수소나트륨");
    ingredientRepository.save(i12);

    Ingredient i13 = new Ingredient();
    i13.setName("젤란검");
    ingredientRepository.save(i13);

    Ingredient i14 = new Ingredient();
    i14.setName("향료");
    ingredientRepository.save(i14);

//    assertThat(i1.getId()).isEqualTo(lastSampleDataId + 1);
//    assertThat(i2.getId()).isEqualTo(lastSampleDataId + 2);

    Product p1 = new Product();
    p1.setName("식물성 바유");

//    p1.getIngredients().add(i1);
//    p1.getIngredients().add(i2);
//    p1.getIngredients().add(i3);
//    p1.getIngredients().add(i4);

    productRepository.save(p1);

    Product p2 = new Product();
    p2.setName("매일 두유");

    productRepository.save(p2);

//    product.getIngredients().add(ingredient1);
//    product.getIngredients().add(ingredient2);
    
    return p2.getId();
  }

  private void createSampleData() {
    lastSampleDataId = createSampleData(ingredientRepository, productRepository);
  }

  public static void clearData(IngredientRepository ingredientRepository, ProductRepository productRepository) {
    ingredientRepository.deleteAll();
    ingredientRepository.truncateTable();

    productRepository.deleteAll();
    productRepository.truncateTable();
  }

  private void clearData() {
    clearData(ingredientRepository, productRepository);
  }

  @Test
  void 저장() {
    Ingredient i1 = new Ingredient();
    i1.setName("정제수");
    ingredientRepository.save(i1);

    Ingredient i2 = new Ingredient();
    i2.setName("원액두유");
    ingredientRepository.save(i2);

    Ingredient i3 = new Ingredient();
    i3.setName("설탕");
    ingredientRepository.save(i3);

    Ingredient i4 = new Ingredient();
    i4.setName("기타과당");
    ingredientRepository.save(i4);

    Ingredient i5 = new Ingredient();
    i5.setName("아몬드 페이스트");
    ingredientRepository.save(i5);

    Ingredient i6 = new Ingredient();
    i6.setName("이소말토올리고당");
    ingredientRepository.save(i6);

    Ingredient i7 = new Ingredient();
    i7.setName("바나나농축액");
    ingredientRepository.save(i7);

    Ingredient i8 = new Ingredient();
    i8.setName("글리세린지방산에스테르");
    ingredientRepository.save(i8);

    Ingredient i9 = new Ingredient();
    i9.setName("카라기난");
    ingredientRepository.save(i9);

    Ingredient i10 = new Ingredient();
    i10.setName("구아검");
    ingredientRepository.save(i10);

    Ingredient i11 = new Ingredient();
    i11.setName("정제소금");
    ingredientRepository.save(i11);

    Ingredient i12 = new Ingredient();
    i12.setName("탄산수소나트륨");
    ingredientRepository.save(i12);

    Ingredient i13 = new Ingredient();
    i13.setName("젤란검");
    ingredientRepository.save(i13);

    Ingredient i14 = new Ingredient();
    i14.setName("향료");
    ingredientRepository.save(i14);

//    assertThat(i1.getId()).isEqualTo(lastSampleDataId + 1);
//    assertThat(i2.getId()).isEqualTo(lastSampleDataId + 2);

    Product p1 = new Product();
    p1.setName("식물성 바유");
    productRepository.save(p1);

    Product p2 = new Product();
    p2.setName("매일 두유");
    productRepository.save(p2);

    assertThat(p1.getId()).isEqualTo(lastSampleDataId + 1);
    assertThat(p2.getId()).isEqualTo(lastSampleDataId + 2);
  }

//  @Test
//  void 삭제() {
//    assertThat(productRepository.count()).isEqualTo(lastSampleDataId);
//    Product p = productRepository.findById(1L).get();
//    productRepository.delete(p);
//
//    assertThat(productRepository.count()).isEqualTo(lastSampleDataId - 1);
//  }

//  @Test
//  void 수정() {
//    assertThat(postRepository.count()).isEqualTo(lastSampleDataId);
//
//    MyPost p = postRepository.findById(1L).get();
//    p.setSubject("수정된 제목");
//    postRepository.save(p);
//
//    p = postRepository.findById(1L).get();
//    assertThat(p.getSubject()).isEqualTo("수정된 제목");
//  }

//  @Test
//  void findAll() {
//    List<Product> all = productRepository.findAll();
//    assertThat(all.size()).isEqualTo(lastSampleDataId);
//
//    Product p = all.get(0);
//    assertThat(p.getName()).isEqualTo("식물성 바유");
//  }

//  @Test
//  void pageable() {
//    Pageable pageable = PageRequest.of(0, (int) lastSampleDataId);
//    Page<MyPost> page = postRepository.findAll(pageable);
//
//    assertThat(page.getTotalPages()).isEqualTo(1);
//
//    System.out.println(page.getNumber());
//  }

//  @Test
//  void findBySubject() {
//    MyPost p = postRepository.findBySubject("비건 햄버거 먹어봤습니다!");
//    assertThat(p.getId()).isEqualTo(1);
//  }
//
//  @Test
//  void findBySubjectAndContent() {
//    MyPost p = postRepository.findBySubjectAndContent("비건 햄버거 먹어봤습니다!", "비건 햄버거");
//    assertThat(p.getId()).isEqualTo(1);
//  }
//
//  @Test
//  void findBySubjectLike() {
//    List<MyPost> pList = productRepository.findBySubjectLike("비건%");
//    MyPost p = pList.get(0);
//    assertThat(p.getSubject()).isEqualTo("비건 햄버거 먹어봤습니다!");
//  }

  @Test
  void createManySampleData() {
    boolean run = false;

    if (run == false) return;

    IntStream.rangeClosed(15, 300).forEach(id -> {
      Product p = new Product();
      p.setName("%d번 제품".formatted(id));

      productRepository.save(p);
    });
  }
}