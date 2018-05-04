CREATE TABLE dida_rate_plan_prices (
  id bigserial NOT NULL primary key,
  name varchar(50),
  dida_rate_plan_id bigint,
  dida_room_status_id uuid,
  origin_price float,
  current_price float
);

comment on column dida_rate_plan_prices.name is '名称';
comment on column dida_rate_plan_prices.dida_rate_plan_id is '包含服务';
comment on column dida_rate_plan_prices.dida_room_status_id is '房态id';
comment on column dida_rate_plan_prices.origin_price is '设计价格';
comment on column dida_rate_plan_prices.current_price is '当前价格';