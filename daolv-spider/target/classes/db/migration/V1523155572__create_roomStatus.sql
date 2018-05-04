CREATE TABLE dida_room_status(
  hotel_id uuid,
  room_type_id uuid,
  id uuid primary key,
  date date,
  price float,
  count integer,
  is_present boolean,
  extra jsonb,
  created_at timestamp,
  updated_at timestamp
);

comment on column dida_room_status.hotel_id is '酒店id';
comment on column dida_room_status.id is '房态id,room_status_id';
comment on column dida_room_status.date is '日期';
comment on column dida_room_status.price is '价格';
comment on column dida_room_status.count is '可定数量';
comment on column dida_room_status.is_present is '是否可订';
comment on column dida_room_status.extra is '额外信息';
comment on column dida_room_status.created_at is '创建时间';
comment on column dida_room_status.updated_at is '修改时间';