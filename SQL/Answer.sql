-- POSTGRESQL
-- Update Customer Phone Number
UPDATE tblSubscriptionInfo
SET customer_contact_phone = 'XXXXXXX'
WHERE customer_id = XXXXXXX;

/*
The update query bottleneck might be because a customer can have multiple records in tblSubscriptionInfo.
Hence, it needs to update multiple records if a customer phone number is changed.
The solution might be to normalize tblSubscriptionInfo table 
by taking out the customer info and store it in the customer table.
In this way, a single record per customer needs to be updated only.
*/

-- 1. number of subscribers whose subscriptions will be ending in 2023;
SELECT COUNT(customer_id) FROM tblSubscriptionInfo
WHERE EXTRACT(YEAR FROM subscription_end_date) = 2023; 
-- 2. number of subscribers who have subscribed for more than 3 months in 2022;
SELECT COUNT(customer_id) FROM tblSubscriptionInfo
WHERE subscription_start_date < timestamp '2022-10-01 00:00:00' AND subscription_end_date >= timestamp '2022-04-01 00:00:00' AND 
        (EXTRACT(YEAR FROM AGE(subscription_end_date, subscription_start_date)) * 12 + EXTRACT(MONTH FROM AGE(subscription_end_date, subscription_start_date))) > 3;
-- 3. subscribers who have subscribed for more than two products;
SELECT customer_id, customer_contact_phone, customer_name, customer_address FROM tblSubscriptionInfo
GROUP BY customer_id, customer_contact_phone, customer_name, customer_address
HAVING COUNT(product_id) > 2; 
-- 4. product with the most/2ndmost/3rdmost number of subscribers in 2022;
SELECT product_id, product_name FROM tblSubscriptionInfo
GROUP BY product_id, product_name
ORDER BY COUNT(customer_id) DESC; 
-- 5. number of subscribers who have re-subscribed more than once for each product;
SELECT product_id, product_name, COUNT(DISTINCT customer_id) FROM tblSubscriptionInfo
GROUP BY product_id, product_name, customer_id
HAVING COUNT(subscription_id) > 1; 
-- 6. subscribers who have re-subscribed a higher version of the product in 2023 - for example Autocad 2022 to Autocad 2023.
SELECT customer_id, customer_contact_phone, customer_name, customer_address, product_id, product_name 
FROM tblSubscriptionInfo
GROUP BY customer_id, customer_contact_phone, customer_name, customer_address, product_id, product_name
HAVING COUNT(DISTINCT EXTRACT(YEAR FROM subscription_start_date)) > 1; 