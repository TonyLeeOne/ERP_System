# 用户表
create table user (
  id varchar(50) not null primary key comment ''主键'',
  uname varchar(50) not null comment ''用户名'',
  upass varchar(50) not null comment ''用户密码'',
  departId varchar(50) not null comment ''部门id'',
  status char(1) not null comment ''用户状态 1代表正常  2代表锁定''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 角色表
CREATE TABLE role (
  rid varchar(50) NOT NULL comment ''主键'',
  rname varchar(255) DEFAULT NULL comment ''角色名'',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 用户角色关联表
CREATE TABLE user_role (
  uid varchar(50) DEFAULT NULL comment ''用户id'',
  rid varchar(50) DEFAULT NULL comment ''角色id'',
  KEY `u_fk` (`uid`),
  KEY `r_fk` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 权限表
CREATE TABLE module (
  mid varchar(50) NOT NULL comment ''主键'',
  mname varchar(255) DEFAULT NULL comment ''权限名称'',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 角色权限关联表
CREATE TABLE role_module (
  rid varchar(50) DEFAULT NULL comment ''角色id'',
  mid varchar(50) DEFAULT NULL comment ''权限id'',
  KEY `r_id` (`rid`),
  KEY `m_id` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 部门表
create table department(
  d_id varchar(50) primary key comment ''主键'',
  d_name varchar(50) comment ''部门名称'',
  d_mamager varchar(50) comment ''部门主管'',
  d_duty varchar(200) comment ''部门职责''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 用户信息表
create table profile(
  pid varchar(50) primary key not null comment ''主键'',
  p_uid varchar(50) not null comment ''用户id'',
  p_indate varchar(20) not null comment ''入职日期'',
  p_faredate varchar(20) not null comment ''离职日期'',
  p_major varchar(50)  comment ''专业'',
  p_id varchar(20) comment ''身份证号'',
  p_sex char(1) comment ''性别'',
  p_edu varchar(10) comment ''教育程度'',
  p_tel varchar(15) comment ''联系方式'',
  p_name varchar (20) comment ''中文名或别名'',
  p_wechat varchar (50) comment ''微信''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 客户表
create table custom(
  custom_id varchar(50) primary key not null comment ''主键'',
  custom_name varchar(100) not null comment ''客户名字'',
  custom_address varchar(200) comment ''客户地址'',
  custom_code varchar(20) comment ''客户编号'',
  custom_tel varchar(20) comment ''客户电话'',
  custom_publish varchar(20) comment ''客户法人'',
  custom_fullname varchar(20) comment ''客户全名'',
  custom_status char(1) comment ''客户状态  1代表有效   2代表无效''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 产品表
create table product(
  pro_id  varchar(50) not null primary key comment ''主键'',
  pro_code varchar(100) comment ''产品编号'',
  pro_name varchar(100) comment ''产品名称'',
  pro_count int comment ''产品当前库存数量'',
  pro_price float comment ''产品单价'',
  pro_image varchar(200) comment ''产品图片的url'',
  pro_note varchar(200) comment ''产品介绍'',
  pro_status char(1) comment ''产品状态，1代表已量产，2代表已停产'',
  unique index pro_code (pro_code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 设备表
create table device(
  device_id varchar(50) not null primary key comment ''主键'',
  device_name varchar(100) comment ''设备名称'',
  device_pur_date varchar(20) comment ''设备采购日期'',
  device_price int comment ''设备单价'',
  device_code varchar(100) comment ''设备编号'',
  device_vendor varchar(100) comment ''设备供应商'',
  device_vendor_tel varchar(100) comment ''供应商联系电话'',
  device_used_period varchar(20) comment ''设备使用截止日期'',
  device_note varchar(200) comment ''备注'',
  device_status char(1) comment ''设备状态，1代表良好，2代表待维修，3代表维修OK''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 设备保养表
create table device_maintain_his(
  his_id varchar(50) primary key comment ''主键'',
  his_device_code varchar(100) comment ''设备编号'',
  his_date varchar(20) comment ''设备保养日期'',
  his_operator varchar(10) comment ''设备保养人员签字'',
  his_result char(1) comment ''设备检查结果 1代表良好 2代表故障'',
  his_note varchar(200) comment ''备注''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 生产计划表
create table manufacture_plan(
  mp_id varchar(50) primary key comment ''主键'',
  mp_sn varchar(50) comment ''生产计划编号'',
  mp_pro_code varchar(50) comment ''产品编号'',
  mp_start_date varchar(50) comment ''生产计划开始日期'',
  mp_end_date varchar(50) comment ''生产计划结束日期'',
  mp_count int comment ''计划生产数量'',
  mp_order_id varchar(50) comment ''订单id'',
  mp_status char(1) comment ''1代表生成进行中，2代表生产完成''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 生产工单
create table manufacture_order(
  mo_id varchar(50) primary key comment ''主键'',
  mo_sn varchar(50) comment ''生产工单编号'',
  mo_mp_sn varchar(50) comment ''生产计划编号'',
  mo_start_date varchar(50) comment ''生产开始时间'',
  mo_end_date varchar(50) comment ''生产结束时间'',
  mo_count int comment ''已生产数量'',
  mo_wait_count int comment ''待生产数量'',
  mo_status char (1) comment ''状态 1进行中 2已完工''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#物料消耗表/领料表
create table manufacture_material_consume(
  mmc_id varchar(50) primary key comment ''主键'',
  mmc_mp_sn varchar(50) comment ''对应生产计划编号'',
  mmc_mo_sn varchar(50) comment ''对应生产工单编号'',
  mmc_m_sn varchar(50) comment ''物料编号'',
  mmc_count_needed int comment ''物料需求数量'',
  mmc_count_indeed int comment ''物料实际领取数量'',
  mmc_requestor varchar(20) comment ''领料员'',
  mmc_operator varchar(20) comment ''发料员'',
  mmc_date varchar(20) comment ''领料时间'',
  mmc_status char(1) comment ''领料状态 1，待领料，2 已待领料''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 物料表
create table material(
  m_id varchar(50) primary key comment ''主键'',
  m_name varchar(50) comment ''物料名称'',
  m_sn varchar(50) comment ''物料编号'',
  m_count int comment ''仓库物料剩余数量'',
  m_note varchar(200) comment ''备注'',
  m_status char(1) comment ''物料状态，1代表可用物料，2代表禁止使用''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 物料采购表
create table material_pur_his(
  mph_id varchar(50) primary key comment ''主键'',
  mph_name varchar(50) comment ''物料名称'',
  mph_sn varchar(50) comment ''物料编号'',
  mph_price float comment ''物料单价'',
  mph_count int comment ''物料入库数量'',
  mph_vendor_id varchar (50) comment ''供应商id'',
  mph_vendor_code varchar (50) comment ''供应商物料编号'',
  mph_sender varchar(100) comment ''送货人'',
  mph_operator varchar(10) comment ''入库员'',
  mph_date varchar(20) comment ''入库日期'',
  mph_note varchar(200) comment ''备注''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



# 供应商管理
create table vendor(
  v_id varchar(50) primary key comment ''主键'',
  v_name varchar(100) comment ''供应商名字'',
  v_address varchar(200) comment ''供应商地址'',
  v_tel varchar(20) comment ''供应商联系方式'',
  v_publish varchar(10) comment ''供应商法人代表'',
  v_fullname varchar(20) comment ''供应商全名'',
  v_status char(1) comment ''供应商状态 1代表正常 2代表终止合作'',
  v_note  varchar(200) comment ''备注''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- # 新增url权限配置
CREATE TABLE urlConfigure (
  id varchar(40) NOT NULL primary key comment ''主键'',
  url varchar(200) NOT NULL comment ''url'',
  authority varchar(10) DEFAULT NULL comment ''权限配置'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 固定权限值
# 物料
insert into module values(''1f22d285fa534142a11e134502420372'',''material:add'');
insert into module values(''2e227af26ebe43baa15064af33bf2bbf'',''material:update'');
insert into module values(''b57eab5f57d24c009c05faafdcf04928'',''material:delete'');
insert into module values(''a9afdc5ed43d4188b2fb197daed79a0b'',''material:query'');
# 订单
insert into module values(''1706f112d3a642ddb92f2667b851858b'',''order:add'');
insert into module values(''a418f73f74c741b3bcc2f2434bbdf50b'',''order:update'');
insert into module values(''e6dd5eaa3aaf4f72a2f02da80310989b'',''order:delete'');
insert into module values(''94df464babec4fef9269716aa61b1c0f'',''order:query'');
# 客户
insert into module values(''fe721445594845b4a4bb1e6fcd9a4f13'',''custom:add'');
insert into module values(''4de7c371565c4b868b1bd7ca644793c9'',''custom:update'');
insert into module values(''7307e5d4b7c54ee58af76b2820e1bdc8'',''custom:delete'');
insert into module values(''c652f17bbdd6471fa818b0c21f0b801a'',''custom:query'');
# 供应商
insert into module values(''0ffa99c1c8df4332853105087e4b4fbd'',''vendor:add'');
insert into module values(''9f64d1e79ee7419aa8226dd023125164'',''vendor:query'');
insert into module values(''ef3c036ffe7447d5b329b2699e30f0ed'',''vendor:delete'');
insert into module values(''281ea6f66f85470485fd523716100d5c'',''vendor:update'');
# 生产计划
insert into module values(''5e518fe53c3046db9ab3c544534d0722'',''manfacturePlan:add'');
insert into module values(''602429a9ac1e4bda8b732f3767acbf11'',''manfacturePlan:delete'');
insert into module values(''c4332a8289994f7eb274403d2f6cc040'',''manfacturePlan:query'');
insert into module values(''b1361cf6253b445d95ecba281246a11a'',''manfacturePlan:update'');
# 生产计划进度
insert into module values(''aad73fbdca4c4a9a91ab514a591dbe77'',''manfactureProgress:add'');
insert into module values(''9b862a57762e41808e27fe03679051d1'',''manfactureProgress:delete'');
insert into module values(''be28d24db96e4353bcf4b4944b70ebbf'',''manfactureProgress:update'');
insert into module values(''218d29a3500a4f61992f1fe197bce142'',''manfactureProgress:query'');

# 用户管理
insert into module values(''5f13dc4a078143c19113069aa60e49a8'',''user:add'');
insert into module values(''7071e74f76f1497ab3c0c2b8069dc741'',''user:delete'');
insert into module values(''136be568b62e40519f7b72250dac18e7'',''user:update'');
insert into module values(''da2786008f7842b39e1fb17ff6b7dcad'',''user:query'');

# 用户角色管理
insert into module values(''a4e6f7a2c46e4ae99620b204f8ec4da8'',''role:add'');
insert into module values(''0f63060888d44433b07ae23fd7ca3e41'',''role:delete'');
insert into module values(''495ba188daa446e380c5448a94073d41'',''role:update'');
insert into module values(''aa83386d79f74f4fa40917b4526ca22a'',''role:query'');

# 部门管理
insert into module values(''18fa28ba77a148f8b5e24cffdb0ec44e'',''department:add'');
insert into module values(''0a695d95e5e6493b846a44e9eda90ab7'',''department:delete'');
insert into module values(''4906dd6502ae4d139ffc50f3f56e4c65'',''department:update'');
insert into module values(''fcc04b913a8d457ab5eef35c27e3ccbe'',''department:query'');

# 设备管理
insert into module values(''363007d98c5a443bbb9ded207602c0ce'',''device:add'');
insert into module values(''920558b684164340a9ed454dac3d5198'',''device:delete'');
insert into module values(''cb2b67025dbf4d62b6c63d992685f80d'',''device:update'');
insert into module values(''cd73ba81f5974b19899bbee31b9665e1'',''device:query'');


#超级系统管理员，默认拥有所有权限
insert into role values (''7a03476475414a048c05917b3415bfb1'',''system_admin'');

insert into role_module values ('7a03476475414a048c05917b3415bfb1','363007d98c5a443bbb9ded207602c0ce');


