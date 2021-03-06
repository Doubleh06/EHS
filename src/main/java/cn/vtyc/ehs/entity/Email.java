package cn.vtyc.ehs.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Email extends BaseEntity {
    private String smtpAuth;
    private String transportProrocol;
    private String sendCharset;
    private String smtpPort;
    private String isSsl;
    private String host;
    private String authName;
    private String authPassword;
    private Integer smtpTimeout;
    private Integer isUsing;
    private String address;
    private String ehsEmail;

}
