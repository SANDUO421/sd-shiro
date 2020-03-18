# 创建数据库
CREATE DATABASE if not exists saas_hrm DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
# 创建表 co_company
set names utf8mb4;
set foreign_key_checks = 0;
drop table if exists saas_hrm.co_company;
create table saas_hrm.co_company
(
    id                   varchar(40)  not null comment 'ID',
    name                 varchar(255) not null comment '公司名称',
    manager_id           varchar(255) not null comment '企业登录账号',
    version              varchar(255)          default null comment '当前版本',
    renewal_date         datetime              default null comment '续期时间',
    expiration_date      datetime              default null comment '到期时间',
    company_area         varchar(255)          default null comment '公司地区',
    company_address      text                  default null comment '公司地址',
    business_license_id  varchar(255)          default null comment '营业执照-图片ID',
    legal_representative varchar(255)          default null comment '法人代表',
    company_phone        varchar(255)          default null comment '公司电话',
    mailbox              varchar(255)          default null comment '邮箱',
    company_size         varchar(255)          default null comment '公司规模',
    industry             varchar(255)          default null comment '所属行业',
    remark               text comment '备注',
    audit_state          varchar(255)          default null comment '审核状态,0未审核 1审核',
    state                tinyint(2)   not null default '1' comment '状态',
    balance              double       not null comment '当前余额',
    create_time          datetime     not null comment '创建时间',
    primary key (id)

) Engine = InnoDB
  default charset = utf8mb4;

# 创建部门表
drop table if exists saas_hrm.co_department;
create table saas_hrm.co_department
(
    id         varchar(40)  not null comment 'id',
    company_id  varchar(40)  not null comment '企业ID',
    parent_id   varchar(40)  default null comment '父级部门Id',
    name        varchar(255) not null comment '部门名称',
    code        varchar(255) not null comment '部门编码',
    category    varchar(255) default null comment '部门类别',
    manager_id  varchar(255) default null comment '负责人ID',
    manager     varchar(40)  default null comment '负责人',
    city        varchar(255) default null comment '城市',
    introduce   text comment '介绍',
    create_time datetime     not null comment '创建时间',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门管理';

set foreign_key_checks = 1;