package com.tugalsan.java.core.captcha.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.thread;
import module com.tugalsan.java.core.servlet.url;
import module com.tugalsan.java.core.captcha;

public class TS_CaptchaSUEMemRefresh extends TS_SURLExecutor {

    @Override
    public String name() {
        return TGS_CaptchaUtils.SERVLET_REFRESH();
    }

    @Override
    public void run(TS_ThreadSyncTrigger servletKillThrigger, TS_SURLHandler suh) {
        suh.img("png", img -> {
            var captcha = new TS_Captcha.Builder().buildPreffered(
                    img.getParameterInteger("bg", false),
                    img.getParameterInteger("gimp", false),
                    img.getParameterInteger("border", false),
                    img.getParameterInteger("txt", false),
                    img.getParameterInteger("word", false),
                    img.getParameterInteger("noise", false),
                    onlyNumbers == null ? false : onlyNumbers.validate(img)
            );
            TS_CaptchaMemUtils.setServer(suh.rq, captcha.getAnswer());
            return captcha.getImage();
        });
    }

    public static TGS_FuncMTU_OutBool_In1<TS_SURLHandler02ForFileImg> onlyNumbers;
}
