package org.usersystem.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StaffInfo {
  int id;
  String staffId;
  String staffName;
  String staffSex;
  String staffTel;
  String staffPos;
  Date staffEntry;
  String staffCover;
  String describe;
  String password;
  Date createTime;


}
