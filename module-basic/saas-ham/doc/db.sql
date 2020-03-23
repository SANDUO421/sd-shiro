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

# 用户管理
drop table if exists saas_hrm.bs_user;
create table saas_hrm.bs_user
(
    id   varchar(40)  not null comment 'id',
    mobile varchar(40)  not null comment '手机号码',
    username varchar(255)  not null comment '用户名称',
    password varchar(255)  default null comment '密码',
    enable_state int(2) default 1 comment '启用状态 0 禁用,1是启用',
    create_time datetime  default null comment '创建时间',
    department_id varchar(40)  default null comment '部门Id',
    time_of_entry datetime  default null comment '入职时间',
    form_of_employment int(1)  default null comment '聘用形式',
    work_number varchar(40)  default null comment '工号',
    form_of_management varchar(8)  default null comment '管理形式',
    working_city varchar(16)  not null comment '工作城市',
    correction_time datetime default null comment '转正时间',
    in_service_status int(2)  default null comment '在职状态  1 在职,2离职',
    company_id varchar(40) default null comment '企业Id',
    company_name varchar(40)  default null comment '企业名称',
    department_name varchar(40)  default null comment '部门名称',
    primary key (id),
    unique key idx_user_phone(mobile) using btree
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户管理';
# 角色管理
drop table if exists saas_hrm.pe_role;
create table saas_hrm.pe_role(
   id   varchar(40)  not null comment 'id',
   name varchar(255) not null comment '角色名称',
   description varchar(255) default null comment '角色描述',
   company_id varchar(40) not null comment '企业Id',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色管理';

# 用户角色管理 TODO
drop table if exists saas_hrm.pe_user_role;
create table saas_hrm.pe_user_role(
    id varchar(40)  not null comment 'id',
    user_id varchar(40)  not null comment '用户id',
    role_id varchar(40)  not null comment '角色id',
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色管理';

# 权限管理
drop table if exists saas_hrm.pe_permission;
create table saas_hrm.pe_permission(
    id varchar(40)  not null comment 'id',
    name varchar(255) not null comment '权限名称',
    type int(2) default null comment '权限类型: 1 菜单,2 功能,3 为API',
    code varchar(255) not null comment '权限标识',
    description varchar(255) default null comment '权限描述',
    pid varchar(40)  default null comment '权限父ID',
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限管理';

# 角色权限 TODO
drop table if exists saas_hrm.pe_role_permission;
create table saas_hrm.pe_role_permission
(
    id varchar(40)  not null comment 'id',
    role_id varchar(40)  not null comment '角色id',
    permission_id varchar(40)  not null comment '权限id',
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限管理';

# 菜单权限实体类
drop table if exists saas_hrm.pe_permission_menu;
create table saas_hrm.pe_permission_menu
(
    id varchar(40)  not null comment 'id',
    menu_icon varchar(40)  not null comment '展示图标',
    menu_order varchar(40)  not null comment '排序号',
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限管理';


# 按钮权限实体类
drop table if exists saas_hrm.pe_permission_point;
create table saas_hrm.pe_permission_point
(
    id varchar(40)  not null comment '主键id',
    point_class varchar(40)  not null comment '按钮代码',
    point_icon varchar(40)  not null comment '按钮图标',
    point_status varchar(40)  not null comment '按钮状态',
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='按钮权限管理';


# API权限实体类 TODO
drop table if exists saas_hrm.pe_permission_api;
create table saas_hrm.pe_permission_api
(
    id varchar(40)  not null comment '主键id',
    id_url varchar(40)  not null comment '链接',
    api_method varchar(40)  not null comment '请求类型',
    permission_type varchar(2)  not null comment '权限登记 1',
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='API权限管理';





set foreign_key_checks = 1;