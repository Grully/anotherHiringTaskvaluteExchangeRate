Сервис, который принимает дату в запросе вида localhost:///get-rate-xml?date=01/01/2021 парсит полученный XML и кладет его в базу Postgres со следующими таблицами: 
CREATE TABLE IF NOT EXISTS public.currency_exchange
(
    id integer NOT NULL DEFAULT nextval('valute_id_seq'::regclass),
    date date,
    CONSTRAINT currency_exchange_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.valute
(
    id integer NOT NULL DEFAULT nextval('valute_id_seq'::regclass),
    num_code character varying COLLATE pg_catalog."default",
    char_code character varying COLLATE pg_catalog."default",
    nominal integer,
    name character varying COLLATE pg_catalog."default",
    value real,
    vunit_rate real,
    currency_exchange_id integer NOT NULL DEFAULT nextval('valute_currency_exchange_id_seq'::regclass),
    CONSTRAINT id PRIMARY KEY (id),
    CONSTRAINT currency_exchange FOREIGN KEY (currency_exchange_id)
        REFERENCES public.currency_exchange (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

обратите внимание, что старые значения валют НЕ стираются, а просто кладутся поверх старых, первая таблица просто хранит дату, которую мы запросили.
