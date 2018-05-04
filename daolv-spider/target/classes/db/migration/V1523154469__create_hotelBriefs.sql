CREATE TABLE dida_hotel_briefs(
  id uuid primary key,
  hotel_id uuid,
  lowest_price float,
  is_present boolean, 
  date date,
  extra jsonb,
  created_at timestamp,
  updated_at timestamp
);
comment on column dida_hotel_briefs.id is '酒店概要id';
comment on column dida_hotel_briefs.hotel_id is '酒店id';
comment on column dida_hotel_briefs.lowest_price is '最低价格';
comment on column dida_hotel_briefs.is_present is '是否可预定';
comment on column dida_hotel_briefs.date is '日期';
comment on column dida_hotel_briefs.extra is '额外信息';
comment on column dida_hotel_briefs.created_at is '创建时间';
comment on column dida_hotel_briefs.updated_at is '修改时间';
