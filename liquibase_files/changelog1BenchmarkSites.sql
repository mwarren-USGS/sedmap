--liquibase formatted sql

--This is for the sedmap schema
 
--changeset ajmccart:CreatePopulateBenchmarkTable
CREATE TABLE SEDMAP.BENCHMARK_SITES
(
  SITE_NO  VARCHAR2(15 BYTE)
);

ALTER TABLE SEDMAP.BENCHMARK_SITES ADD (
  CONSTRAINT BENCHMARK_SITES_PK
  PRIMARY KEY
  (SITE_NO));
  
ALTER TABLE SEDMAP.BENCHMARK_SITES ADD (
  CONSTRAINT BENCHMARK_SITES_R01 
  FOREIGN KEY (SITE_NO) 
  REFERENCES SEDMAP.SITE_REF (SITE_NO));

insert into benchmark_sites
select * from src_benchmark_sites
where USGS_STATION_ID not in 
(select "site_no" from SRC_AJM_EXCESS_SITES_11_26_13);

GRANT SELECT ON SEDMAP.BENCHMARK_SITES TO SEDUSER;


--rollback Drop table benchmark_sites;  