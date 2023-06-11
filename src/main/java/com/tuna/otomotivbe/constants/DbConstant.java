package com.tuna.otomotivbe.constants;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class DbCostant {

    //tbl
    public static final String TBL_USERS = "USERS";
    public static final String TBL_ROLE = "ROLES";
    public static final String TBL_USER_ROLES = "USER_ROLES";
    public static final String TBL_PURCHASES = "PURCHASES";
    public static final String TBL_PRODUCT = "PRODUCT";

    //col
    public static final String COL_USER_NAME = "USER_NAME";
    public static final String COL_ID = "ID";
    public static final String COL_USER_FIRST_NAME = "USER_FIRST_NAME";
    public static final String COL_USER_LAST_NAME = "USER_LAST_NAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_IS_EMAIL_VERIFIED = "IS_EMAIL_VERIFIED";
    public static final String COL_VERIFICATION_TOKEN = "VERIFICATION_TOKEN";
    public static final String COL_USER_ID = "USER_ID";
    public static final String COL_ROLE_ID = "ROLE_ID";
    public static final String COL_PRODUCT_ID = "PRODUCT_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_LOGIN_ATTEMPTS = "LOGIN_ATTEMPTS";
    public static final String COL_BLOCKED = "BLOCKED";
    public static final String COL_PURCHASE_DATE = "PURCHASE_DATE";
    public static final String COL_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_STOCK = "STOCK";

}
