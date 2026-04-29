INSERT INTO sys_role (id, name, code, sort, status, type, remark, creator, create_time, updater, update_time, deleted,
                      deleted_time, tenant_id)
VALUES ('1', '系统管理员', 'admin', 1, 0, 1, '系统管理员', 'system', '2026-04-25 00:10:00', 'system',
        '2026-04-25 00:10:46', false, null, 0);
INSERT INTO sys_role (id, name, code, sort, status, type, remark, creator, create_time, updater, update_time, deleted,
                      deleted_time, tenant_id)
VALUES ('2', '安全管理员', 'SEC_ADMIN', 2, 0, 1, '安全管理员', null, '2026-04-29 12:03:56', 'system',
        '2026-04-29 04:06:12', false, null, 0);
INSERT INTO sys_role (id, name, code, sort, status, type, remark, creator, create_time, updater, update_time, deleted,
                      deleted_time, tenant_id)
VALUES ('3', '安全审计员', 'AUDITOR', 3, 0, 1, '安全审计员', null, '2026-04-29 12:07:23', 'system',
        '2026-04-29 13:05:22', false, null, 0);


INSERT INTO sys_menu (id, parent_id, ancestor_ids, code, name, title, path, component, icon, sort, type, permission, visible, status, keep_alive, affix, iframe, cached, redirect, breadcrumb, frame_src, redirect_path, remark, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2049074786869747713', '0', '', 'SystemManage', '系统管理', null, '/system', null, null, 0, 2, null, 1, 1, 0, 0, 0, 0, 0, 1, null, null, null, null, '2026-04-28 18:34:34', 'system', '2026-04-28 18:34:34', false, null);
INSERT INTO sys_menu (id, parent_id, ancestor_ids, code, name, title, path, component, icon, sort, type, permission, visible, status, keep_alive, affix, iframe, cached, redirect, breadcrumb, frame_src, redirect_path, remark, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2049078747731369985', '2049074786869747713', '', 'SystemSettings', '系统设置', null, '/system/settings', null, null, 0, 2, null, 1, 1, 0, 0, 0, 0, 0, 1, null, null, null, null, '2026-04-28 18:50:18', 'system', '2026-04-28 18:57:37', false, null);
INSERT INTO sys_menu (id, parent_id, ancestor_ids, code, name, title, path, component, icon, sort, type, permission, visible, status, keep_alive, affix, iframe, cached, redirect, breadcrumb, frame_src, redirect_path, remark, creator, create_time, updater, update_time, deleted, deleted_time) VALUES ('2049083843496189953', '2049078747731369985', '2049078747731369985', 'MenuManage', '菜单管理', null, '/system/settings/menu-manage', null, null, 0, 2, null, 1, 1, 0, 0, 0, 0, 0, 1, null, null, null, null, '2026-04-28 19:10:33', 'system', '2026-04-28 19:10:33', false, null);

