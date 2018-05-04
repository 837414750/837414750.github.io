CREATE TABLE dida_room_types(
  hotel_id uuid,
  name varchar(20),
  id uuid primary key,
  room_status integer,
  breakfast_type integer,
  bed_type integer,
  default_price float,
  info varchar(100),
  is_free_web boolean, 
  extra jsonb,
  created_at timestamp,
  updated_at timestamp
);

comment on column dida_room_types.hotel_id is '酒店id';
comment on column dida_room_types.name is '名称';
comment on column dida_room_types.id is '房间类型id ROOM_type_id';
comment on column dida_room_types.room_status is '房间类型';
comment on column dida_room_types.breakfast_type is '早餐类型';
comment on column dida_room_types.bed_type is '床型';
comment on column dida_room_types.default_price is '默认价格';
comment on column dida_room_types.info is '基本简介';
comment on column dida_room_types.is_free_web is '是否免费网络';
comment on column dida_room_types.extra is '额外信息';
comment on column dida_room_types.created_at is '创建时间';
comment on column dida_room_types.updated_at is '修改时间';
