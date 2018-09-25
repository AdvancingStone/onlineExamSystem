--用户表
create table euser(
    id number primary key,
    username varchar2(36) not null,
    password varchar2(16) not null,
    ismanager number not null，
    CONSTRAINT constraint_name UNIQUE (username)    
)
create sequence euser_id_seq;
drop table euser;


insert into euser values(euser_id_seq.nextval,'杰哥','123456',1);
insert into euser values(euser_id_seq.nextval,'马哥','123456',1);
insert into euser values(euser_id_seq.nextval,'小月月','123456',2);
insert into euser values(euser_id_seq.nextval,'小瑶瑶','123456',2);
insert into euser values(euser_id_seq.nextval,'小略略','123456',2);


--选择题
create table choice_question(
    cid number primary key,--考题编号
    c_question varchar2(1000) unique, --试题内容
    right_answer varchar2(2) not null, --正确选项 ABCD之一
    a_answer varchar2(256) not null, -- A 答案
    b_answer varchar2(256) not null, -- B 答案  
    c_answer varchar2(256) not null, -- C 答案 
    d_answer varchar2(256) not null, -- D 答案
    mark number default 8-- 分值
)
create sequence choice_question_cid_seq;
drop sequence choice_question_cid_seq;
drop table choice_question;

--问答题
create table essay_question(
    eid number primary key,--考题编号
    e_question varchar2(1000) unique, --试题内容
    mark number default 20-- 分值
)
create sequence essay_question_eid_seq;
drop sequence essay_question_eid_seq;
drop table essay_question;

-- 考题
create table subject(
    sid number primary key,--考题编号
    c_q_1 varchar2(1000)  references choice_question(c_question),--选择题
    c_q_2 varchar2(1000)  references choice_question(c_question),
    c_q_3 varchar2(1000)  references choice_question(c_question),
    c_q_4 varchar2(1000)  references choice_question(c_question),
    c_q_5 varchar2(1000)  references choice_question(c_question),
    c_q_6 varchar2(1000)  references choice_question(c_question),
    c_q_7 varchar2(1000)  references choice_question(c_question),
    c_q_8 varchar2(1000)  references choice_question(c_question),
    
    e_q_1 varchar2(1000)  references essay_question(e_question),--大题
    e_q_2 varchar2(1000)  references essay_question(e_question),
    e_q_3 varchar2(1000)  references essay_question(e_question)

)
create sequence subject_sid_seq;
drop sequence subject_sid_seq;
drop table subject;

--学员考试记录表
create table exam_record(
    erid number primary key, --记录编号
    id number not null references euser(id),
    time date, --考试时间
    sid number references subject(sid),
    c_a_1 varchar2(2),
    c_a_2 varchar2(2),
    c_a_3 varchar2(2),
    c_a_4 varchar2(2),
    c_a_5 varchar2(2),
    c_a_6 varchar2(2),
    c_a_7 varchar2(2),
    c_a_8 varchar2(2),
    
    e_a_1 varchar2(256),
    e_a_2 varchar2(256),
    e_a_3 varchar2(256)
    
)
create sequence exam_record_erid_seq;
drop sequence exam_record_erid_seq;
drop table exam_record;




--成绩表
create table result(
    rid number primary key, --成绩表编号
    erid number not null references exam_record(erid),
    id number not null references euser(id),
    record number
)
create sequence result_rid_seq;
drop sequence result_rid_seq;
drop table result;
