package com.hc.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc:数据导出，生成excel文件时的列名称集合
 * @author:
 * @time:
 */
public class ColumnTitleMap {
    private Map<String, String> columnTitleMap = new HashMap<String, String>();
    private ArrayList<String> titleKeyList = new ArrayList<String> ();

    public ColumnTitleMap(String datatype) {
        switch (datatype) {
            case "house":
                initUserInfoColu();
                initUserInfoTitleKeyList();
                break;
            default:
                break;
        }

    }
    /**
     * mysql用户表需要导出字段--显示名称对应集合
     */
    private void initUserInfoColu() {
        columnTitleMap.put("id", "序号");
        columnTitleMap.put("name", "经纪人");
        columnTitleMap.put("phone", "经纪人手机号");
        columnTitleMap.put("identity_number", "经纪人身份证");
        columnTitleMap.put("client_name", "推荐客户姓名");
        columnTitleMap.put("client_phone", "客户手机号");
        columnTitleMap.put("client_identity_card", "客户身份证号");
        columnTitleMap.put("client_arrage_time", "预计到访时间");
        columnTitleMap.put("status", "状态");
    }

    /**
     * mysql用户表需要导出字段集
     */
    private void initUserInfoTitleKeyList() {
        titleKeyList.add("id");
        titleKeyList.add("name");
        titleKeyList.add("phone");
        titleKeyList.add("identity_number");
        titleKeyList.add("client_name");
        titleKeyList.add("client_phone");
        titleKeyList.add("client_identity_card");
        titleKeyList.add("client_arrage_time");
        titleKeyList.add("status");
    }

    public Map<String, String> getColumnTitleMap() {
        return columnTitleMap;
    }

    public ArrayList<String> getTitleKeyList() {
        return titleKeyList;
    }
}

