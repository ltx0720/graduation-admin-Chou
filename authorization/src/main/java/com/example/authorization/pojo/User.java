package com.example.authorization.pojo;

/**
 * @Author ltx
 * @Date 21:53 2020/4/12
 *
 * 用户
 */
public class User {
   private int id;
   private byte roles;
   private int school_id;
   private int department_id;
   private long identify_id;
   private String name;
   private String phone;
   private String mail;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public byte getRoles() {
      return roles;
   }

   public void setRoles(byte roles) {
      this.roles = roles;
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

   public long getIdentify_id() {
      return identify_id;
   }

   public void setIdentify_id(long identify_id) {
      this.identify_id = identify_id;
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
