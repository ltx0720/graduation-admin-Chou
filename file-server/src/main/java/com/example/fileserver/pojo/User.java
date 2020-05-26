package com.example.fileserver.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * @Author ltx
 * @Date 21:53 2020/4/12
 *
 * 用户
 */
public class User implements UserDetails {
   private int school_id;
   private int department_id;
   private int identify_id;
   private String role;
   private String name;
   private String phone;
   private String mail;

   public User(String role) {
      this.role = role;
   }

   public User() {}

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return Arrays.asList(new SimpleGrantedAuthority(this.role));
   }

   @Override
   public String getPassword() {
      return "";
   }

   @Override
   public String getUsername() {
      return "";
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }


   public int getSchool_id() {
      return school_id;
   }

   public void setSchool_id(int school_id) {
      this.school_id = school_id;
   }

   public int getDepartment_id() {
      return department_id;
   }

   public void setDepartment_id(int department_id) {
      this.department_id = department_id;
   }

   public int getIdentify_id() {
      return identify_id;
   }

   public void setIdentify_id(int identify_id) {
      this.identify_id = identify_id;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getMail() {
      return mail;
   }

   public void setMail(String mail) {
      this.mail = mail;
   }
}
