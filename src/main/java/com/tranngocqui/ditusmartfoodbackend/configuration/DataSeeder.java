package com.tranngocqui.ditusmartfoodbackend.configuration;

import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import com.tranngocqui.ditusmartfoodbackend.entity.Dish;
import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. Tạo category
        List<Category> categories = new ArrayList<>();
        String[][] categoryData = {
                {"MonChinh", "Các món chính"},
                {"MonTrangMieng", "Các món tráng miệng"},
                {"DoUong", "Các loại đồ uống"},
                {"MonAnNhanh", "Các món ăn nhanh"},
                {"Salad", "Các món salad và rau"},
                {"Khac", "Khác"}
        };

        for (String[] cat : categoryData) {
            if (!categoryRepository.existsById(cat[0])) {
                Category c = new Category();
                c.setName(cat[0]);
                c.setDescription(cat[1]);
                categoryRepository.save(c);
                categories.add(c);
            } else {
                categories.add(categoryRepository.findById(cat[0]).get());
            }
        }

        // 2. Data mẫu cho Dish
        String[] dishNames = {
                "Phở bò tái nạm", "Bún thịt nướng", "Cơm gà xối mỡ", "Bánh mì pate", "Gỏi cuốn",
                "Chè ba màu", "Sinh tố bơ", "Bánh xèo", "Hủ tiếu", "Nem rán",
                "Cơm tấm", "Bánh canh", "Bò kho", "Mì Quảng", "Cháo sườn",
                "Salad trái cây", "Cà phê sữa đá", "Trà đào cam sả", "Smoothie dâu", "Nước ép cam"
        };

        String[] descriptions = {
                "Hương vị truyền thống, đậm đà.",
                "Món ăn thơm ngon, chuẩn vị.",
                "Ngon miệng, bổ dưỡng.",
                "Phù hợp cho bữa sáng.",
                "Được nhiều thực khách yêu thích."
        };

        String[] images = {
                "/images/pho.webp",
                "/images/comga.webp",
                "/images/bunthitnuong.webp",
                "/images/banhmi.webp",
                "/images/chebamau.webp",
                "/images/cf-suada.webp",
                "/images/tradao.webp",
                "/images/salad.webp",
                "/images/nemran.webp"
        };

        String[] tags = {"Best Seller", "Hot", "New", "Healthy Choice", null};

        Random random = new Random();

        for (int i = 0; i < 50; i++) { // 50 món
            Dish dish = new Dish();
            dish.setName(dishNames[random.nextInt(dishNames.length)] + " #" + (i + 1));
            dish.setDescription(descriptions[random.nextInt(descriptions.length)]);
            BigDecimal price = BigDecimal.valueOf((random.nextInt(150) + 50) * 1000); // 50k - 200k
            dish.setPrice(price);

            // ~30% món có oldPrice cao hơn 5k-15k
            if (random.nextDouble() < 0.3) {
                dish.setOldPrice(price.add(BigDecimal.valueOf((random.nextInt(11) + 5) * 1000)));
            }

            // rating 3.5 - 5.0
            dish.setRating(Math.round((3.5 + random.nextDouble() * 1.5) * 10.0) / 10.0);

            // thỉnh thoảng có tag
            dish.setTag(tags[random.nextInt(tags.length)]);

            dish.setStock(random.nextInt(91) + 10); // 10-100
            dish.setPreparationTime(random.nextInt(26) + 5); // 5-30 phút
            dish.setOrderCount(random.nextInt(201)); // 0-200
            dish.setImageUrl(images[random.nextInt(images.length)]);
            dish.setEnabled(true);
            dish.setCreatedAt(LocalDateTime.now());
            dish.setUpdatedAt(LocalDateTime.now());

            // Random gán 1 category
            Category category = categories.get(random.nextInt(categories.size()));
            dish.setCategories(Set.of(category));

            dishRepository.save(dish);
        }

        System.out.println("Seeded categories and dishes successfully!");
    }
}
