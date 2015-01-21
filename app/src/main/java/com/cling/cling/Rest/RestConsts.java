package com.cling.cling.Rest;

/**
 * Created by igor on 21.01.2015.
 */
public enum RestConsts {

    METHOD_NAME,
    POST,
    GET,
    PUT,
    DELETE,

    SUBJECT,
    USER {
        public String apiName() {return "user";}
    },
    PRODUCT {
        public String apiName() {return "goods";}
    },
    FEED {
        public String apiName() {return "feed";}
    },

    PARAMS;

    public String apiName() {
        return null;
    };
}
