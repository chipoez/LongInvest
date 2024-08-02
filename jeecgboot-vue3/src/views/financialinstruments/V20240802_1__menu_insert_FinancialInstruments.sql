-- 注意：该页面对应的前台目录为views/financialinstruments文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024080208147590580', NULL, '金融商品', '/financialinstruments/financialInstrumentsList', 'financialinstruments/FinancialInstrumentsList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-08-02 20:14:58', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080208147590581', '2024080208147590580', '添加金融商品', NULL, NULL, 0, NULL, NULL, 2, 'financialinstruments:financial_instruments:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 20:14:58', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080208147590582', '2024080208147590580', '编辑金融商品', NULL, NULL, 0, NULL, NULL, 2, 'financialinstruments:financial_instruments:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 20:14:58', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080208147590583', '2024080208147590580', '删除金融商品', NULL, NULL, 0, NULL, NULL, 2, 'financialinstruments:financial_instruments:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 20:14:58', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080208147590584', '2024080208147590580', '批量删除金融商品', NULL, NULL, 0, NULL, NULL, 2, 'financialinstruments:financial_instruments:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 20:14:58', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080208147590585', '2024080208147590580', '导出excel_金融商品', NULL, NULL, 0, NULL, NULL, 2, 'financialinstruments:financial_instruments:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 20:14:58', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024080208147590586', '2024080208147590580', '导入excel_金融商品', NULL, NULL, 0, NULL, NULL, 2, 'financialinstruments:financial_instruments:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-08-02 20:14:58', NULL, NULL, 0, 0, '1', 0);