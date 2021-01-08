package com.tianhe.framework.netty.online.codec.kryo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by tianhe on 2019/11/6.
 */
@Data
public class Request {

    private String serviceCode;

    private String action;

    public enum ActionEnum {

        HEART_BEAT("HEART_BEAT", "心跳检测");

        @Getter
        @Setter
        private String code;

        @Getter
        @Setter
        private String name;

        ActionEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public static ActionEnum get(String code) {
            ActionEnum action = null;
            for (ActionEnum value : ActionEnum.values()) {
                if (value.getCode().equals(code)) {
                    action = value;
                    break;
                }
            }
            return action;
        }

    }
}
