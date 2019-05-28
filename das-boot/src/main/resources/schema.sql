;              
CREATE USER IF NOT EXISTS SA SALT '9057595376c74046' HASH 'f7e1b561fbcd16ddc0f7b5413dee5d9107c89d3cc4786c70bfc95c6751d9540a' ADMIN;            
CREATE SEQUENCE IF NOT EXISTS PUBLIC.SYSTEM_SEQUENCE_8009191C_29AA_44D8_9064_0E60A83310A5 START WITH 26 BELONGS_TO_TABLE;    
CREATE SEQUENCE IF NOT EXISTS PUBLIC.SYSTEM_SEQUENCE_02BFC56E_CD8B_427F_96B0_FE0ADB0752B6 START WITH 28 BELONGS_TO_TABLE;    
CREATE SEQUENCE IF NOT EXISTS PUBLIC.SYSTEM_SEQUENCE_3EBB7777_1209_4DCA_9C49_7F9D40658F04 START WITH 3 BELONGS_TO_TABLE;     
CREATE SEQUENCE IF NOT EXISTS PUBLIC.SYSTEM_SEQUENCE_FDC9F5B4_F5C9_4096_96FC_668A12FBAF25 START WITH 69 BELONGS_TO_TABLE;    
CREATE CACHED TABLE IF NOT EXISTS PUBLIC.LOCATION(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_FDC9F5B4_F5C9_4096_96FC_668A12FBAF25) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_FDC9F5B4_F5C9_4096_96FC_668A12FBAF25,
    COUNTRY VARCHAR(255),
    STATE VARCHAR(255)
);     
ALTER TABLE PUBLIC.LOCATION ADD CONSTRAINT PUBLIC.CONSTRAINT_9 PRIMARY KEY(ID);

CREATE CACHED TABLE IF NOT EXISTS PUBLIC.MANUFACTURER(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_3EBB7777_1209_4DCA_9C49_7F9D40658F04) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_3EBB7777_1209_4DCA_9C49_7F9D40658F04,
    AVERAGEYEARLYSALES DECIMAL(19, 2),
    FOUNDEDDATE TIMESTAMP,
    NAME VARCHAR(255),
    HEADQUARTERS_ID BIGINT,
    LOCATION_ID BIGINT,
    ACTIVE BOOLEAN
);      
ALTER TABLE PUBLIC.MANUFACTURER ADD CONSTRAINT PUBLIC.CONSTRAINT_1 PRIMARY KEY(ID);            

CREATE CACHED TABLE PUBLIC.MODEL(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_02BFC56E_CD8B_427F_96B0_FE0ADB0752B6) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_02BFC56E_CD8B_427F_96B0_FE0ADB0752B6,
    FRETS INTEGER NOT NULL,
    NAME VARCHAR(255),
    PRICE DECIMAL(19, 2),
    WOODTYPE VARCHAR(255),
    YEARFIRSTMADE TIMESTAMP,
    MANUFACTURER_ID BIGINT,
    MODELTYPE_ID BIGINT
);
ALTER TABLE PUBLIC.MODEL ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(ID);   

CREATE CACHED TABLE PUBLIC.MODELTYPE(
    ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_8009191C_29AA_44D8_9064_0E60A83310A5) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_8009191C_29AA_44D8_9064_0E60A83310A5,
    NAME VARCHAR(255)
);               
ALTER TABLE PUBLIC.MODELTYPE ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);               

ALTER TABLE PUBLIC.MANUFACTURER ADD CONSTRAINT PUBLIC.FK946977F11E8FDB8 FOREIGN KEY(LOCATION_ID) REFERENCES PUBLIC.LOCATION(ID) NOCHECK;       
ALTER TABLE PUBLIC.MODEL ADD CONSTRAINT PUBLIC.FK4710B097D6BF938 FOREIGN KEY(MANUFACTURER_ID) REFERENCES PUBLIC.MANUFACTURER(ID) NOCHECK;      
ALTER TABLE PUBLIC.MODEL ADD CONSTRAINT PUBLIC.FK4710B096FD1D69C FOREIGN KEY(MODELTYPE_ID) REFERENCES PUBLIC.MODELTYPE(ID) NOCHECK;            
ALTER TABLE PUBLIC.MANUFACTURER ADD CONSTRAINT PUBLIC.FK946977F1598B2F06 FOREIGN KEY(HEADQUARTERS_ID) REFERENCES PUBLIC.LOCATION(ID) NOCHECK;  
