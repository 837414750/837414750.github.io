CREATE TABLE dida_hotels(
  id uuid primary key,
  id_name varchar(50),
  address varchar(200),
  city_code varchar(20), 
  city_name varchar(50),
  country_code varchar(20),
  country_name varchar(50),
  zip_code varchar(20),
  lngitude varchar(20),
  latitude varchar(20),
  star_rating varchar(6),
  tele_phone varchar(11),
  destination_id integer,
  destination_name varchar(50),
  lowest_price float, 
  web_set varchar(50),
  info varchar(200),
  images text[],
  extra jsonb,
  created_at timestamp,
  updated_at timestamp
);

comment on column dida_hotels.id is '酒店id';
comment on column dida_hotels.id_name is '酒店名称';
comment on column dida_hotels.address is '地址';
comment on column dida_hotels.city_code is '城市编码';
comment on column dida_hotels.city_name is '城市名称';
comment on column dida_hotels.country_code is '国家编码';
comment on column dida_hotels.country_name is '国家名称';
comment on column dida_hotels.zip_code is '邮编';
comment on column dida_hotels.lngitude is 'Gps坐标lng';
comment on column dida_hotels.latitude is 'GPS坐标lat';
comment on column dida_hotels.star_rating is '星级';
comment on column dida_hotels.tele_phone is '电话';
comment on column dida_hotels.destination_id is '分区Id';
comment on column dida_hotels.destination_name is '分区名称，如“芭媞雅（及其周边地区）”';
comment on column dida_hotels.lowest_price  is '最低价格';
comment on column dida_hotels.web_set is '网址';
comment on column dida_hotels.info is '基本介绍';
comment on column dida_hotels.images is '图片';
comment on column dida_hotels.extra is '额外信息';
comment on column dida_hotels.created_at is '创建时间';
comment on column dida_hotels.updated_at is '修改时间';
