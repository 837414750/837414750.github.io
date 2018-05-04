ALTER TABLE dida_hotel_briefs add column origin_price float;
ALTER TABLE dida_room_status add column origin_price float;
comment on column dida_hotel_briefs.origin_price is '原价';
comment on column dida_room_status.origin_price is '原价';