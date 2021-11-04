create database db_escola;

use db_escola;

create table tb_usuarios(
id_usuario int primary key auto_increment,
nome_usuario varchar(50) not null,
login_usuario varchar(25) not null unique,
senha_usuario varchar(25) not null,
perfil_usuario varchar(5) not null
);

insert into tb_usuarios(nome_usuario,login_usuario,senha_usuario,perfil_usuario)
values('Adiministrador','admin','12345','admin');

update tb_usuarios set login_usuario = 'admin' where id_usuario = 1;

create table tb_escola(
id_escola int primary key auto_increment,
nome_escola varchar(25) not null,
codigo_inep varchar(10),
rua_escola varchar(20) not null,
numero_escola varchar(5) not null,
bairro_escola varchar(20) not null,
cidade_escola varchar(30) not null,
cep_escola varchar(10) not null,
estado_escola varchar(20) not null
);

create table tb_turma(
id_turma int primary key auto_increment,
nome_turma varchar(25) not null unique,
turno varchar(10) not null,
id_escola int not null,
foreign key(id_escola) references tb_escola(id_escola)
);

create table tb_professor(
id_prof int primary key auto_increment,
cpf_prof varchar(15) not null unique,
nome_prof varchar(25) not null,
sobrenome_prof varchar(25) not null,
data_de_nascimento_prof varchar(10) not null,
turno_prof varchar(10) not null,
formacao_prof varchar(20),
telefone_prof varchar(15) not null,
email_prof varchar(50),
rua_prof varchar(20) not null,
numero varchar(5) not null,
bairro varchar(20) not null,
cidade varchar(30) not null,
cep varchar(10) not null,
estado varchar(20) not null,
matricula_prof varchar(15) not null,
vinculo_prof varchar(10) not null,
id_turma int not null,
foreign key(id_turma) references tb_turma(id_turma)
);

create table tb_aluno(
id_aluno int primary key auto_increment,
cpf_aluno varchar(15) not null unique,
nome_aluno varchar(25) not null,
sobrenome_aluno varchar(25) not null,
data_nasc_aluno varchar(10) not null,
turno_aluno varchar(10) not null,
telefone_aluno varchar(15) not null,
email_aluno varchar(50),
rua_aluno varchar(20) not null,
numero varchar(5) not null,
bairro varchar(20) not null,
cidade varchar(30) not null,
cep varchar(10) not null,
estado varchar(20) not null,
matricula_aluno varchar(15) not null,
id_prof int not null,
foreign key(id_prof) references tb_professor(id_prof),
id_turma int not null,
foreign key(id_turma) references tb_turma(id_turma)
);

create table adv_aluno(
id_adv_aluno int primary key auto_increment,
motivos varchar(30) not null,
data_acontecimento varchar(15) not null,
advertencia varchar(500) not null,
id_aluno int not null,
foreign key(id_aluno) references tb_aluno(id_aluno)
);

create table susp_aluno(
id_sup_aluno int primary key auto_increment,
motivos varchar(30) not null,
data_acontecimento varchar(15) not null,
suspensao varchar(500) not null,
data_inicio_susp varchar(15) not null,
data_fim_susp varchar(15) not null,
id_aluno int not null,
foreign key(id_aluno) references tb_aluno(id_aluno)
);

create table expulsao_aluno(
exp_aluno int primary key auto_increment,
motivo varchar(30) not null,
data_acontecimento varchar(15) not null,
expulsao varchar(500) not null,
id_aluno int not null,
foreign key(id_aluno) references tb_aluno(id_aluno)
);

-- Selects
select * from tb_usuarios;
select * from adv_aluno;
select * from susp_aluno;
select * from tb_escola;

select
P.id_prof,email_prof,nome_prof,sobrenome_prof,
A.id_aluno,cpf_aluno,nome_aluno,sobrenome_aluno
from tb_professor as P inner join tb_aluno as A
on(A.id_prof = P.id_prof);

select
P.id_prof,nome_prof,sobrenome_prof,
T.id_turma,nome_turma,turno
from tb_professor as P inner join tb_turma as T 
on(T.id_turma = P.id_turma);

select
E.nome_escola,
P.nome_prof,sobrenome_prof,
A.nome_aluno,sobrenome_aluno,
T.nome_turma
from tb_escola as E inner join tb_turma as T
inner join tb_aluno as A inner join tb_professor as P
on(P.id_prof = A.id_prof)
on(P.id_turma = T.id_turma);

select
O.motivos,data_acontecimento,suspensao,data_inicio_susp,data_fim_susp,
S.nome_escola,cidade_escola,rua_escola,bairro_escola,numero_escola,cep_escola,estado_escola
from susp_aluno as O 
inner join tb_escola as S
on(S.id_escola = S.id_escola);

select
sa.id_sup_aluno,motivos,suspensao,
al.nome_aluno,sobrenome_aluno
from susp_aluno as sa inner join tb_aluno as al
on(al.id_aluno = sa.id_aluno);