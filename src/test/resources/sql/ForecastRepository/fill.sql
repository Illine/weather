insert into forecasts (index, forecast_time, forecast_date)
values (1, '00:00:00', current_date); -- id 1
insert into forecasts (index, forecast_time, forecast_date)
values (1, '03:00:00', current_date); -- id 2
insert into forecasts (index, forecast_time, forecast_date)
values (1, '06:00:00', current_date); -- id 3
insert into forecasts (index, forecast_time, forecast_date)
values (1, '09:00:00', current_date); -- id 4
insert into forecasts (index, forecast_time, forecast_date)
values (1, '12:00:00', current_date); -- id 5
insert into forecasts (index, forecast_time, forecast_date)
values (1, '15:00:00', current_date); -- id 6
insert into forecasts (index, forecast_time, forecast_date)
values (1, '18:00:00', current_date); -- id 7
insert into forecasts (index, forecast_time, forecast_date)
values (1, '21:00:00', current_date); -- id 8

insert into forecasts (index, forecast_time, forecast_date)
values (1, '00:00:00', current_date + interval '1 day'); -- id 9
insert into forecasts (index, forecast_time, forecast_date)
values (1, '03:00:00', current_date + interval '1 day'); -- id 10
insert into forecasts (index, forecast_time, forecast_date)
values (1, '06:00:00', current_date + interval '1 day'); -- id 11
insert into forecasts (index, forecast_time, forecast_date)
values (1, '09:00:00', current_date + interval '1 day'); -- id 12
insert into forecasts (index, forecast_time, forecast_date)
values (1, '12:00:00', current_date + interval '1 day'); -- id 13
insert into forecasts (index, forecast_time, forecast_date)
values (1, '15:00:00', current_date + interval '1 day'); -- id 14
insert into forecasts (index, forecast_time, forecast_date)
values (1, '18:00:00', current_date + interval '1 day'); -- id 15
insert into forecasts (index, forecast_time, forecast_date)
values (1, '21:00:00', current_date + interval '1 day'); -- id 16

-- deleted
insert into forecasts (index, forecast_time, forecast_date, active)
values (1, '00:00:00', current_date + interval '2 day', false); -- id 17
insert into forecasts (index, forecast_time, forecast_date, active)
values (1, '03:00:00', current_date + interval '2 day', false); -- id 18
insert into forecasts (index, forecast_time, forecast_date, active)
values (1, '06:00:00', current_date + interval '2 day', false); -- id 19
insert into forecasts (index, forecast_time, forecast_date, active)
values (1, '09:00:00', current_date + interval '2 day', false); -- id 20
insert into forecasts (index, forecast_time, forecast_date, active)
values (1, '12:00:00', current_date + interval '2 day', false); -- id 21
insert into forecasts (index, forecast_time, forecast_date, active)
values (1, '15:00:00', current_date + interval '2 day', false); -- id 22
insert into forecasts (index, forecast_time, forecast_date, active)
values (1, '18:00:00', current_date + interval '2 day', false); -- id 23
insert into forecasts (index, forecast_time, forecast_date, active)
values (1, '21:00:00', current_date + interval '2 day', false); -- id 24