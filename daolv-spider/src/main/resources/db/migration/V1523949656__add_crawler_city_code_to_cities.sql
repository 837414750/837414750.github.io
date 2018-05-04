ALTER TABLE cities add column crawler_go_city_code varchar(50);
comment on column cities.crawler_go_city_code is 'go爬虫城市code';