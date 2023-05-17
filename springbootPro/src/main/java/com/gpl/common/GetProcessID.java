package com.gpl.common;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class GetProcessID {
    public static void main(String[] args) {
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();

        // 获取代表正在运行的Java虚拟机的名称。
        //它返回类似于6460 @ AURORA的信息。凡价值
        // @符号之前是PID。
        String jvmName = bean.getName();
        System.out.println("Name = " + jvmName);

        // 通过拆分由返回的字符串来提取PID
        // bean.getName()方法。
        long pid = Long.valueOf(jvmName.split("@")[0]);
        System.out.println("PID  = " + pid);
    }
}