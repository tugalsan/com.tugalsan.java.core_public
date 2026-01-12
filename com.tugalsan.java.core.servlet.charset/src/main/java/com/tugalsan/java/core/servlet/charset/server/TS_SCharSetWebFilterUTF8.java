package com.tugalsan.java.core.servlet.charset.server;

import module com.tugalsan.java.core.charset;
import module com.tugalsan.java.core.file;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module jakarta.servlet;

@WebFilter(
        urlPatterns = {"/*"},
        initParams = {
            @WebInitParam(name = "requestEncoding", value = TGS_CharSet.CommonGwt.UTF8)
        }
)
public class TS_SCharSetWebFilterUTF8 implements Filter {

    final private static TS_Log d = TS_Log.of(TS_SCharSetWebFilterUTF8.class);

    @Override
    public void init(FilterConfig config) {
        TGS_FuncMTCUtils.run(() -> {
            encoding = config.getInitParameter("requestEncoding");
            if (encoding == null) {
                encoding = TGS_CharSet.cmn().UTF8;
            }
        });
    }
    private String encoding;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) {
        TGS_FuncMTCUtils.run(() -> {

            //REQUEST.COMMON
            if (null == request.getCharacterEncoding()) {
                request.setCharacterEncoding(encoding);
            }

            //REQUEST.TEXT
            var contentType = TGS_FileTypes.findByContenTypePrefix(String.valueOf(response.getContentType()));
            if (contentType != null && contentType.utf8) {
                response.setContentType(contentType.content);
            }

            //RESPONSE
            response.setCharacterEncoding(TGS_CharSet.cmn().UTF8);

            //ESCALATE
            next.doFilter(request, response);
        }, e -> {
            if (e.getClass().getName().equals("org.apache.catalina.connector.ClientAbortException")) {
                if (request instanceof HttpServletRequest hsr) {
                    d.cr("doFilter", "CLIENT GAVE UP", e.getMessage(), hsr.getRequestURL().toString() + "?" + hsr.getQueryString());
                } else {
                    d.cr("doFilter", "CLIENT GAVE UP", e.getMessage());
                }
                return;
            }
            TGS_FuncMTCUtils.run(() -> next.doFilter(request, response));//ESCALATE WITHOUT DEF_CHARSET
        });
    }

    @Override
    public void destroy() {
    }
}
