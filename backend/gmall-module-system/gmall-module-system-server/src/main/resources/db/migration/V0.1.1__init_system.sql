-- 租户管理
INSERT INTO sys_tenant (id, code, name, contact_user_id, contact_name, contact_phone, license_number, address, domain, intro, description, websites, package_id, expire_time, account_count, status, create_time, update_time, creator, updater, deleted, deleted_time) VALUES ('1', 'gmall', 'GMALL', null, null, null, null, null, null, null, null, '127.0.0.1:5778,localhost:5778', null, null, null, null, '2026-06-18 18:02:42', '2026-06-18 18:02:42', 'system', 'system', false, null);

-- 用户管理
INSERT INTO sys_user (id, username, password, nickname, dept_id, remark, email, mobile, sex, avatar, status, login_ip, login_date, creator, create_time, updater, update_time, deleted, deleted_time, tenant_id) VALUES ('1', 'superAdmin', '$2a$04$Nd341QsF.HLmoGJTvsy5AO/HqMnNynmcxuh7PZj6kZ1OEQzHQK2DW', '超级管理员', '1', '超级管理员', 'admin@qq.com', '', 0, '', 1, '', null, 'system', '2026-06-18 18:03:34', 'system', '2026-06-18 18:03:34', false, null, 0);


-- 角色
BEGIN;
INSERT INTO sys_role (id, name, code, sort, status, type, remark, creator, create_time, updater, update_time, deleted, deleted_time, tenant_id) VALUES ('1', '系统管理员', 'SYS_ADMIN', 1, 0, 1, '系统管理员', 'system', '2026-04-25 00:10:00', 'system', '2026-04-25 00:10:46', false, null, 0);
INSERT INTO sys_role (id, name, code, sort, status, type, remark, creator, create_time, updater, update_time, deleted, deleted_time, tenant_id) VALUES ('2', '安全管理员', 'SEC_ADMIN', 2, 0, 1, '安全管理员', null, '2026-04-29 12:03:56', 'system','2026-04-29 04:06:12', false, null, 0);
INSERT INTO sys_role (id, name, code, sort, status, type, remark, creator, create_time, updater, update_time, deleted, deleted_time, tenant_id) VALUES ('3', '安全审计员', 'AUDITOR', 3, 0, 1, '安全审计员', null, '2026-04-29 12:07:23', 'system','2026-04-29 13:05:22', false, null, 0);
COMMIT;

-- 菜单
BEGIN;
INSERT INTO sys_menu (id, parent_id, ancestor_ids, code, name, title, path, component, icon, sort, type, permission, visible, status, keep_alive, affix, iframe, cached, redirect, breadcrumb, frame_src, redirect_path, remark, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2049074786869747713', '0', '', 'SystemManage', '系统管理', null, '/system', null, null, 0, 2, null, 1, 1, 0, 0, 0, 0, 0, 1, null, null, null, null, '2026-04-28 18:34:34', 'system', '2026-04-28 18:34:34', false, null);
INSERT INTO sys_menu (id, parent_id, ancestor_ids, code, name, title, path, component, icon, sort, type, permission, visible, status, keep_alive, affix, iframe, cached, redirect, breadcrumb, frame_src, redirect_path, remark, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2049078747731369985', '2049074786869747713', '', 'SystemSettings', '系统设置', null, '/system/settings', null, null, 0, 2, null, 1, 1, 0, 0, 0, 0, 0, 1, null, null, null, null, '2026-04-28 18:50:18', 'system', '2026-04-28 18:57:37', false, null);
INSERT INTO sys_menu (id, parent_id, ancestor_ids, code, name, title, path, component, icon, sort, type, permission, visible, status, keep_alive, affix, iframe, cached, redirect, breadcrumb, frame_src, redirect_path, remark, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2049083843496189953', '2049078747731369985', '2049078747731369985', 'MenuManage', '菜单管理', null, '/system/settings/menu-manage', null, null, 0, 2, null, 1, 1, 0, 0, 0, 0, 0, 1, null, null, null, null, '2026-04-28 19:10:33', 'system', '2026-04-28 19:10:33', false, null);
COMMIT;

-- 字典类型
BEGIN;
INSERT INTO sys_dict_type (id, code, name, type, status, remark, sort, creator, create_time, updater, update_time,deleted, deleted_time) VALUES ('2051863148739465217', 'sys_common_status', '状态', '1', 1, '状态列表', 1, null, '2026-05-06 11:14:31','system', '2026-05-06 04:37:10', false, null);
INSERT INTO sys_dict_type (id, code, name, type, status, remark, sort, creator, create_time, updater, update_time,deleted, deleted_time) VALUES ('2051882738303377409', 'sys_user_sex', '用户性别', '1', 1, '用户性别列表', 2, null, '2026-05-06 12:32:22','system', '2026-05-06 12:32:22', false, null);
INSERT INTO sys_dict_type (id, code, name, type, status, remark, sort, creator, create_time, updater, update_time,deleted, deleted_time) VALUES ('2051882957250240514', 'sys_notice_type', '通知类型', '1', 1, '通知类型列表', 3, null, '2026-05-06 12:33:14','system', '2026-05-06 12:33:14', false, null);
INSERT INTO sys_dict_type (id, code, name, type, status, remark, sort, creator, create_time, updater, update_time,deleted, deleted_time) VALUES ('2051883093904859138', 'sys_notice_status', '通知状态', '1', 1, '通知状态列表', 4, null, '2026-05-06 12:33:46','system', '2026-05-06 12:33:46', false, null);
INSERT INTO sys_dict_type (id, code, name, type, status, remark, sort, creator, create_time, updater, update_time,deleted, deleted_time) VALUES ('2051883221701107714', 'sys_yes_no', '系统是否', '1', 1, '系统是否列表', 5, null, '2026-05-06 12:34:17', 'system', '2026-05-06 04:37:24', false, null);
INSERT INTO sys_dict_type (id, code, name, type, status, remark, sort, creator, create_time, updater, update_time,deleted, deleted_time) VALUES ('2051883340647374849', 'sys_oper_type', '操作类型', '1', 1, '操作类型列表', 6, null, '2026-05-06 12:34:45','system', '2026-05-06 04:37:24', false, null);
INSERT INTO sys_dict_type (id, code, name, type, status, remark, sort, creator, create_time, updater, update_time,deleted, deleted_time) VALUES ('2051883634034745345', 'sys_grant_type', '授权类型', '1', 1, '认证授权类型', 7, null, '2026-05-06 12:35:55', 'system', '2026-05-06 04:37:24', false, null);
INSERT INTO sys_dict_type (id, code, name, type, status, remark, sort, creator, create_time, updater, update_time,deleted, deleted_time) VALUES ('2051883762766323713', 'sys_device_type', '设备类型', '1', 1, '客户端设备类型', 8, null, '2026-05-06 12:36:26', 'system', '2026-05-06 04:37:24', false, null);
COMMIT;

-- 字典数据
BEGIN;
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051954604439314433', '2051863148739465217', 'sys_common_status', '0', '1', '启用', 1, '启用', 1, 'system', '2026-05-06 17:17:56', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051970251714043905', '2051863148739465217', 'sys_common_status', '0', '0', '禁用', 1, '禁用', 2, 'system', '2026-05-06 18:20:06', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051972898680578049', '2051883634034745345', 'sys_grant_type', '0', 'sms', '短信认证', 1, '短信认证', 1, 'system', '2026-05-06 18:30:38', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051972980247207937', '2051883634034745345', 'sys_grant_type', '0', 'email', '邮件认证', 1, '邮件认证', 2, 'system', '2026-05-06 18:30:57', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051973080767897601', '2051883634034745345', 'sys_grant_type', '0', 'miniapp', '小程序认证', 1, '小程序认证', 0, 'system', '2026-05-06 18:31:21', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051973180286148609', '2051883634034745345', 'sys_grant_type', '0', 'social', '三方登录认证', 1, '三方登录认证', 0, 'system', '2026-05-06 18:31:45', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051973267905159170', '2051883762766323713', 'sys_device_type', '0', 'PC', 'PC', 1, 'PC', 0, 'system', '2026-05-06 18:32:06', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051973329758560258', '2051883762766323713', 'sys_device_type', '0', 'android', '安卓', 1, 'android', 0, 'system', '2026-05-06 18:32:20', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051973418375815170', '2051883762766323713', 'sys_device_type', '0', 'iOS', 'iOS', 1, 'iOS', 0, 'system', '2026-05-06 18:32:41', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051973564610224130', '2051883762766323713', 'sys_device_type', '0', 'miniapp', '小程序', 1, '小程序', 0, 'system', '2026-05-06 18:33:16', 'system', '2026-05-06 10:35:30', false, null);
INSERT INTO sys_dict_data (id, type_id, type_code, parent_id, data_code, data_name, status, remark, sort, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2051973644675293185', '2051883762766323713', 'sys_device_type', '0', 'chat', '对话', 1, '对话', 0, 'system', '2026-05-06 18:33:35', 'system', '2026-05-06 10:35:30', false, null);
COMMIT;

BEGIN;
INSERT INTO sys_oauth2_client (id, client_id, client_secret, name, logo, description, access_token_validity_seconds, refresh_token_validity_seconds, redirect_uris, authorized_grant_types, scopes, auto_approve_scopes, authorities, resource_ids, additional_information, status, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('1', 'default', 'admin123', 'Gmall', 'http://127.0.0.1:8080/logo.pg', '我是描述', 200, 1800, '["https://www.iocoder.cn","https://doc.iocoder.cn"]', '["password","authorization_code","implicit","refresh_token","client_credentials"]', '["user.read","user.write"]', '[]', '["user.read","user.write"]', '[]', '{}', 1, 'system', '2026-06-05 20:41:47', 'system', '2026-06-05 20:42:26', false, null);
COMMIT;



