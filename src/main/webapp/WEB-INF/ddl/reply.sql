CREATE SEQUENCE seq_reply;

CREATE TABLE reply(
  re_no         NUMBER NOT NULL,
  re_category   VARCHAR2(30) NOT NULL,
  re_parent_no  NUMBER NOT NULL,
  re_mem_id     VARCHAR2(30) NOT NULL,
  re_content    VARCHAR2(4000) ,
  re_ip  VARCHAR2(30),
  re_reg_date DATE DEFAULT SYSDATE, 
  re_mod_date DATE, 
  CONSTRAINT pk_reply PRIMARY KEY (re_no)  
);
COMMENT ON table reply is  '댓글정보 테이블';
COMMENT ON COLUMN reply.re_no IS  '댓글번호';
COMMENT ON COLUMN reply.re_category IS '분류(BOARD, PDS, FREE, ...)';
COMMENT ON COLUMN reply.re_parent_no IS '부모 번호';
COMMENT ON COLUMN reply.re_mem_id   IS '작성자ID';
COMMENT ON COLUMN reply.re_content  IS '댓글 내용';
COMMENT ON COLUMN reply.re_ip       IS 'IP';
COMMENT ON COLUMN reply.re_reg_date IS '댓글 등록일자';
COMMENT ON COLUMN reply.re_mod_date IS '댓글 수정일자';

