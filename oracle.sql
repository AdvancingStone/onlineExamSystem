--�û���
create table euser(
    id number primary key,
    username varchar2(36) not null,
    password varchar2(16) not null,
    ismanager number not null��
    CONSTRAINT constraint_name UNIQUE (username)    
)
create sequence euser_id_seq;
drop table euser;


insert into euser values(euser_id_seq.nextval,'�ܸ�','123456',1);
insert into euser values(euser_id_seq.nextval,'���','123456',1);
insert into euser values(euser_id_seq.nextval,'С����','123456',2);
insert into euser values(euser_id_seq.nextval,'С����','123456',2);
insert into euser values(euser_id_seq.nextval,'С����','123456',2);


--ѡ����
create table choice_question(
    cid number primary key,--������
    c_question varchar2(1000) unique, --��������
    right_answer varchar2(2) not null, --��ȷѡ�� ABCD֮һ
    a_answer varchar2(256) not null, -- A ��
    b_answer varchar2(256) not null, -- B ��  
    c_answer varchar2(256) not null, -- C �� 
    d_answer varchar2(256) not null, -- D ��
    mark number default 8-- ��ֵ
)
create sequence choice_question_cid_seq;
drop sequence choice_question_cid_seq;
drop table choice_question;

--�ʴ���
create table essay_question(
    eid number primary key,--������
    e_question varchar2(1000) unique, --��������
    mark number default 20-- ��ֵ
)
create sequence essay_question_eid_seq;
drop sequence essay_question_eid_seq;
drop table essay_question;

-- ����
create table subject(
    sid number primary key,--������
    c_q_1 varchar2(1000)  references choice_question(c_question),--ѡ����
    c_q_2 varchar2(1000)  references choice_question(c_question),
    c_q_3 varchar2(1000)  references choice_question(c_question),
    c_q_4 varchar2(1000)  references choice_question(c_question),
    c_q_5 varchar2(1000)  references choice_question(c_question),
    c_q_6 varchar2(1000)  references choice_question(c_question),
    c_q_7 varchar2(1000)  references choice_question(c_question),
    c_q_8 varchar2(1000)  references choice_question(c_question),
    
    e_q_1 varchar2(1000)  references essay_question(e_question),--����
    e_q_2 varchar2(1000)  references essay_question(e_question),
    e_q_3 varchar2(1000)  references essay_question(e_question)

)
create sequence subject_sid_seq;
drop sequence subject_sid_seq;
drop table subject;

--ѧԱ���Լ�¼��
create table exam_record(
    erid number primary key, --��¼���
    id number not null references euser(id),
    time date, --����ʱ��
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




--�ɼ���
create table result(
    rid number primary key, --�ɼ�����
    erid number not null references exam_record(erid),
    id number not null references euser(id),
    record number
)
create sequence result_rid_seq;
drop sequence result_rid_seq;
drop table result;
