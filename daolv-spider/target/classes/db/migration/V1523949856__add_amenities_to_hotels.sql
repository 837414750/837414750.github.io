ALTER TABLE dida_hotels add column amenities text[];
comment on column dida_hotels.amenities is '酒店服务';
ALTER TABLE dida_hotels add column check_in_time varchar(20);
comment on column dida_hotels.check_in_time is '入住时间';
ALTER TABLE dida_hotels add column check_out_time varchar(20);
comment on column dida_hotels.check_out_time is '离店时间';
ALTER TABLE dida_hotel_briefs add column available_rooms integer;
comment on column dida_hotel_briefs.available_rooms is '可定数量';