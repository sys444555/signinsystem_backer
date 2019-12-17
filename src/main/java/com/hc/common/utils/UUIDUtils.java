package com.hc.common.utils;

import java.util.UUID;

/**
 *
 * @����: Charles
 * @ʱ��: 2017��6��7������7:05:14
 * @TODO�� ��������ַ����Ĺ����� uuid
 */
public class UUIDUtils {

    public static String getUUID(){
         return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println("��ʽǰ��UUID �� " + UUID.randomUUID().toString());
        System.out.println("��ʽ�����UUID ��" + getUUID());
    }
}
