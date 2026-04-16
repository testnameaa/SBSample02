package com.example.doma2test2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class JyuOrgDto implements Serializable {
    private static final long serialVersionUID = 2L; // 一意のIDを定義

    Integer jyuno;
    String toriCd;
    String toriNm;
    String braCd;
    String braNm;
    Date hatDate;
    Date syuDate;
    Date nouDate;
    String denNo;
    String tokCd;
    String tokNm;
    String ukebaCd;
    String sokCd;
    String sokNm;
    String haisoCd;
    String haisoNm;
    Integer denGyo;
    String rhinCd;
    String hinCd;
    String hinNm;
    Integer barasu;
    BigDecimal genTan;
    BigDecimal genKin;
    BigDecimal baiTan;
    BigDecimal baiKin;
    String outFlg;
    Timestamp insAt;
    Timestamp updAt;
}
