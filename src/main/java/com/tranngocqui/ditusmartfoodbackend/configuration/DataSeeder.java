package com.tranngocqui.ditusmartfoodbackend.configuration;

import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import com.tranngocqui.ditusmartfoodbackend.entity.MenuItem;
import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.MenuItemRepository;
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
    private final MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) throws Exception {

        // 1. Tạo category với ảnh minh họa
        Map<String, String> categoryData = Map.of(
                "MonChinh", "Các món chính",
                "MonTrangMieng", "Các món tráng miệng",
                "DoUong", "Các loại đồ uống",
                "MonAnNhanh", "Các món ăn nhanh",
                "Salad", "Các món salad và rau",
                "Khac", "Khác"
        );

        Map<String, String> categoryImages = Map.of(
                "MonChinh", "https://source.unsplash.com/featured/?vietnamese,food",
                "MonTrangMieng", "https://source.unsplash.com/featured/?dessert",
                "DoUong", "https://source.unsplash.com/featured/?drink",
                "MonAnNhanh", "https://source.unsplash.com/featured/?fastfood",
                "Salad", "https://source.unsplash.com/featured/?salad",
                "Khac", "https://source.unsplash.com/featured/?snack"
        );

        Map<String, Category> categories = new HashMap<>();
        for (var entry : categoryData.entrySet()) {
            String key = entry.getKey();
            String description = entry.getValue();

            Category c = categoryRepository.findByName(key).orElseGet(() -> {
                Category cat = new Category();
                cat.setName(key);
                cat.setDescription(description);
                cat.setImageUrl(categoryImages.getOrDefault(key,
                        "https://picsum.photos/seed/" + key + "/800/400"));
                return categoryRepository.save(cat);
            });

            categories.put(key, c);
        }

        // 2. Dữ liệu món ăn
        Map<String, List<MenuItemData>> menuItemsByCategory = Map.of(
                "MonChinh", List.of(
                        new MenuItemData("Phở bò tái nạm", 85000, 4.8, true, 150, 15,
                                "https://images.unsplash.com/photo-1604908177522-040f63e2cddd"),
                        new MenuItemData("Bún thịt nướng", 75000, 4.5, true, 120, 12,
                                "https://images.unsplash.com/photo-1617196039714-7ecf81c8b6e4"),
                        new MenuItemData("Cơm gà xối mỡ", 65000, 4.6, false, 95, 10,
                                "https://source.unsplash.com/featured/?fried,chicken,rice"),
                        new MenuItemData("Bò kho", 70000, 4.3, false, 80, 20,
                                "https://source.unsplash.com/featured/?beef,stew"),
                        new MenuItemData("Mì Quảng", 80000, 4.7, true, 110, 18,
                                "https://source.unsplash.com/featured/?noodle,soup"),
                        new MenuItemData("Cơm tấm", 60000, 4.4, false, 140, 8,
                                "https://source.unsplash.com/featured/?broken,rice"),
                        new MenuItemData("Bánh canh", 55000, 4.2, false, 75, 15,
                                "https://source.unsplash.com/featured/?thick,noodle"),
                        new MenuItemData("Cháo sườn", 50000, 4.1, false, 60, 12,
                                "https://source.unsplash.com/featured/?porridge")
                ),
                "MonTrangMieng", List.of(
                        new MenuItemData("Chè ba màu", 25000, 4.3, false, 45, 5,
                                "https://source.unsplash.com/featured/?sweet,beans"),
                        new MenuItemData("Bánh flan", 30000, 4.4, true, 35, 3,
                                "https://images.unsplash.com/photo-1617195739714-7ecf81c8b6e4"),
                        new MenuItemData("Bánh xèo", 40000, 4.6, false, 25, 12,
                                "https://source.unsplash.com/featured/?vietnamese,pancake"),
                        new MenuItemData("Bánh ngọt", 35000, 4.2, false, 30, 8,
                                "https://source.unsplash.com/featured/?cake")
                ),
                "DoUong", List.of(
                        new MenuItemData("Cà phê sữa đá", 20000, 4.7, true, 200, 3,
                                "https://images.unsplash.com/photo-1527169402691-feff5539e52c"),
                        new MenuItemData("Trà đào cam sả", 25000, 4.5, true, 180, 5,
                                "https://source.unsplash.com/featured/?peach,tea"),
                        new MenuItemData("Sinh tố bơ", 35000, 4.4, false, 90, 4,
                                "https://source.unsplash.com/featured/?avocado,smoothie"),
                        new MenuItemData("Smoothie dâu", 40000, 4.6, false, 70, 4,
                                "https://source.unsplash.com/featured/?strawberry,smoothie"),
                        new MenuItemData("Nước ép cam", 30000, 4.3, false, 85, 3,
                                "https://source.unsplash.com/featured/?orange,juice")
                ),
                "MonAnNhanh", List.of(
                        new MenuItemData("Bánh mì pate", 20000, 4.5, true, 220, 2,
                                "https://images.unsplash.com/photo-1606978688230-174ebb5dcf4f"),
                        new MenuItemData("Nem rán", 15000, 4.2, false, 150, 8,
                                "https://source.unsplash.com/featured/?spring,roll"),
                        new MenuItemData("Gà rán", 45000, 4.7, true, 160, 10,
                                "https://source.unsplash.com/featured/?fried,chicken"),
                        new MenuItemData("Khoai tây chiên", 25000, 4.1, false, 130, 5,
                                "https://source.unsplash.com/featured/?french,fries")
                ),
                "Salad", List.of(
                        new MenuItemData("Salad trái cây", 35000, 4.4, false, 40, 5,
                                "https://images.unsplash.com/photo-1572449043416-55f4685c9bbf"),
                        new MenuItemData("Salad rau trộn", 30000, 4.3, false, 35, 3,
                                "https://images.unsplash.com/photo-1546069901-eacef0df6022"),
                        new MenuItemData("Salad Caesar", 50000, 4.6, true, 25, 8,
                                "https://source.unsplash.com/featured/?caesar,salad")
                ),
                "Khac", List.of(
                        new MenuItemData("Snack", 10000, 3.8, false, 80, 1,
                                "https://source.unsplash.com/featured/?snack"),
                        new MenuItemData("Trái cây theo mùa", 25000, 4.0, false, 50, 2,
                                "https://source.unsplash.com/featured/?fruit")
                )
        );

        Random random = new Random();
        int displayOrder = 1;
        int repeatCount = 3;

        for (var entry : menuItemsByCategory.entrySet()) {
            String categoryKey = entry.getKey();
            Category category = categories.get(categoryKey);

            for (MenuItemData itemData : entry.getValue()) {
                for (int i = 1; i <= repeatCount; i++) {
                    MenuItem menuItem = new MenuItem();
                    String suffix = " #" + i;

                    menuItem.setName(itemData.name + suffix);
                    menuItem.setDescription("Món " + itemData.name + " phiên bản " + i +
                            " được chế biến từ nguyên liệu tươi ngon.");

                    int randomPriceOffset = random.nextInt(10000) - 5000;
                    int finalPrice = Math.max(itemData.basePrice + randomPriceOffset, 10000);
                    menuItem.setPrice(BigDecimal.valueOf(finalPrice));

                    if (random.nextDouble() < 0.3) {
                        menuItem.setOldPrice(BigDecimal.valueOf(finalPrice + random.nextInt(20000) + 10000));
                    }

                    menuItem.setRating(Math.round((itemData.rating + (random.nextDouble() - 0.5) * 0.4) * 10.0) / 10.0);
                    menuItem.setIsFeatured(itemData.isFeatured);

                    if (itemData.isFeatured) {
                        String[] featuredTags = {"Best Seller", "Hot", "Chef's Choice"};
                        menuItem.setTag(featuredTags[random.nextInt(featuredTags.length)]);
                    } else {
                        String[] normalTags = {"New", "Healthy Choice", null, null};
                        menuItem.setTag(normalTags[random.nextInt(normalTags.length)]);
                    }

                    menuItem.setStock(itemData.baseOrderCount / 2 + random.nextInt(20) + 10);
                    menuItem.setPreparationTime(itemData.prepTime + random.nextInt(5));
                    menuItem.setOrderCount(itemData.baseOrderCount + random.nextInt(50));
                    menuItem.setDisplayOrder(itemData.isFeatured ? displayOrder : displayOrder + 1000);
                    displayOrder++;

                    // Ảnh minh họa public
                    menuItem.setImageUrl(itemData.imageUrl);

                    menuItem.setEnabled(true);
                    menuItem.setIsAvailable(menuItem.getStock() > 0);

                    LocalDateTime createdTime = LocalDateTime.now().minusDays(random.nextInt(30));
                    menuItem.setCreatedAt(createdTime);
                    menuItem.setUpdatedAt(createdTime.plusDays(random.nextInt(5)));

                    menuItem.setPrimaryCategory(category);
                    menuItem.setCategories(Set.of(category));

                    menuItemRepository.save(menuItem);
                }
            }
        }

        System.out.println("✅ Seeded " + menuItemRepository.count() + " menu items across " + categoryRepository.count() + " categories!");
    }

    // Inner class
    private static class MenuItemData {
        String name;
        int basePrice;
        double rating;
        boolean isFeatured;
        int baseOrderCount;
        int prepTime;
        String imageUrl;

        public MenuItemData(String name, int basePrice, double rating, boolean isFeatured,
                            int baseOrderCount, int prepTime, String imageUrl) {
            this.name = name;
            this.basePrice = basePrice;
            this.rating = rating;
            this.isFeatured = isFeatured;
            this.baseOrderCount = baseOrderCount;
            this.prepTime = prepTime;
            this.imageUrl = imageUrl;
        }
    }
}
