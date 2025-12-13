INSERT INTO customers (name, email, phone) VALUES
('Иван Петров', 'ivan@example.com', '+79991234567'),
('Мария Сидорова', 'maria@example.com', '+79997654321'),
('Николай Иванов', 'nikolay@example.com', '+79995551234'),
('Анна Смирнова', 'anna@example.com', '+79994445678');

INSERT INTO products (name, price, stock_quantity, sku) VALUES
('ASUS VivoBook 15', 50000.00, 5, 'ASUS-VB-15'),
('LG 24 Monitor', 15000.00, 10, 'LG-MON-24'),
('Corsair K70 Keyboard', 8000.00, 15, 'COR-K70'),
('Logitech MX Master', 5000.00, 20, 'LOG-MX-M'),
('Monitor Stand', 2000.00, 30, 'STAND-001'),
('USB 3.0 Hub', 1500.00, 25, 'HUB-USB3');

INSERT INTO orders (customer_id, status, total_amount, delivery_address) VALUES
(1, 'PENDING', 1599.99, 'ул. Пушкина, д. 10, Москва'),
(2, 'CONFIRMED', 5999.99, 'пр. Ленина, д. 5, СПб'),
(3, 'SHIPPED', 3499.99, 'ул. Горького, д. 20, Казань'),
(4, 'DELIVERED', 7500.00, 'ул. Советская, д. 15, Новосибирск'),
(1, 'PENDING', 2000.00, 'ул. Пушкина, д. 10, Москва');

INSERT INTO order_items (order_id, product_id, quantity, unit_price, subtotal) VALUES
(1, 1, 1, 50000.00, 50000.00),
(1, 4, 1, 5000.00, 5000.00),
(2, 2, 1, 15000.00, 15000.00),
(2, 3, 1, 8000.00, 8000.00),
(3, 3, 1, 8000.00, 8000.00),
(3, 5, 2, 2000.00, 4000.00),
(4, 1, 1, 50000.00, 50000.00),
(4, 6, 1, 1500.00, 1500.00),
(5, 5, 1, 2000.00, 2000.00);

INSERT INTO order_status_history (order_id, old_status, new_status) VALUES
(1, NULL, 'PENDING'),
(2, NULL, 'PENDING'),
(2, 'PENDING', 'CONFIRMED'),
(3, NULL, 'PENDING'),
(3, 'PENDING', 'SHIPPED'),
(4, NULL, 'PENDING'),
(4, 'PENDING', 'CONFIRMED'),
(4, 'CONFIRMED', 'SHIPPED'),
(4, 'SHIPPED', 'DELIVERED'),
(5, NULL, 'PENDING');
