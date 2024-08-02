-- 注意：该页面对应的前台目录为views/financialdata文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024080209231240010', NULL, '金融商品数据表', '/financialdata/financialDataList', 'financialdata/FinancialDataList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-08-02 21:23:01', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080209231240011', '2024080209231240010', '添加金融商品数据表', NULL, NULL, 0, NULL, NULL, 2, 'financialdata:financial_data:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 21:23:01', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080209231240012', '2024080209231240010', '编辑金融商品数据表', NULL, NULL, 0, NULL, NULL, 2, 'financialdata:financial_data:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 21:23:01', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080209231240013', '2024080209231240010', '删除金融商品数据表', NULL, NULL, 0, NULL, NULL, 2, 'financialdata:financial_data:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 21:23:01', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080209231240014', '2024080209231240010', '批量删除金融商品数据表', NULL, NULL, 0, NULL, NULL, 2, 'financialdata:financial_data:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 21:23:01', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080209231240015', '2024080209231240010', '导出excel_金融商品数据表', NULL, NULL, 0, NULL, NULL, 2, 'financialdata:financial_data:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 21:23:01', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080209231240016', '2024080209231240010', '导入excel_金融商品数据表', NULL, NULL, 0, NULL, NULL, 2, 'financialdata:financial_data:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 21:23:01', NULL, NULL, 0, 0, '1', 0);