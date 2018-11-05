# 用户表
create table user (
  id varchar(50) not null primary key comment '主键',
  uname varchar(50) not null comment '用户名',
  upass varchar(50) not null comment '用户密码',
  departId varchar(50) not null comment '部门id',
  status char(1) not null comment '用户状态 1代表正常  2代表锁定'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 角色表
CREATE TABLE role (
  rid varchar(50) NOT NULL comment '主键',
  rname varchar(255) DEFAULT NULL comment '角色名',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 用户角色关联表
CREATE TABLE user_role (
  uid varchar(50) DEFAULT NULL comment '用户id',
  rid varchar(50) DEFAULT NULL comment '角色id',
  KEY `u_fk` (`uid`),
  KEY `r_fk` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 权限表
CREATE TABLE module (
  mid varchar(50) NOT NULL comment '主键',
  mname varchar(255) DEFAULT NULL comment '权限名称',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 角色权限关联表
CREATE TABLE role_module (
  rid varchar(50) DEFAULT NULL comment '角色id',
  mid varchar(50) DEFAULT NULL comment '权限id',
  KEY `r_id` (`rid`),
  KEY `m_id` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 部门表
create table department(
  did varchar(50) primary key comment '主键',
  dname varchar(50) comment '部门名称',
  dmamager varchar(50) comment '部门经理',
  duty varchar(200) comment '部门职责'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 用户信息表
create table profile(
  pid varchar(50) primary key not null comment '主键',
  uid varchar(50) not null comment '用户id',
  did varchar(50) not null comment '部门id',
  indate varchar(20) not null comment '入职日期',
  faredate varchar(20) not null comment '离职日期',
  major varchar(50)  comment '专业',
  id varchar(20) comment '身份证号',
  sex char(1) comment '性别',
  edu varchar(10) comment '教育程度'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 客户表
create table custom(
  custom_id varchar(50) primary key not null comment '主键',
  custom_name varchar(100) not null comment '客户名字',
  custom_address varchar(200) comment '客户地址',
  custom_code varchar(20) comment '客户编号',
  custom_tel varchar(20) comment '客户电话',
  custom_publish varchar(20) comment '客户法人',
  custom_fullname varchar(20) comment '客户全名',
  status char(1) comment '客户状态  1代表有效   2代表无效'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 产品表
create table product(
  pro_id  varchar(50) not null primary key comment '主键',
  pro_name varchar(100) comment '产品名称',
  pro_code varchar(100) comment '产品编号',
  pro_count long comment '产品当前库存数量',
  pro_status char(1) comment '产品状态，1代表已量产，2代表已停产',
  pro_image varchar(200) comment '产品图片的url',
  pro_note varchar(200) comment '产品备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 设备表
create table device(
  device_id varchar(50) not null primary key comment '主键',
  device_name varchar(100) comment '设备名称',
  device_status char(1) comment '设备状态，1代表良好，2代表待维修，3代表维修OK',
  device_pur_date varchar(20) comment '设备采购日期',
  device_price long comment '设备单价',
  device_code varchar(100) comment '设备编号',
  device_vendor varchar(100) comment '设备供应商',
  device_vendor_tel varchar(100) comment '供应商联系电话',
  device_used_period varchar(20) comment '设备使用截止日期',
  device_note varchar(200) comment '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 设备保养表
create table device_mantain_histo(
  his_id varchar(50) primary key comment '主键',
  device_code varchar(100) comment '设备编号',
  his_date varchar(20) comment '设备保养日期',
  his_operator varchar(10) comment '设备保养人员签字',
  his_result char(1) comment '设备检查结果 1代表良好 2代表故障',
  his_note varchar(200) comment '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 生产计划表
create table manufacture_plan(
  m_id varchar(50) primary key comment '主键',
  m_sn varchar(50) comment '生产编号',
  m_pro varchar(50) comment '产品名称',
  m_start_date varchar(50) comment '生产计划开始日期',
  m_end_date varchar(50) comment '生产计划结束日期',
  m_count long comment '生产数量',
  order_id varchar(50) comment '订单id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 生产进度监控表
create table manufacture_plan_monitor(
  mpm_id varchar(50) primary key comment '主键',
  mpm_sn varchar(50) comment '生产监控编号',
  m_sn varchar(50) comment '生产计划编号',
  mpm_start_date varchar(50) comment '生产开始时间',
  mpm_end_date varchar(50) comment '生产结束时间',
  mpm_count long comment '已生产数量',
  mpm_wait_count long comment '待生产数量'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 生产进度监控物料消耗表
create table manufacture_material_needed(
  mmn_id varchar(50) primary key comment '主键',
  mmn_sn varchar(50) comment '对应生产监控编号',
  m_id varchar(50) comment '物料id',
  mmn_needed long comment '物料需求数量',
  mmn_get long comment '物料领取数量',
  mmn_consume long comment '物料消耗数量',
  mmn_requestor varchar(20) comment '领料员',
  mmn_apply varchar(20) comment '发料员',
  mmn_date varchar(20) comment '领料时间',
  mmn_status char(1) comment '领料状态 1，已领料，2 待领料'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 物料表
create table material(
  m_id varchar(50) primary key comment '主键',
  m_name varchar(50) comment '物料名称',
  m_sn varchar(50) comment '物料编号',
  m_count long comment '物料剩余数量',
  m_vendor_id varchar(50) comment '供应商id',
  m_note varchar(200) comment '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 物料采购表
create table material_pur_his(
  mph_id varchar(50) primary key comment '主键',
  mph_name varchar(50) comment '物料名称',
  mph_sn varchar(50) comment '物料编号',
  mph_count long comment '物料入库数量',
  m_vendor_name varchar(50) comment '物料供应商名称',
  m_vendor_address varchar(100) comment '物料供应商地址',
  m_vendor_tel varchar(100) comment '物料供应商联系电话',
  mph_operator varchar(10) comment '入库员',
  mph_date varchar(20) comment '入库日期',
  mph_note varchar(200) comment '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 物料消耗表
create table material_consume_his(
  mch_id varchar(50) primary key comment '主键',
  mch_name varchar(50) comment '物料名称',
  mch_sn varchar(50) comment '物料编号',
  mch_count long comment '物料领取数量',
  mch_operator varchar(10) comment '发料员',
  mch_requestor varchar(20) comment '领料员',
  mch_date varchar(20) comment '领料日期',
  mph_note varchar(200) comment '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 供应商管理
create table vendor(
  v_id varchar(50) primary key comment '主键',
  v_name varchar(100) comment '供应商名字',
  v_address varchar(200) comment '供应商地址',
  v_tel varchar(20) comment '供应商联系方式',
  v_publish varchar(10) comment '供应商法人代表',
  v_fullname varchar(20) comment '供应商全名',
  v_status char(1) comment '供应商状态 1代表正常 2代表终止合作',
  v_note  varchar(200) comment '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# url权限配置
CREATE TABLE urlConfigure (
  id varchar(40) NOT NULL primary key comment '主键'',
  url varchar(200) NOT NULL comment 'url',
  authority varchar(10) DEFAULT NULL comment '权限配置'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
