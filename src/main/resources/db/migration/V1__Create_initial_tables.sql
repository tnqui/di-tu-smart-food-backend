CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  full_name VARCHAR(255),
  email VARCHAR(100) UNIQUE,
  phone VARCHAR(20) UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  is_email_verified BOOLEAN DEFAULT FALSE,
  is_phone_verified BOOLEAN DEFAULT FALSE,
  account_status BOOLEAN DEFAULT TRUE,
  login_attempts INT DEFAULT 0,
  last_login_at TIMESTAMP,
  last_login_ip VARCHAR(255),
  avatar_url VARCHAR(1000),
  language VARCHAR(10) DEFAULT 'vi',
  timezone VARCHAR(50) DEFAULT 'Asia/Ho_Chi_Minh',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) UNIQUE NOT NULL, -- ADMIN, CUSTOMER, STAFF
  label VARCHAR(100),
  description VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_roles (
  id SERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
  role_id INT REFERENCES roles(id) ON DELETE CASCADE,
  UNIQUE (user_id, role_id)
);


CREATE TABLE categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) UNIQUE NOT NULL,
  description VARCHAR(1000)
);


CREATE TABLE dishes (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000),
  price NUMERIC(10, 2) NOT NULL,
  image_url VARCHAR(1000),
  category_id INT REFERENCES categories(id),
  is_available BOOLEAN DEFAULT TRUE,
  discount_percent INT DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
  total_amount NUMERIC(10, 2) NOT NULL,
  status VARCHAR(50),  -- pending, paid, cancelled, delivered
  note VARCHAR(1000),
  delivery_address VARCHAR(1000) NOT NULL,
  payment_method VARCHAR(50),  -- momo, cod, zalo
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
  id SERIAL PRIMARY KEY,
  order_id INT REFERENCES orders(id) ON DELETE CASCADE,
  dish_id INT REFERENCES dishes(id),
  quantity INT NOT NULL,
  price_at_order_time NUMERIC(10, 2) NOT NULL,
  UNIQUE (order_id, dish_id)
);

CREATE TABLE notifications (
  id SERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
  title VARCHAR(255) NOT NULL,
  content VARCHAR(1000) NOT NULL,
  type VARCHAR(50) DEFAULT 'ORDER',  -- system, order, promo, payment
  status VARCHAR(20) DEFAULT 'UNREAD',
  redirect_url VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  read_at TIMESTAMP
);

CREATE TABLE payment_transactions (
  id SERIAL PRIMARY KEY,
  order_id INT REFERENCES orders(id) ON DELETE CASCADE,
  provider VARCHAR(50),  -- momo, zalopay
  transaction_id VARCHAR(100) UNIQUE,
  amount NUMERIC(10,2),
  status VARCHAR(50),  -- success, failed, pending
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE review_ratings (
  id SERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
  dish_id INT REFERENCES dishes(id) ON DELETE CASCADE,
  rating INT CHECK (rating BETWEEN 1 AND 5),
  comment varchar(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE(user_id, dish_id)
);