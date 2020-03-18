create table oauth_client_details
(
    client_id               varchar(256)  not null
        primary key,
    resource_ids            varchar(256)  null,
    client_secret           varchar(256)  null,
    scope                   varchar(256)  null,
    authorized_grant_types  varchar(256)  null,
    web_server_redirect_uri varchar(256)  null,
    authorities             varchar(256)  null,
    access_token_validity   int           null,
    refresh_token_validity  int           null,
    additional_information  varchar(4096) null,
    autoapprove             varchar(256)  null
)
    charset = utf8mb4;

create table sys_menu
(
    id               bigint unsigned                                   not null
        primary key,
    parent_id        bigint unsigned         default 0                 not null comment '父菜单id',
    level            tinyint(5) unsigned     default 0                 not null comment '菜单等级',
    sort_number      tinyint(5) unsigned     default 0                 not null comment '排序字段',
    menu_name        varchar(50)             default ''                not null comment '菜单名称',
    menu_type        enum ('MENU', 'BUTTON') default 'MENU'            not null comment '菜单类型',
    icon             varchar(100)            default ''                not null comment '菜单图标',
    url              varchar(50)             default ''                not null comment '菜单地址',
    menu_description varchar(100)            default ''                not null comment '菜单描述',
    create_time      datetime                default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id   bigint unsigned                                   null comment '创建人id',
    update_time      datetime                                          null comment '修改时间',
    update_user_id   bigint unsigned                                   null comment '修改人id',
    delete_time      datetime                                          null comment '删除时间',
    delete_user_id   bigint unsigned                                   null comment '删除人id',
    is_delete        tinyint unsigned        default 0                 not null comment '是否删除（0-未删除，1-删除）'
)
    comment '菜单表';

create table sys_permission
(
    id                    bigint unsigned                            not null
        primary key,
    permission_name       varchar(100)     default ''                not null comment '权限名称',
    permission_expression varchar(100)     default ''                not null comment '权限表达式',
    create_time           datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id        bigint unsigned                            null comment '创建人id',
    update_time           datetime                                   null comment '更新时间',
    update_user_id        bigint unsigned                            null comment '更新人id',
    delete_time           datetime                                   null comment '删除时间',
    delete_user_id        bigint unsigned                            null comment '删除人id',
    is_delete             tinyint unsigned default 0                 not null comment '是否删除（0-未删除，1-删除）'
)
    comment '权限表';

create table sys_role
(
    id               bigint unsigned                            not null
        primary key,
    role_name        varchar(50)      default ''                not null comment '角色名称',
    role_expression  varchar(50)      default ''                not null comment '角色表达式',
    role_description varchar(100)     default ''                not null comment '角色描述',
    create_time      datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id   bigint unsigned                            null comment '创建人id',
    update_time      datetime                                   null comment '修改时间',
    update_user_id   bigint unsigned                            null comment '修改人id',
    delete_time      datetime                                   null comment '删除时间',
    delete_user_id   bigint unsigned                            null comment '删除人id',
    is_delete        tinyint unsigned default 0                 not null comment '是否删除（0-未删除，1-删除）'
)
    comment '用户角色表';

create table sys_role_permission
(
    id             bigint unsigned                            not null
        primary key,
    role_id        bigint unsigned                            not null comment '用户id',
    permission_id  bigint unsigned                            not null comment '角色id',
    create_time    datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id bigint unsigned                            null comment '创建人id',
    update_time    datetime                                   null comment '修改时间',
    update_user_id bigint unsigned                            null comment '修改人id',
    delete_time    datetime                                   null comment '删除时间',
    delete_user_id bigint unsigned                            null comment '删除人id',
    is_delete      tinyint unsigned default 0                 not null comment '是否删除（0-未删除，1-删除）'
)
    comment '角色权限关系表';

create table sys_user
(
    id                bigint unsigned                            not null
        primary key,
    user_name         varchar(100)     default ''                not null,
    nick_name         varchar(100)     default ''                not null,
    sex               tinyint(3) unsigned default 0              not null comment '性别（0-男，1-女）',
    phone             varchar(20)      default ''                not null,
    password          varchar(100)     default ''                not null,
    head_portraits    varchar(200)     default ''                not null comment '头像',
    read_time         bigint unsigned  default 0                 not null comment '阅读时长（单位秒）',
    login_times       int unsigned     default 0                 not null comment '登陆次数',
    recent_login_time datetime                                   null comment '最近登录时间',
    state             tinyint unsigned default 0                 not null comment '状态（0-正常，1-停用）',
    is_vip            tinyint unsigned default 1                 not null comment '是否VIP（0-是，1-不是）',
    vip_begin_date    datetime                                   null comment '会员开始日期',
    vip_effective_day int(6) unsigned  default 0                 not null comment '会员有效期',
    vip_end_date      datetime                                   null comment '会员结束日期',
    create_time       datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id    bigint unsigned                            null comment '创建人id',
    update_time       datetime                                   null comment '更新时间',
    update_user_id    bigint unsigned                            null comment '更新人id',
    delete_time       datetime                                   null comment '删除时间',
    delete_user_id    bigint unsigned                            null comment '删除人id',
    is_delete         tinyint unsigned default 0                 not null comment '是否删除（0-未删除，1-删除）'
)
    comment '用户表';

create table sys_user_permission
(
    id             bigint unsigned                            not null
        primary key,
    user_id        bigint unsigned                            not null comment '用户id',
    permission_id  bigint unsigned                            not null comment '角色id',
    create_time    datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id bigint unsigned                            null comment '创建人id',
    update_time    datetime                                   null comment '修改时间',
    update_user_id bigint unsigned                            null comment '修改人id',
    delete_time    datetime                                   null comment '删除时间',
    delete_user_id bigint unsigned                            null comment '删除人id',
    is_delete      tinyint unsigned default 0                 not null comment '是否删除（0-未删除，1-删除）'
)
    comment '用户权限关系表';

create table sys_user_role
(
    id             bigint unsigned                            not null
        primary key,
    user_id        bigint unsigned                            not null comment '用户id',
    role_id        bigint unsigned                            not null comment '角色id',
    create_time    datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    create_user_id bigint unsigned                            null comment '创建人id',
    update_time    datetime                                   null comment '修改时间',
    update_user_id bigint unsigned                            null comment '修改人id',
    delete_time    datetime                                   null comment '删除时间',
    delete_user_id bigint unsigned                            null comment '删除人id',
    is_delete      tinyint unsigned default 0                 not null comment '是否删除（0-未删除，1-删除）'
)
    comment '用户角色关系表';

