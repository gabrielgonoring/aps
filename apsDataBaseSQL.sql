--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.12
-- Dumped by pg_dump version 9.6.12

-- Started on 2019-05-19 14:56:46

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'WIN1252';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2223 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 190 (class 1259 OID 16571)
-- Name: tb_caixa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_caixa (
    id_caixa integer NOT NULL,
    nm_caixa character varying(30) NOT NULL,
    fg_ativo boolean
);


ALTER TABLE public.tb_caixa OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16569)
-- Name: tb_caixa_id_caixa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_caixa_id_caixa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_caixa_id_caixa_seq OWNER TO postgres;

--
-- TOC entry 2224 (class 0 OID 0)
-- Dependencies: 189
-- Name: tb_caixa_id_caixa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_caixa_id_caixa_seq OWNED BY public.tb_caixa.id_caixa;


--
-- TOC entry 194 (class 1259 OID 16592)
-- Name: tb_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_compra (
    id_compra integer NOT NULL,
    id_forma_pagamento integer NOT NULL,
    id_caixa integer NOT NULL,
    data_compra date NOT NULL,
    id_empregado integer
);


ALTER TABLE public.tb_compra OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 16590)
-- Name: tb_compra_id_compra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_compra_id_compra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_compra_id_compra_seq OWNER TO postgres;

--
-- TOC entry 2225 (class 0 OID 0)
-- Dependencies: 193
-- Name: tb_compra_id_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_compra_id_compra_seq OWNED BY public.tb_compra.id_compra;


--
-- TOC entry 188 (class 1259 OID 16543)
-- Name: tb_empregado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_empregado (
    id_empregado integer NOT NULL,
    id_funcao integer NOT NULL,
    id_supervisor integer,
    nm_empregado character varying(120) NOT NULL,
    cpf character varying(11) NOT NULL,
    telefone character varying(11),
    data_entrada date NOT NULL,
    cidade character varying(30) NOT NULL,
    estado character varying(15) NOT NULL,
    rua character varying(120) NOT NULL,
    bairro character varying(60) NOT NULL,
    num_endereco character varying(10) NOT NULL,
    fg_ativo boolean,
    senha character varying(20),
    usuario character varying(30),
    cep character varying(8) NOT NULL
);


ALTER TABLE public.tb_empregado OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16541)
-- Name: tb_empregado_id_empregado_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_empregado_id_empregado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_empregado_id_empregado_seq OWNER TO postgres;

--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 187
-- Name: tb_empregado_id_empregado_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_empregado_id_empregado_seq OWNED BY public.tb_empregado.id_empregado;


--
-- TOC entry 192 (class 1259 OID 16584)
-- Name: tb_forma_pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_forma_pagamento (
    id_forma_pagamento integer NOT NULL,
    desc_forma_pagamento character varying(60),
    fg_ativo boolean,
    formapagamento_id_forma_pagamento integer
);


ALTER TABLE public.tb_forma_pagamento OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16582)
-- Name: tb_forma_pagamento_id_forma_pagamento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_forma_pagamento_id_forma_pagamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_forma_pagamento_id_forma_pagamento_seq OWNER TO postgres;

--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 191
-- Name: tb_forma_pagamento_id_forma_pagamento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_forma_pagamento_id_forma_pagamento_seq OWNED BY public.tb_forma_pagamento.id_forma_pagamento;


--
-- TOC entry 186 (class 1259 OID 16534)
-- Name: tb_funcao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_funcao (
    id_funcao integer NOT NULL,
    nm_funcao character varying(60) NOT NULL,
    desc_funcao character varying(60),
    salario_funcao numeric(7,2) NOT NULL,
    fg_ativo boolean,
    CONSTRAINT tb_funcao_salario_funcao_check CHECK ((salario_funcao > (0)::numeric))
);


ALTER TABLE public.tb_funcao OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16532)
-- Name: tb_funcao_id_funcao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_funcao_id_funcao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_funcao_id_funcao_seq OWNER TO postgres;

--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 185
-- Name: tb_funcao_id_funcao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_funcao_id_funcao_seq OWNED BY public.tb_funcao.id_funcao;


--
-- TOC entry 200 (class 1259 OID 16651)
-- Name: tb_item_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_item_compra (
    id_item_compra integer NOT NULL,
    id_produto integer NOT NULL,
    id_compra integer NOT NULL,
    valor_item numeric(7,2) NOT NULL,
    quantidade_item integer NOT NULL,
    CONSTRAINT tb_item_compra_quantidade_item_check CHECK ((quantidade_item > 0)),
    CONSTRAINT tb_item_compra_valor_item_check CHECK ((valor_item >= (0)::numeric))
);


ALTER TABLE public.tb_item_compra OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16649)
-- Name: tb_item_compra_id_item_compra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_item_compra_id_item_compra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_item_compra_id_item_compra_seq OWNER TO postgres;

--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 199
-- Name: tb_item_compra_id_item_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_item_compra_id_item_compra_seq OWNED BY public.tb_item_compra.id_item_compra;


--
-- TOC entry 198 (class 1259 OID 16631)
-- Name: tb_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_produto (
    id_produto integer NOT NULL,
    id_tipo_produto integer NOT NULL,
    valor_produto numeric(7,2) NOT NULL,
    quantidade_estoque integer NOT NULL,
    desc_produto character varying(60),
    fg_ativo boolean,
    CONSTRAINT tb_produto_quantidade_estoque_check CHECK ((quantidade_estoque >= 0)),
    CONSTRAINT tb_produto_valor_produto_check CHECK ((valor_produto > (0)::numeric))
);


ALTER TABLE public.tb_produto OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16629)
-- Name: tb_produto_id_produto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_produto_id_produto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_produto_id_produto_seq OWNER TO postgres;

--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 197
-- Name: tb_produto_id_produto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_produto_id_produto_seq OWNED BY public.tb_produto.id_produto;


--
-- TOC entry 196 (class 1259 OID 16623)
-- Name: tb_tipo_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_tipo_produto (
    id_tipo_produto integer NOT NULL,
    nm_tipo_produto character varying(25) NOT NULL,
    desc_tipo_produto character varying(60),
    fg_ativo boolean
);


ALTER TABLE public.tb_tipo_produto OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 16621)
-- Name: tb_tipo_produto_id_tipo_produto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tb_tipo_produto_id_tipo_produto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_tipo_produto_id_tipo_produto_seq OWNER TO postgres;

--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 195
-- Name: tb_tipo_produto_id_tipo_produto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tb_tipo_produto_id_tipo_produto_seq OWNED BY public.tb_tipo_produto.id_tipo_produto;


--
-- TOC entry 2046 (class 2604 OID 16574)
-- Name: tb_caixa id_caixa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_caixa ALTER COLUMN id_caixa SET DEFAULT nextval('public.tb_caixa_id_caixa_seq'::regclass);


--
-- TOC entry 2048 (class 2604 OID 16595)
-- Name: tb_compra id_compra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_compra ALTER COLUMN id_compra SET DEFAULT nextval('public.tb_compra_id_compra_seq'::regclass);


--
-- TOC entry 2045 (class 2604 OID 16546)
-- Name: tb_empregado id_empregado; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_empregado ALTER COLUMN id_empregado SET DEFAULT nextval('public.tb_empregado_id_empregado_seq'::regclass);


--
-- TOC entry 2047 (class 2604 OID 16587)
-- Name: tb_forma_pagamento id_forma_pagamento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_forma_pagamento ALTER COLUMN id_forma_pagamento SET DEFAULT nextval('public.tb_forma_pagamento_id_forma_pagamento_seq'::regclass);


--
-- TOC entry 2043 (class 2604 OID 16537)
-- Name: tb_funcao id_funcao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_funcao ALTER COLUMN id_funcao SET DEFAULT nextval('public.tb_funcao_id_funcao_seq'::regclass);


--
-- TOC entry 2053 (class 2604 OID 16654)
-- Name: tb_item_compra id_item_compra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_item_compra ALTER COLUMN id_item_compra SET DEFAULT nextval('public.tb_item_compra_id_item_compra_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 16634)
-- Name: tb_produto id_produto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_produto ALTER COLUMN id_produto SET DEFAULT nextval('public.tb_produto_id_produto_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 16626)
-- Name: tb_tipo_produto id_tipo_produto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_tipo_produto ALTER COLUMN id_tipo_produto SET DEFAULT nextval('public.tb_tipo_produto_id_tipo_produto_seq'::regclass);


--
-- TOC entry 2205 (class 0 OID 16571)
-- Dependencies: 190
-- Data for Name: tb_caixa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_caixa (id_caixa, nm_caixa, fg_ativo) FROM stdin;
4	Caixa 1	t
5	caixa texte	t
8	caixa não masi do gabriel	f
7	caixa do gabriel	f
9	teste	f
10	testeApos	f
12	sdasd asdasda sadas	f
11	teste sem empregadodsd	f
13	testttt111	f
14	1sa	t
15	caixa13	t
16	222222222222222222222222222222	t
17	Caixa teste	t
18	123123	f
\.


--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 189
-- Name: tb_caixa_id_caixa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_caixa_id_caixa_seq', 18, true);


--
-- TOC entry 2209 (class 0 OID 16592)
-- Dependencies: 194
-- Data for Name: tb_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_compra (id_compra, id_forma_pagamento, id_caixa, data_compra, id_empregado) FROM stdin;
3	9	4	2017-01-01	12
4	10	4	2017-01-02	12
5	11	4	2018-01-03	12
6	14	5	2019-05-08	12
9	9	4	2019-05-13	12
10	10	5	2019-05-13	12
11	12	4	2019-05-14	12
12	11	5	2019-05-14	12
16	9	4	2019-05-14	12
17	10	5	2019-05-14	12
18	12	5	2019-05-14	12
20	9	4	2019-05-14	12
21	9	4	2019-05-16	12
22	9	14	2019-05-16	12
23	11	5	2019-05-16	35
24	25	16	2019-05-17	35
25	25	16	2019-05-19	12
26	13	14	2019-05-19	12
\.


--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 193
-- Name: tb_compra_id_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_compra_id_compra_seq', 26, true);


--
-- TOC entry 2203 (class 0 OID 16543)
-- Dependencies: 188
-- Data for Name: tb_empregado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_empregado (id_empregado, id_funcao, id_supervisor, nm_empregado, cpf, telefone, data_entrada, cidade, estado, rua, bairro, num_endereco, fg_ativo, senha, usuario, cep) FROM stdin;
36	8	36	dsssd	12345678901	16999999999	2019-05-11	ds	aaaaaaaaaaaaaaa	ds	ds	s	f	ds	ds	12345678
37	16	37	teste com a funcao	12345678901	16999999999	2019-05-11	ds	ds	sdd	dsd	sd	f	dsds	dsd	12345678
39	9	39	11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111	12345678901	16999999999	2019-05-11	1	1	1	1	1	f	1	1	12345678
12	8	12	Marcelo Gomes	12345678901	16999999999	1945-09-02	Ribeirão Preto	São Paulo	Rua abrahão Issa Halach	Vila Seixas	1458	t	123	marcelo	12345678
27	9	12	Peter Parker	12345678901	16999999999	1953-07-27	Ribeirão Preto	São Paulo	Rua Deolinda	Vila Seixas	107	t	123	peter	12345678
28	10	12	MV Bill	12345678901	16999999999	1975-04-30	Ribeirão Preto	São Paulo	Rua Mirian Strambi	Vila Seixas	642	t	123	mvbill	12345678
29	11	12	Lyoto Machida	12345678901	16999999999	2001-09-11	Ribeirão Preto	São Paulo	Rua Vincente de Carvalho	Vila Seixas	252	t	123	lyoto	12345678
40	8	40	teest	12345678901	16999999999	2019-05-11	2	2	2	2	2	f	2	2	12345678
30	11	12	Heitor Abrão	12345678901	16999999999	2018-07-15	Ribeirão Preto	São Paulo	Rua Flávio Uchôa	Campos Elísios	2151	t	123	heitor	12345678
34	8	12	teste	12345678901	16999999999	2019-05-11	ribeiraão preto	são paulo	rteste	sdsd	123	f	123	teste	12345678
43	8	12	teste2	12345678901	16999999999	2019-05-11	ribeiraão preto	são paulo	rteste	sdsd	123	f	111	11	12345678
44	8	28	Gabriel Gonoring Borges 2	12345678901	16999999999	2019-05-11	Ribeirão Preto2	São Paulo2	rua captão teste da silva2	Florestã Fernandes2	1233212	f	master12	gabriel2	12345678
51	31	51	2222222222222222222	2222222	2	2012-02-22	222222	22222	222222222222	22222222222	222222	f	2222	222222222222	
35	8	35	Gabriel Gonoring Borges	12345678901	16999999999	2019-05-11	Ribeirão Preto	São Paulo	rua captão teste da silva	Florestã Fernandes	123321	t	master1	gabriel	12345678
46	11	35	rosângela gonoring	12345678901	16999999999	2019-05-12	1	1	1	1	1	f	111	111	12345678
47	10	47	testisde	12345678901	16999999999	2019-05-14	323	323	23333	3232	2323	f	1	dsssdd	12345678
38	10	38	Luiz	12221212121	16919299121	2019-05-14	testes	fd	testerson	testerson	1212	f	1212	Luiz	12345678
48	9	28	testersan	11111111111	11111111111	2019-11-11	1	1	1111	11	11	t	111	11111111	11111111
45	16	35	Teste data	12345678901	16999999999	2015-05-11	1	1	1	1	1	t	1	1111	12345678
49	8	49	222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222	           	           	1111-11-11	222222222222222222222222222222	222222222222222	222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222	222222222222222222222222222222222222222222222222222222222222	2222222222	f	22222222222222222222	222222222222222222222222222222	
50	8	50	2222222222222222222222222222222	22222222222		1111-11-11	22	22	22222222222	22	222	f	222	2222	
52	31	52	2222222222222222222222222222	22222222222		2020-08-11	22222	222222	222222222	22	2222222	f	222222	2222222222	22222222
53	8	53	222222222222222222222222222222222	22222222222	22222222222	2019-01-12	222222222	2222	222222222	22222222	2222222222	t	2222	22222	22222222
54	9	28	teste cep	12223333322	22222222222	1999-11-11	21	21	testiano	12	123	f	12	12	
57	8	29	testeaosnas cep	12332323233		2132-01-12	122	122	testersan	211	123	f	1212	1221211	21333333
55	16	27	123	11111111111	11111111111	2000-11-11	123	topersan	1212	testino	212	f	1111	1222222221	
56	8	29	2112	11111111111	11111111111	2000-11-11	123	123	1232	123	123	f	1'213	topersan	
58	16	29	Teste para o relatório	12323123214		3215-10-21	132	324	topersan	213	123	t	321312	312433	21321341
59	9	59	123	11111111111	11111111111	2000-11-11	123	123	123	123	123	f	123	123	23211232
60	11	60	123123	11111111111	22222222222	2000-11-11	321	321	123123	123	213	f	123	123123123	
\.


--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 187
-- Name: tb_empregado_id_empregado_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_empregado_id_empregado_seq', 60, true);


--
-- TOC entry 2207 (class 0 OID 16584)
-- Dependencies: 192
-- Data for Name: tb_forma_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_forma_pagamento (id_forma_pagamento, desc_forma_pagamento, fg_ativo, formapagamento_id_forma_pagamento) FROM stdin;
9	Cartão de Crédito	t	\N
10	Cartão de Débito	t	\N
11	Dinheiro	t	\N
12	Boleto Bancário	t	\N
13	Heh Heh	t	\N
15	teste	t	\N
16	Cocococo	f	\N
14	teste212	f	\N
17	sdasdasds	t	\N
22	dsd1212	f	\N
21	sdaasdsd1	f	\N
23	teste2	f	\N
18	dsds	f	\N
19	testeFinalTalvez	f	\N
20	teste mais um 1	f	\N
24	1	f	\N
25	222222222222222222222222222222222222222222222222222222222222	t	\N
26	123123	f	\N
\.


--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 191
-- Name: tb_forma_pagamento_id_forma_pagamento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_forma_pagamento_id_forma_pagamento_seq', 26, true);


--
-- TOC entry 2201 (class 0 OID 16534)
-- Dependencies: 186
-- Data for Name: tb_funcao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_funcao (id_funcao, nm_funcao, desc_funcao, salario_funcao, fg_ativo) FROM stdin;
8	Gerente	O cara que manda em tudo	16000.00	t
9	Vendedor	Vende coisas	1600.00	t
10	Puxa-saco do gerente	\N	6000.00	t
11	Alimentador	Cuida das tarântulas	3000.00	t
15	TesteAlterar	estou alterando	12.00	f
12	teste	teste	1234.21	f
18	dsds	ds	12.12	f
20	ds	dsds	1.00	f
22	testersonies	topersondfskj	1223.12	f
19	jyu	jyhg	123.70	f
21	testino ultimo	toperson	122.21	f
17	asasa		122.10	f
23	1	1	11111.11	f
24	teste func	dsdsdsd	3231.21	f
13	teste	teste	1234.21	f
28	teste		111.10	f
16	testet	ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss	21.23	t
29	222222222222222222222222222222222222222222222222222222222222	222222222222222222222222222222222222222222222222222222222222	23232.32	f
30	222222222222222222222222222222222222222222222222222222222222	222222222222222222222222222222222222222222222222222222222222	11111.11	f
31	22222222222222222222222222222222222222222222222222222222222	222222222222222222222222222222222222222222222222222222222222	200.00	f
32	123123	123123	12.30	f
33	123	123	21320.00	f
\.


--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 185
-- Name: tb_funcao_id_funcao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_funcao_id_funcao_seq', 33, true);


--
-- TOC entry 2215 (class 0 OID 16651)
-- Dependencies: 200
-- Data for Name: tb_item_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_item_compra (id_item_compra, id_produto, id_compra, valor_item, quantidade_item) FROM stdin;
3	3	3	80.00	1
4	3	4	80.00	1
5	4	5	100.00	2
7	4	6	12.10	12
8	2	9	2.00	12
9	2	10	2.00	12
10	3	10	1.00	1
11	2	11	2.00	12
12	5	11	50.00	12
13	2	12	2.00	1
14	2	12	2.00	2
15	10	12	122.00	12
16	5	12	50.00	12
17	5	12	50.00	123
18	12	16	12.00	2
19	3	17	1.00	1
20	12	17	12.00	1
21	5	18	50.00	23
22	4	18	1.00	1
23	15	18	1154.87	2
24	12	20	12.00	2
25	6	21	1.00	1
26	10	22	122.00	12
27	5	22	50.00	23
28	13	23	1.00	1
29	5	23	50.00	3
30	18	24	18.47	12
31	2	24	11.50	1
32	12	25	12.00	2
33	16	26	111.10	1
34	5	26	50.00	1
\.


--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 199
-- Name: tb_item_compra_id_item_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_item_compra_id_item_compra_seq', 34, true);


--
-- TOC entry 2213 (class 0 OID 16631)
-- Dependencies: 198
-- Data for Name: tb_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_produto (id_produto, id_tipo_produto, valor_produto, quantidade_estoque, desc_produto, fg_ativo) FROM stdin;
7	1	12312.00	12312	testedessd	f
9	1	12.00	12	testeeetetet	f
8	1	1.00	1	Testison	f
11	2	1.00	1	Testino teste da testelva1212	f
4	1	1.00	0	Desert Blonde Tarantulas	t
15	2	1154.87	0	Teste Valor 3 alterado	t
10	6	122.00	110	teste	t
13	1	1.00	0	Homem Aranha	t
14	2	122.59	12	TesteTestino	t
17	9	18.47	150	222222222222222222222222222222222222222222222222222222222222	f
2	1	11.50	1	Mexican Redknee Tarantulas	t
3	1	1.50	30	Chilean Rose Tarantulas	t
6	1	3.55	38	Featherleg Baboon	t
18	3	18.47	108	222222222222222222222222222222222222222222222222222222222222	f
12	2	12.00	5	Tarântula	t
19	1	1.10	1	222	t
20	1	212.30	213	123	f
16	8	111.10	0	testeAlterarSemTipo	t
5	2	50.00	0	Grilhos para alimentação	t
\.


--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 197
-- Name: tb_produto_id_produto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_produto_id_produto_seq', 20, true);


--
-- TOC entry 2211 (class 0 OID 16623)
-- Dependencies: 196
-- Data for Name: tb_tipo_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_tipo_produto (id_tipo_produto, nm_tipo_produto, desc_tipo_produto, fg_ativo) FROM stdin;
1	Velho Mundo	Tarântulas da Europa/Ásia	t
2	Novo Mundo	Tarântulas da América/África	t
3	Acessórios	Tudo o que não for tarântula	t
5	sssssssss	ssssssssssssssssssssssssss	f
4	atualizado	toperson	f
7	111	fdsaaaa	f
6	'1	1	f
8	TesteDelete	1212	f
9	2222222222222222222222222	222222222222222222222222222222222222222222222222222222222222	f
10	123123	123123	f
\.


--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 195
-- Name: tb_tipo_produto_id_tipo_produto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_tipo_produto_id_tipo_produto_seq', 10, true);


--
-- TOC entry 2059 (class 2606 OID 16709)
-- Name: tb_empregado empregado_usuario_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_empregado
    ADD CONSTRAINT empregado_usuario_unique UNIQUE (usuario);


--
-- TOC entry 2063 (class 2606 OID 16576)
-- Name: tb_caixa pk_tb_caixa_id_caixa; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_caixa
    ADD CONSTRAINT pk_tb_caixa_id_caixa PRIMARY KEY (id_caixa);


--
-- TOC entry 2067 (class 2606 OID 16597)
-- Name: tb_compra pk_tb_comp_id_comp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_compra
    ADD CONSTRAINT pk_tb_comp_id_comp PRIMARY KEY (id_compra);


--
-- TOC entry 2061 (class 2606 OID 16548)
-- Name: tb_empregado pk_tb_emp_id_emp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_empregado
    ADD CONSTRAINT pk_tb_emp_id_emp PRIMARY KEY (id_empregado);


--
-- TOC entry 2065 (class 2606 OID 16589)
-- Name: tb_forma_pagamento pk_tb_for_pag_id_for_pag; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_forma_pagamento
    ADD CONSTRAINT pk_tb_for_pag_id_for_pag PRIMARY KEY (id_forma_pagamento);


--
-- TOC entry 2057 (class 2606 OID 16540)
-- Name: tb_funcao pk_tb_func_id_func; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_funcao
    ADD CONSTRAINT pk_tb_func_id_func PRIMARY KEY (id_funcao);


--
-- TOC entry 2073 (class 2606 OID 16658)
-- Name: tb_item_compra pk_tb_item_compra_id_item_compra; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_item_compra
    ADD CONSTRAINT pk_tb_item_compra_id_item_compra PRIMARY KEY (id_item_compra);


--
-- TOC entry 2071 (class 2606 OID 16638)
-- Name: tb_produto pk_tb_produto_id_produto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_produto
    ADD CONSTRAINT pk_tb_produto_id_produto PRIMARY KEY (id_produto);


--
-- TOC entry 2069 (class 2606 OID 16628)
-- Name: tb_tipo_produto pk_tb_tipo_produto_id_tipo_produto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_tipo_produto
    ADD CONSTRAINT pk_tb_tipo_produto_id_tipo_produto PRIMARY KEY (id_tipo_produto);


--
-- TOC entry 2078 (class 2606 OID 16603)
-- Name: tb_compra fk_tb_comp_id_caixa_tb_caixa_id_caixa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_compra
    ADD CONSTRAINT fk_tb_comp_id_caixa_tb_caixa_id_caixa FOREIGN KEY (id_caixa) REFERENCES public.tb_caixa(id_caixa);


--
-- TOC entry 2077 (class 2606 OID 16598)
-- Name: tb_compra fk_tb_comp_id_form_pag_tb_form_pag_id_form_pag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_compra
    ADD CONSTRAINT fk_tb_comp_id_form_pag_tb_form_pag_id_form_pag FOREIGN KEY (id_forma_pagamento) REFERENCES public.tb_forma_pagamento(id_forma_pagamento);


--
-- TOC entry 2074 (class 2606 OID 16549)
-- Name: tb_empregado fk_tb_emp_id_func_tb_func_id_func; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_empregado
    ADD CONSTRAINT fk_tb_emp_id_func_tb_func_id_func FOREIGN KEY (id_funcao) REFERENCES public.tb_funcao(id_funcao);


--
-- TOC entry 2075 (class 2606 OID 16554)
-- Name: tb_empregado fk_tb_emp_id_gerente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_empregado
    ADD CONSTRAINT fk_tb_emp_id_gerente FOREIGN KEY (id_supervisor) REFERENCES public.tb_empregado(id_empregado);


--
-- TOC entry 2081 (class 2606 OID 16659)
-- Name: tb_item_compra fk_tb_item_compra_id_produto_tb_produto_id_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_item_compra
    ADD CONSTRAINT fk_tb_item_compra_id_produto_tb_produto_id_produto FOREIGN KEY (id_produto) REFERENCES public.tb_produto(id_produto);


--
-- TOC entry 2082 (class 2606 OID 16664)
-- Name: tb_item_compra fk_tb_item_compra_tb_compra_id_compra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_item_compra
    ADD CONSTRAINT fk_tb_item_compra_tb_compra_id_compra FOREIGN KEY (id_compra) REFERENCES public.tb_compra(id_compra);


--
-- TOC entry 2080 (class 2606 OID 16639)
-- Name: tb_produto fk_tb_produto_id_tipo_produto_tb_tipo_produto_id_tipo_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_produto
    ADD CONSTRAINT fk_tb_produto_id_tipo_produto_tb_tipo_produto_id_tipo_produto FOREIGN KEY (id_tipo_produto) REFERENCES public.tb_tipo_produto(id_tipo_produto);


--
-- TOC entry 2079 (class 2606 OID 16715)
-- Name: tb_compra fkh274vjqf21hwrw39md668uq8q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_compra
    ADD CONSTRAINT fkh274vjqf21hwrw39md668uq8q FOREIGN KEY (id_empregado) REFERENCES public.tb_empregado(id_empregado);


--
-- TOC entry 2076 (class 2606 OID 16685)
-- Name: tb_forma_pagamento fktabpd70xhx3h5jn3y6pooqgva; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_forma_pagamento
    ADD CONSTRAINT fktabpd70xhx3h5jn3y6pooqgva FOREIGN KEY (formapagamento_id_forma_pagamento) REFERENCES public.tb_forma_pagamento(id_forma_pagamento);


-- Completed on 2019-05-19 14:56:46

--
-- PostgreSQL database dump complete
--

