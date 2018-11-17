-- we don't know how to generate schema erp_system (class Schema) :(
create table custom
(
	custom_id varchar(50) not null comment '主键'
		primary key,
	custom_name varchar(100) not null comment '客户名字',
	custom_address varchar(200) null comment '客户地址',
	custom_code varchar(20) null comment '客户编号',
	custom_tel varchar(20) null comment '客户电话',
	custom_publish varchar(20) null comment '客户法人',
	custom_fullname varchar(20) null comment '客户全名',
	custom_status char null comment '客户状态  1代表有效   2代表无效',
	constraint custom_custom_code_uindex
		unique (custom_code)
)
comment '客户表'
;

create table department
(
	d_id varchar(50) not null comment '主键'
		primary key,
	d_name varchar(50) null comment '部门名称',
	d_mamager varchar(50) null comment '部门主管',
	d_duty varchar(200) null comment '部门职责'
)
comment '部门表'
;

create table device
(
	device_id varchar(50) not null comment '主键'
		primary key,
	device_name varchar(100) null comment '设备名称',
	device_pur_date varchar(20) null comment '设备采购日期',
	device_price int null comment '设备单价',
	device_code varchar(100) null comment '设备编号',
	device_vendor varchar(100) null comment '设备供应商',
	device_vendor_tel varchar(100) null comment '供应商联系电话',
	device_used_period varchar(20) null comment '设备使用截止日期',
	device_note varchar(200) null comment '备注',
	device_status char null comment '设备状态，1代表良好，2代表待维修，3代表维修OK'
)
comment '设备表'
;

create table device_maintain_his
(
	his_id varchar(50) not null comment '主键'
		primary key,
	his_device_code varchar(100) null comment '设备编号',
	his_date varchar(20) null comment '设备保养日期',
	his_operator varchar(10) null comment '设备保养人员签字',
	his_result char null comment '设备检查结果 1代表良好 2代表故障',
	his_note varchar(200) null comment '备注'
)
comment '设备保养记录表'
;

create table manufacture_order
(
	mo_id varchar(50) not null comment '主键'
		primary key,
	mo_sn varchar(50) null comment '生产工单编号',
	mo_mp_sn varchar(50) null comment '生产计划编号',
	mo_start_date varchar(50) null comment '生产开始时间',
	mo_end_date varchar(50) null comment '生产结束时间',
	mo_count int null comment '已生产数量',
	mo_wait_count int null comment '计划生产数量',
	mo_status char null comment '状态 1进行中 2已完工',
	constraint manufacture_order_mo_sn_uindex
		unique (mo_sn)
)
comment '生产工单表'
;

create table manufacture_plan
(
	mp_id varchar(50) not null comment '主键'
		primary key,
	mp_sn varchar(50) null comment '生产计划编号',
	mp_pro_code varchar(50) null comment '产品编号',
	mp_start_date varchar(50) null comment '生产计划开始日期',
	mp_end_date varchar(50) null comment '生产计划结束日期',
	mp_count int default '0' null comment '计划生产数量',
	mp_order_id varchar(50) null comment '订单id',
	mp_status char null comment '1代表生成进行中，2代表生产完成',
	constraint manufacture_plan_mp_sn_uindex
		unique (mp_sn)
)
comment '生产计划表'
;

create table material
(
	m_id varchar(50) not null comment '主键'
		primary key,
	m_name varchar(50) null comment '物料名称',
	m_sn varchar(50) null comment '物料编号',
	m_count int null comment '仓库物料剩余数量',
	m_note varchar(200) null comment '备注',
	m_status char null comment '物料状态，1 ,充足  2,短缺',
	constraint material_m_sn_uindex
		unique (m_sn)
)
comment '物料表'
;

create table material_consume
(
	mc_id varchar(50) not null comment '主键'
		primary key,
	mc_mp_sn varchar(50) null comment '对应生产计划编号',
	mc_mo_sn varchar(50) null comment '对应生产工单编号',
	mc_m_sn varchar(50) null comment '物料编号',
	mc_count_needed int null comment '物料需求数量',
	mc_count_indeed int null comment '物料实际领取数量',
	mc_requestor varchar(20) null comment '领料员',
	mc_operator varchar(20) null comment '发料员',
	mc_date varchar(20) null comment '领料时间',
	mc_status char null comment '领料状态 1,可领料 2,已领料'
)
comment '领料表'
;

create table material_purchase
(
	mph_id varchar(50) not null comment '主键'
		primary key,
	mph_name varchar(50) null comment '物料名称',
	mph_sn varchar(50) null comment '物料编号',
	mph_price float null comment '物料单价',
	mph_count int null comment '物料入库数量',
	mph_vendor_id varchar(50) null comment '供应商id',
	mph_vendor_code varchar(50) null comment '供应商物料编号',
	mph_sender varchar(100) null comment '送货人',
	mph_operator varchar(10) null comment '入库员',
	mph_date varchar(20) null comment '入库日期',
	mph_note varchar(200) null comment '备注'
)
comment '采购表'
;

create table module
(
	mid varchar(50) not null comment '主键'
		primary key,
	mname varchar(255) null comment '权限名称'
)
comment '权限表'
;

create table orders
(
	o_id varchar(50) not null comment '主键'
		primary key,
	o_modifier varchar(20) null comment '修改人',
	o_creator varchar(20) null comment '创建人',
	o_no varchar(50) null comment '订单号',
	o_com_no varchar(50) null comment '公司单号',
	o_product_code varchar(10) null comment '产品编号',
	o_count int null comment '订单数量',
	o_indeed_count int null comment '实际出货数量',
	o_create_date varchar(50) null comment '下单日期',
	o_shipment_date varchar(50) null comment '出货日期',
	o_custom_name varchar(50) null comment '客户名',
	o_pay varchar(20) null comment '结算单价',
	o_pay_category varchar(20) null comment '币种',
	o_exchange_rate varchar(20) null comment '汇率',
	o_shipment_method varchar(20) null comment '交货方式',
	o_contacts varchar(20) null comment '联系人',
	o_tel varchar(20) null comment '联系人电话',
	o_address varchar(100) null comment '客户地址',
	o_salesman varchar(20) null comment '业务员名称',
	o_salesman_depart varchar(20) null comment '业务员部门',
	o_salesman_contact varchar(20) null comment '业务员联系方式',
	o_auditor varchar(20) null comment '审核人',
	o_audit_date varchar(20) null comment '审核日期',
	o_note varchar(100) null comment '备注',
	o_status char null comment '订单状态 1代表待审核 2代表审核未通过  3代表待出货 4代表已安排出货',
	constraint orders_o_no_uindex
		unique (o_no)
)
comment '订单表'
;

create table product
(
	pro_id varchar(50) not null comment '主键'
		primary key,
	pro_code varchar(100) null comment '产品编号',
	pro_name varchar(100) null comment '产品名称',
	pro_count int null comment '产品当前库存数量',
	pro_price float null comment '产品单价',
	pro_image varchar(200) null comment '产品图片的url',
	pro_note varchar(200) null comment '产品介绍',
	pro_status char null comment '产品状态，1代表已量产，2代表已停产',
	constraint pro_code
		unique (pro_code)
)
comment '产品管理表'
;

create table profile
(
	pid varchar(50) not null comment '主键'
		primary key,
	p_uid varchar(50) not null comment '用户id',
	p_indate varchar(20) not null comment '入职日期',
	p_faredate varchar(20) not null comment '离职日期',
	p_major varchar(50) null comment '专业',
	p_id varchar(20) null comment '身份证号',
	p_sex char null comment '性别',
	p_edu varchar(10) null comment '教育程度',
	p_tel varchar(15) null comment '联系方式',
	p_name varchar(20) null comment '中文名或别名',
	p_wechat varchar(50) null comment '微信'
)
comment '用户信息表'
;

create table role
(
	rid varchar(50) not null comment '主键'
		primary key,
	rname varchar(255) null comment '角色名'
)
comment '角色表'
;

create table role_module
(
	rid varchar(50) null comment '角色id',
	mid varchar(50) null comment '权限id'
)
comment '角色权限分配表'
;

create index m_id
	on role_module (mid)
;

create index r_id
	on role_module (rid)
;

create table shipment
(
	s_id varchar(50) not null comment '主键'
		primary key,
	s_order_no varchar(50) null comment '订单编号',
	s_pro_code varchar(50) null comment '产品编号',
	s_ship_count int null comment '出货数量',
	s_auditor varchar(10) null comment '审核人',
	s_surer varchar(10) null comment '确认人',
	s_audit_date varchar(20) null comment '审核日期',
	s_ship_date varchar(20) null comment '出货日期',
	s_status char null comment '1,待审核 2,审核不通过 3,待确认 4,已安排出货'
)
comment '出货表'
;

create table storage
(
	sto_id varchar(50) not null comment '主键'
		primary key,
	sto_mp_sn varchar(50) null comment '生产计划编号',
	sto_mo_sn varchar(50) null comment '生产工单标号',
	sto_pro_code varchar(100) null comment '入库产品编号',
	sto_indeed_num int null comment '入库数量',
	sto_real_date varchar(20) null comment '入库日期',
	sto_surer varchar(10) null comment '确认人',
	sto_sender varchar(10) null comment '送料员',
	sto_status char null comment '1,待确认 2,入库失败 3,入库成功 '
)
comment '成品入库表'
;

create table url_configure
(
	id varchar(40) not null comment '主键'
		primary key,
	url varchar(200) not null comment 'url',
	authority varchar(10) null comment '权限配置'
)
comment 'url权限配置表'
;

create table user
(
	id varchar(50) not null comment '主键'
		primary key,
	uname varchar(50) not null comment '用户名',
	upass varchar(50) not null comment '用户密码',
	departId varchar(50) not null comment '部门id',
	status char not null comment '用户状态 1代表正常  2代表锁定',
	constraint user_uname_uindex
		unique (uname)
)
comment '用户表'
;

create table user_role
(
	uid varchar(50) null comment '用户id',
	rid varchar(50) null comment '角色id'
)
comment '用户角色分配表'
;

create index r_fk
	on user_role (rid)
;

create index u_fk
	on user_role (uid)
;

create table vendor
(
	v_id varchar(50) not null comment '主键'
		primary key,
	v_name varchar(100) null comment '供应商名字',
	v_address varchar(200) null comment '供应商地址',
	v_tel varchar(20) null comment '供应商联系方式',
	v_publish varchar(10) null comment '供应商法人代表',
	v_fullname varchar(20) null comment '供应商全名',
	v_status char null comment '供应商状态 1代表正常 2代表终止合作',
	v_note varchar(200) null comment '备注'
)
comment '供应商管理表'
;



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


