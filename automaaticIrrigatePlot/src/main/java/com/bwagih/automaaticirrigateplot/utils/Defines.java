package com.bwagih.automaaticirrigateplot.utils;

public final class Defines {

    // ConstantStrings for JWT Security
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 60  *10;//10 hours
    public static final String SIGNING_KEY = "secretkey";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";


    //  ConstantStrings for app

    public static final String TYPE_FIELD_TYPE_SPEC = "type";
    public static final String AREA_FIELD_PLOT_SPEC = "area";
    public static final String LIKE_OP = "%";
    public static final Double ZERO = Double.valueOf("0.00");
    public static final String MIN_VALUE_MSG = "min value 1";
}
