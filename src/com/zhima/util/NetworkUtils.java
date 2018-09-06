/**
 * Copyright (C) 2012 Greatech. All rights reserved.
 */
package com.zhima.util;

 import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
 
 public class NetworkUtils {
     /**
      * get inetAddress from operation system.this is not relation to platform.
      * @return instance of inetAddress if find InetAddress,return null if not find InetAddress
      *  or throw exception
      */
     public static InetAddress getLocalHostAddress() {
         try {
             for (Enumeration<NetworkInterface> nis = NetworkInterface
                     .getNetworkInterfaces(); nis.hasMoreElements();) {
                 NetworkInterface ni = nis.nextElement();
                 if (ni.isLoopback() || ni.isVirtual() || !ni.isUp())
                     continue;
                 for (Enumeration<InetAddress> ias = ni.getInetAddresses(); ias.hasMoreElements();) {
                     InetAddress ia = ias.nextElement();
                     if (ia instanceof Inet6Address) continue;
                     return ia;
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }     
 }
 