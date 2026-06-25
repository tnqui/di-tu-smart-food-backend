-- ============================================
-- DATA SEED SCRIPT - Ditu Smart Food Backend
-- ============================================

-- Disable constraints tạm thời
ALTER TABLE IF EXISTS users_permissions DISABLE TRIGGER ALL;
ALTER TABLE IF EXISTS addresses DISABLE TRIGGER ALL;
ALTER TABLE IF EXISTS notifications DISABLE TRIGGER ALL;
ALTER TABLE IF EXISTS orders DISABLE TRIGGER ALL;
ALTER TABLE IF EXISTS order_items DISABLE TRIGGER ALL;
ALTER TABLE IF EXISTS payment_transactions DISABLE TRIGGER ALL;
ALTER TABLE IF EXISTS products DISABLE TRIGGER ALL;
ALTER TABLE IF EXISTS review_ratings DISABLE TRIGGER ALL;

-- ============================================
-- 1. INSERT PERMISSIONS
-- ============================================
INSERT INTO permissions (id, name, description, created_at, deleted, deleted_at, updated_at)
VALUES
    ('550e8400-e29b-41d4-a716-446655440001', 'VIEW_PRODUCTS', 'Xem sản phẩm', NOW(), false, NULL, NOW()),
    ('550e8400-e29b-41d4-a716-446655440002', 'CREATE_ORDER', 'Tạo đơn hàng', NOW(), false, NULL, NOW()),
    ('550e8400-e29b-41d4-a716-446655440003', 'MANAGE_USERS', 'Quản lý người dùng', NOW(), false, NULL, NOW()),
    ('550e8400-e29b-41d4-a716-446655440004', 'MANAGE_PRODUCTS', 'Quản lý sản phẩm', NOW(), false, NULL, NOW()),
    ('550e8400-e29b-41d4-a716-446655440005', 'VIEW_ORDERS', 'Xem đơn hàng', NOW(), false, NULL, NOW()),
    ('550e8400-e29b-41d4-a716-446655440006', 'MANAGE_PAYMENTS', 'Quản lý thanh toán', NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 2. INSERT CATEGORIES
-- ============================================
INSERT INTO categories (id, name, description, image_url, created_at, deleted, deleted_at, updated_at)
VALUES
    ('660e8400-e29b-41d4-a716-446655440001', 'Cơm', 'Các món cơm ngon', 'https://example.com/com.jpg', NOW(), false, NULL, NOW()),
    ('660e8400-e29b-41d4-a716-446655440002', 'Phở', 'Phở Hà Nội - Sài Gòn', 'https://example.com/pho.jpg', NOW(), false, NULL, NOW()),
    ('660e8400-e29b-41d4-a716-446655440003', 'Bánh mì', 'Bánh mì thơm ngon', 'https://example.com/banh-mi.jpg', NOW(), false, NULL, NOW()),
    ('660e8400-e29b-41d4-a716-446655440004', 'Đồ uống', 'Nước ngọt, cà phê, trà', 'https://example.com/drinks.jpg', NOW(), false, NULL, NOW()),
    ('660e8400-e29b-41d4-a716-446655440005', 'Tráng miệng', 'Kem, chè, bánh ngọt', 'https://example.com/dessert.jpg', NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 3. INSERT PRODUCTS
-- ============================================
INSERT INTO products (id, name, description, image_url, price, stock, is_available, preparation_time, order_count, rating, primary_category_id, created_at, deleted, deleted_at, updated_at)
VALUES
    ('770e8400-e29b-41d4-a716-446655440001', 'Cơm gà xối mỡ', 'Cơm trắng, thịt gà xối mỡ', 'https://example.com/com-ga.jpg', 45000.00, 50, true, 15, 120, 4.5, '660e8400-e29b-41d4-a716-446655440001', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440002', 'Cơm gà roti', 'Cơm gà với nước sốt roti', 'https://example.com/com-ga-roti.jpg', 50000.00, 45, true, 15, 95, 4.3, '660e8400-e29b-41d4-a716-446655440001', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440003', 'Phở gà', 'Phở gà nước dùng đậm đà', 'https://example.com/pho-ga.jpg', 55000.00, 60, true, 12, 200, 4.7, '660e8400-e29b-41d4-a716-446655440002', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440004', 'Phở bò', 'Phở bò nấu theo công thức truyền thống', 'https://example.com/pho-bo.jpg', 65000.00, 55, true, 12, 180, 4.8, '660e8400-e29b-41d4-a716-446655440002', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440005', 'Bánh mì thịt', 'Bánh mì với pâté và thịt', 'https://example.com/banh-mi-thit.jpg', 25000.00, 100, true, 5, 150, 4.4, '660e8400-e29b-41d4-a716-446655440003', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440006', 'Cà phê đen', 'Cà phê đen đậm', 'https://example.com/ca-phe-den.jpg', 15000.00, 200, true, 3, 500, 4.6, '660e8400-e29b-41d4-a716-446655440004', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440007', 'Cà phê sữa', 'Cà phê sữa đặc sánh', 'https://example.com/ca-phe-sua.jpg', 18000.00, 180, true, 3, 480, 4.5, '660e8400-e29b-41d4-a716-446655440004', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440008', 'Kem vani', 'Kem vani mừm mịn', 'https://example.com/kem-vani.jpg', 30000.00, 80, true, 2, 90, 4.3, '660e8400-e29b-41d4-a716-446655440005', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440009', 'Chè trà xanh', 'Chè trà xanh mát lạnh', 'https://example.com/che-tra-xanh.jpg', 20000.00, 150, true, 4, 200, 4.2, '660e8400-e29b-41d4-a716-446655440005', NOW(), false, NULL, NOW()),
    ('770e8400-e29b-41d4-a716-446655440010', 'Bánh mì cá cơm', 'Bánh mì với cá cơm cay', 'https://example.com/banh-mi-ca-com.jpg', 28000.00, 120, true, 5, 130, 4.1, '660e8400-e29b-41d4-a716-446655440003', NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 4. INSERT USERS
-- ============================================
INSERT INTO users (id, email, phone, password, first_name, last_name, avatar_url, enabled, email_verified, is_phone_verified, login_attempts, two_factor_enabled, two_factor_secret, language, timezone, last_login_at, last_login_ip, roles, created_at, deleted, deleted_at, updated_at)
VALUES
    ('880e8400-e29b-41d4-a716-446655440001', 'admin@example.com', '0901234567', '$2a$12$FK04F3SvKDg.X1UeEzPireJTqVV9ZP95SotosuhL6MOgWW2oInZyK', 'Admin', 'System', 'https://example.com/admin.jpg', true, true, true, 0, false, NULL, 'vi', 'Asia/Ho_Chi_Minh', NOW(), '192.168.1.1', ARRAY['ROLE_ADMIN'], NOW(), false, NULL, NOW()),
    ('880e8400-e29b-41d4-a716-446655440002', 'user1@example.com', '0912345678', '$2a$12$FK04F3SvKDg.X1UeEzPireJTqVV9ZP95SotosuhL6MOgWW2oInZyK', 'Nguyễn', 'Văn A', 'https://example.com/user1.jpg', true, true, true, 0, false, NULL, 'vi', 'Asia/Ho_Chi_Minh', NOW(), '192.168.1.2', ARRAY['ROLE_USER'], NOW(), false, NULL, NOW()),
    ('880e8400-e29b-41d4-a716-446655440003', 'user2@example.com', '0923456789', '$2a$12$FK04F3SvKDg.X1UeEzPireJTqVV9ZP95SotosuhL6MOgWW2oInZyK', 'Trần', 'Thị B', 'https://example.com/user2.jpg', true, true, true, 0, false, NULL, 'vi', 'Asia/Ho_Chi_Minh', NOW(), '192.168.1.3', ARRAY['ROLE_USER'], NOW(), false, NULL, NOW()),
    ('880e8400-e29b-41d4-a716-446655440004', 'merchant@example.com', '0934567890', '$2a$12$FK04F3SvKDg.X1UeEzPireJTqVV9ZP95SotosuhL6MOgWW2oInZyK', 'Lê', 'Văn C', 'https://example.com/merchant.jpg', true, true, true, 0, false, NULL, 'vi', 'Asia/Ho_Chi_Minh', NOW(), '192.168.1.4', ARRAY['ROLE_MERCHANT'], NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 5. INSERT USER PERMISSIONS
-- ============================================
INSERT INTO users_permissions (user_id, permissions_id)
VALUES
    ('880e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440001'),
    ('880e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440003'),
    ('880e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440004'),
    ('880e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440006'),
    ('880e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440001'),
    ('880e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440002'),
    ('880e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440005'),
    ('880e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440001'),
    ('880e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440002'),
    ('880e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440001'),
    ('880e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440004'),
    ('880e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440005')
ON CONFLICT DO NOTHING;

-- ============================================
-- 6. INSERT ADDRESSES
-- ============================================
INSERT INTO addresses (id, user_id, country, city, district, ward, street, house_number, full_address, geocode_matched_address, latitude, longitude, is_default, created_at, deleted, deleted_at, updated_at)
VALUES
    ('990e8400-e29b-41d4-a716-446655440001', '880e8400-e29b-41d4-a716-446655440002', 'Việt Nam', 'Hồ Chí Minh', 'Quận 1', 'Phường Bến Nghé', 'Đường Nguyễn Huệ', '123', '123 Đường Nguyễn Huệ, Phường Bến Nghé, Quận 1, Hồ Chí Minh', '123 Nguyễn Huệ St', 10.7769, 106.7009, true, NOW(), false, NULL, NOW()),
    ('990e8400-e29b-41d4-a716-446655440002', '880e8400-e29b-41d4-a716-446655440002', 'Việt Nam', 'Hồ Chí Minh', 'Quận 3', 'Phường 8', 'Đường Võ Văn Tần', '456', '456 Đường Võ Văn Tần, Phường 8, Quận 3, Hồ Chí Minh', '456 Võ Văn Tần St', 10.7788, 106.6947, false, NOW(), false, NULL, NOW()),
    ('990e8400-e29b-41d4-a716-446655440003', '880e8400-e29b-41d4-a716-446655440003', 'Việt Nam', 'Hồ Chí Minh', 'Quận 7', 'Phường Phú Mỹ', 'Đường Nguyễn Lương Bằng', '789', '789 Đường Nguyễn Lương Bằng, Phường Phú Mỹ, Quận 7, Hồ Chí Minh', '789 Nguyễn Lương Bằng St', 10.7443, 106.7140, true, NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 7. INSERT ORDERS
-- ============================================
INSERT INTO orders (id, order_id, user_id, status, total_amount, shipping_fee, payment_method, payment_method_name, payment_transaction_id, delivery_method, recipient_name, recipient_phone, shipping_address, note, latitude, longitude, paid_at, created_at, deleted, deleted_at, updated_at)
VALUES
    ('aa0e8400-e29b-41d4-a716-446655440001', '25062026001', '880e8400-e29b-41d4-a716-446655440002', 'DELIVERED', 140000.00, 30000.00, 'MOMO', 'Momo', 'TRANS_001', 'GHTK', 'Nguyễn Văn A', '0912345678', '123 Đường Nguyễn Huệ, Phường Bến Nghé, Quận 1, Hồ Chí Minh', 'Giao lúc 12h trưa', 10.7769, 106.7009, NOW(), NOW() - INTERVAL '3 days', false, NULL, NOW()),
    ('aa0e8400-e29b-41d4-a716-446655440002', '25062026002', '880e8400-e29b-41d4-a716-446655440002', 'SHIPPING', 120000.00, 30000.00, 'MOMO', 'Momo', 'TRANS_002', 'GRAB', 'Nguyễn Văn A', '0912345678', '123 Đường Nguyễn Huệ, Phường Bến Nghé, Quận 1, Hồ Chí Minh', 'Giao chiều hôm nay', 10.7769, 106.7009, NOW(), NOW() - INTERVAL '1 days', false, NULL, NOW()),
    ('aa0e8400-e29b-41d4-a716-446655440003', '25062026003', '880e8400-e29b-41d4-a716-446655440003', 'CONFIRMED', 95000.00, 25000.00, 'MOMO', 'Momo', 'TRANS_003', 'BE', 'Trần Thị B', '0923456789', '789 Đường Nguyễn Lương Bằng, Phường Phú Mỹ, Quận 7, Hồ Chí Minh', 'Gọi trước khi tới', 10.7443, 106.7140, NULL, NOW(), false, NULL, NOW()),
    ('aa0e8400-e29b-41d4-a716-446655440004', '25062026004', '880e8400-e29b-41d4-a716-446655440003', 'PENDING', 155000.00, 35000.00, 'MOMO', 'Momo', NULL, 'IN_HOUSE', 'Trần Thị B', '0923456789', '789 Đường Nguyễn Lương Bằng, Phường Phú Mỹ, Quận 7, Hồ Chí Minh', NULL, 10.7443, 106.7140, NULL, NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 8. INSERT ORDER ITEMS
-- ============================================
INSERT INTO order_items (id, order_id, product_id, quantity, price_at_ordered_time, note, created_at, deleted, deleted_at, updated_at)
VALUES
    -- Order 1
    ('bb0e8400-e29b-41d4-a716-446655440001', 'aa0e8400-e29b-41d4-a716-446655440001', '770e8400-e29b-41d4-a716-446655440001', 2, 45000.00, 'Không cay', NOW(), false, NULL, NOW()),
    ('bb0e8400-e29b-41d4-a716-446655440002', 'aa0e8400-e29b-41d4-a716-446655440001', '770e8400-e29b-41d4-a716-446655440006', 1, 15000.00, NULL, NOW(), false, NULL, NOW()),

    -- Order 2
    ('bb0e8400-e29b-41d4-a716-446655440003', 'aa0e8400-e29b-41d4-a716-446655440002', '770e8400-e29b-41d4-a716-446655440003', 2, 55000.00, 'Phở chín', NOW(), false, NULL, NOW()),
    ('bb0e8400-e29b-41d4-a716-446655440004', 'aa0e8400-e29b-41d4-a716-446655440002', '770e8400-e29b-41d4-a716-446655440007', 1, 18000.00, NULL, NOW(), false, NULL, NOW()),

    -- Order 3
    ('bb0e8400-e29b-41d4-a716-446655440005', 'aa0e8400-e29b-41d4-a716-446655440003', '770e8400-e29b-41d4-a716-446655440004', 1, 65000.00, 'Phở bò tái', NOW(), false, NULL, NOW()),
    ('bb0e8400-e29b-41d4-a716-446655440006', 'aa0e8400-e29b-41d4-a716-446655440003', '770e8400-e29b-41d4-a716-446655440008', 1, 30000.00, NULL, NOW(), false, NULL, NOW()),

    -- Order 4
    ('bb0e8400-e29b-41d4-a716-446655440007', 'aa0e8400-e29b-41d4-a716-446655440004', '770e8400-e29b-41d4-a716-446655440002', 2, 50000.00, 'Cơm gà cay', NOW(), false, NULL, NOW()),
    ('bb0e8400-e29b-41d4-a716-446655440008', 'aa0e8400-e29b-41d4-a716-446655440004', '770e8400-e29b-41d4-a716-446655440005', 1, 25000.00, NULL, NOW(), false, NULL, NOW()),
    ('bb0e8400-e29b-41d4-a716-446655440009', 'aa0e8400-e29b-41d4-a716-446655440004', '770e8400-e29b-41d4-a716-446655440009', 1, 20000.00, NULL, NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 9. INSERT PAYMENT TRANSACTIONS
-- ============================================
INSERT INTO payment_transactions (id, order_id, transaction_id, provider, amount, status, callback_data, error_message, paid_at, expired_at, created_at, deleted, deleted_at, updated_at)
VALUES
    ('cc0e8400-e29b-41d4-a716-446655440001', 'aa0e8400-e29b-41d4-a716-446655440001', 'MOMO_001', 'MOMO', 170000.00, 'COMPLETED', '{"resultCode": 0, "message": "Success"}', NULL, NOW() - INTERVAL '3 days', NULL, NOW() - INTERVAL '3 days', false, NULL, NOW()),
    ('cc0e8400-e29b-41d4-a716-446655440002', 'aa0e8400-e29b-41d4-a716-446655440002', 'MOMO_002', 'MOMO', 150000.00, 'COMPLETED', '{"resultCode": 0, "message": "Success"}', NULL, NOW() - INTERVAL '1 days', NULL, NOW() - INTERVAL '1 days', false, NULL, NOW()),
    ('cc0e8400-e29b-41d4-a716-446655440003', 'aa0e8400-e29b-41d4-a716-446655440003', 'MOMO_003', 'MOMO', 120000.00, 'PENDING', NULL, NULL, NULL, NOW() + INTERVAL '1 hour', NOW(), false, NULL, NOW()),
    ('cc0e8400-e29b-41d4-a716-446655440004', 'aa0e8400-e29b-41d4-a716-446655440004', 'MOMO_004', 'MOMO', 190000.00, 'PENDING', NULL, NULL, NULL, NOW() + INTERVAL '2 hours', NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 10. INSERT REVIEW RATINGS
-- ============================================
INSERT INTO review_ratings (id, product_id, user_id, rating, comment, created_at, deleted, deleted_at, updated_at)
VALUES
    ('dd0e8400-e29b-41d4-a716-446655440001', '770e8400-e29b-41d4-a716-446655440001', '880e8400-e29b-41d4-a716-446655440002', 5, 'Cơm gà rất ngon, gà tươi, cơm dẻo', NOW() - INTERVAL '2 days', false, NULL, NOW()),
    ('dd0e8400-e29b-41d4-a716-446655440002', '770e8400-e29b-41d4-a716-446655440003', '880e8400-e29b-41d4-a716-446655440002', 5, 'Phở gà đúng vị, nước dùng đậm đà, thơm lúm lúm', NOW() - INTERVAL '2 days', false, NULL, NOW()),
    ('dd0e8400-e29b-41d4-a716-446655440003', '770e8400-e29b-41d4-a716-446655440004', '880e8400-e29b-41d4-a716-446655440003', 5, 'Phở bò tươi ngon, nước lạc lằng, topping đầy đủ', NOW(), false, NULL, NOW()),
    ('dd0e8400-e29b-41d4-a716-446655440004', '770e8400-e29b-41d4-a716-446655440006', '880e8400-e29b-41d4-a716-446655440002', 4, 'Cà phê đen đậm, tuy nhiên một chút đắng', NOW() - INTERVAL '1 days', false, NULL, NOW()),
    ('dd0e8400-e29b-41d4-a716-446655440005', '770e8400-e29b-41d4-a716-446655440008', '880e8400-e29b-41d4-a716-446655440003', 4, 'Kem mừm mịn, vị vani rõ rệt', NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- ============================================
-- 11. INSERT NOTIFICATIONS
-- ============================================
INSERT INTO notifications (id, user_id, type, status, title, content, redirect_url, read_at, created_at, deleted, deleted_at, updated_at)
VALUES
    ('ee0e8400-e29b-41d4-a716-446655440001', '880e8400-e29b-41d4-a716-446655440002', 'ORDER', 'READ', 'Đơn hàng đã giao', 'Đơn hàng 25062026001 của bạn đã giao thành công', '/orders/25062026001', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', false, NULL, NOW()),
    ('ee0e8400-e29b-41d4-a716-446655440002', '880e8400-e29b-41d4-a716-446655440002', 'ORDER', 'READ', 'Đơn hàng đang giao', 'Đơn hàng 25062026002 đang trên đường tới bạn', '/orders/25062026002', NOW() - INTERVAL '1 days', NOW() - INTERVAL '1 days', false, NULL, NOW()),
    ('ee0e8400-e29b-41d4-a716-446655440003', '880e8400-e29b-41d4-a716-446655440003', 'ORDER', 'UNREAD', 'Đơn hàng mới', 'Đơn hàng 25062026003 của bạn đã được xác nhận', '/orders/25062026003', NULL, NOW(), false, NULL, NOW()),
    ('ee0e8400-e29b-41d4-a716-446655440004', '880e8400-e29b-41d4-a716-446655440002', 'PROMO', 'UNREAD', 'Khuyến mãi mới', 'Giảm 20% cho đơn hàng tiếp theo, áp dụng cho cơm gà', NULL, NULL, NOW(), false, NULL, NOW()),
    ('ee0e8400-e29b-41d4-a716-446655440005', '880e8400-e29b-41d4-a716-446655440003', 'SYSTEM', 'UNREAD', 'Cập nhật hệ thống', 'App đã được cập nhật lên phiên bản 2.0', NULL, NULL, NOW(), false, NULL, NOW())
ON CONFLICT DO NOTHING;

-- Re-enable constraints
ALTER TABLE IF EXISTS users_permissions ENABLE TRIGGER ALL;
ALTER TABLE IF EXISTS addresses ENABLE TRIGGER ALL;
ALTER TABLE IF EXISTS notifications ENABLE TRIGGER ALL;
ALTER TABLE IF EXISTS orders ENABLE TRIGGER ALL;
ALTER TABLE IF EXISTS order_items ENABLE TRIGGER ALL;
ALTER TABLE IF EXISTS payment_transactions ENABLE TRIGGER ALL;
ALTER TABLE IF EXISTS products ENABLE TRIGGER ALL;
ALTER TABLE IF EXISTS review_ratings ENABLE TRIGGER ALL;

-- ============================================
-- SUMMARY
-- ============================================
-- ✅ 6 Permissions
-- ✅ 5 Categories
-- ✅ 10 Products
-- ✅ 4 Users
-- ✅ 3 Addresses
-- ✅ 4 Orders + 9 Order Items
-- ✅ 4 Payment Transactions
-- ✅ 5 Review Ratings
-- ✅ 5 Notifications
-- ✅ 12 User-Permission mappings