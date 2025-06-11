-- 확장: UUID 생성용
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ENUM 타입 정의
CREATE TYPE user_role AS ENUM ('ADMIN', 'CUSTOMER');
CREATE TYPE order_status AS ENUM ('PENDING', 'PAID', 'SHIPPED', 'DELIVERED', 'CANCELLED');

-- users 테이블
CREATE TABLE users (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(10),
    phonenumber VARCHAR(11),
    email VARCHAR(40),
    nickname VARCHAR(10),
    role user_role
);

-- products 테이블
CREATE TABLE products (
    product_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(20),
    price INT,
    info TEXT,
    stock INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- orders 테이블
CREATE TABLE orders (
    order_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(user_id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status order_status,
    total_price INT
);

-- order_details 테이블
CREATE TABLE order_details (
    detail_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id UUID REFERENCES products(product_id),
    order_id UUID REFERENCES orders(order_id),
    unit_price INT,
    quantity INT
);
