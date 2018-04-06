-- Q1a. lists all truck types from naive query table
select * from rentaltrucks.truck_types;

-- Q1. lists all truck types from search-optimized table
select * from rentaltrucks.truck_types_by_location_and_date ;

-- Q1. lists all truck types from search-optimized table for partition key
select * from rentaltrucks.truck_types_by_location_and_date where pickup_city = 'Chicago' and pickup_state = 'IL' and pickup_date = '2018-03-01';

