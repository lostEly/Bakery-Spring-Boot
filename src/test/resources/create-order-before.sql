insert into status(status_id, status_name) values
(1, 'Complete'),
(2, 'New'),
(3, 'Ongoing'),
(4, 'Cancelled');

insert into orderr(order_id, date_of_completion, date_of_order,
total_price, status_id, userr_id) values
(1, DATE '2015-12-17', DATE '2015-12-16', 555, 2, 3);