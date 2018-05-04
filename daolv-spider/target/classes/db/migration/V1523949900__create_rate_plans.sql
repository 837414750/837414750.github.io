CREATE TABLE dida_rate_plans (
  id bigserial NOT NULL primary key,
  rate_plan_name varchar(30),
  amenities text[],
  with_breakfest boolean,
  room_type_id uuid,
  extra jsonb
);

comment on column dida_rate_plans.rate_plan_name is '价格体系名称';
comment on column dida_rate_plans.amenities is '包含服务';
comment on column dida_rate_plans.with_breakfest is '是否带早餐';
comment on column dida_rate_plans.room_type_id is '房型id';
comment on column dida_rate_plans.extra is '附加信息';