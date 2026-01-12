package com.tugalsan.java.core.gui.client.pop;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.java.core.gui.client.click.*;
import com.tugalsan.java.core.gui.client.focus.*;
import com.tugalsan.java.core.gui.client.key.*;
import com.tugalsan.java.core.gui.client.dim.*;
import com.tugalsan.java.core.gui.client.widget.*;
import com.tugalsan.java.core.gui.client.panel.*;
import com.tugalsan.java.core.icon.client.*;

import com.tugalsan.java.core.gui.client.browser.*;
import com.tugalsan.java.core.network.client.*;
import com.tugalsan.java.core.string.client.*;
import com.tugalsan.java.core.thread.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

public class TGC_PopLblYesNoTextArea implements TGC_PopInterface {

//    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoTextArea.class);

    public static int MAX_CHAR_SQL_STR() {
        return 254;
    }

    public static int MAX_CHAR_SQL_BLOB() {
        return 179363;
    }

    final private String btnOkText, btnCancelText;
    final public TGS_FuncMTU_In1<TGC_PopLblYesNoTextArea> onEsc, onExe;
    final public TGS_FuncMTU onVisible;

    public void setLabelHTML(String html) {
        lblHtml = TGS_NetworkHTMLUtils.HTML_SPACE() + html;
        label.setHTML(lblHtml);
    }
    private String lblHtml;

    public TGC_PopLblYesNoTextArea(TGC_Dimension dim,
            CharSequence lblHtml, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextArea> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextArea> onEsc,
            TGS_FuncMTU onVisible_optional) {
        this(dim,
                lblHtml, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional,
                null, null
        );
    }

    public TGC_PopLblYesNoTextArea(TGC_Dimension dim,
            CharSequence lblHtml, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextArea> onExe,
            TGS_FuncMTU_In1<TGC_PopLblYesNoTextArea> onEsc,
            TGS_FuncMTU onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        maxCharCount = MAX_CHAR_SQL_BLOB();
        this.dim = dim;
        this.lblHtml = TGS_NetworkHTMLUtils.HTML_SPACE() + lblHtml;
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.onEsc = onEsc;
        this.onExe = onExe;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = textArea;
    }
    private TGC_Dimension dim;
    private String iconClassExe, iconClassEsc;
    public int maxCharCount;

    @Override
    final public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHtml);
        textArea = new TextArea();
        btnExtend = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_ENLARGE(), "Ekranı Kapla");
        footer = new HTML("");
    }
    public PushButton btnEsc, btnExe, btnExtend;
    public TextArea textArea;
    public HTML label, footer;

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
    }

    @Override
    final public void configActions() {
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_ClickUtils.add(btnEsc, () -> onEsc.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(textArea, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_ClickUtils.add(btnExtend, () -> getPop().setVisibleFullScreen());
    }

    @Override
    final public void configFocus() {
        TGC_FocusUtils.addKeyUp(btnEsc, new TGS_FocusSides4(null, btnExe, null, textArea));
        TGC_FocusUtils.addKeyUp(btnExe, new TGS_FocusSides4(btnEsc, textArea, null, textArea));
        TGC_FocusUtils.addKeyUp(textArea, new TGS_FocusSides4(null, null, btnEsc, null));
    }

    @Override
    final public void configLayout() {
        label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        footer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        var vp = TGC_PanelLayoutUtils.createVertical(
                TGC_PanelLayoutUtils.createGrid(
                        dim == null ? null : dim.getWidth(),
                        new Integer[]{33, 34, 33},
                        new Widget[]{btnEsc, btnExe, btnExtend},
                        false
                ),
                label
        );
        vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorthSouth(2, vp,
                        2, footer,
                        textArea
                ),
                dim,
                () -> btnExtend.setVisible(!getPop().isFullScreen())
        );

    }
    private TGC_Pop panelPopup;

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }

    public void setEditable(boolean editable) {
        textArea.setReadOnly(!editable);
    }

    public void append(String text) {
        setTextArea(textArea.getText() + text);
    }

    public void setTextArea(String text) {
        textArea.setText(text);
    }

    public void startMaxCharCheck_untilUnVisible() {
        var delayInSec = 1;
        var hint = TGC_BrowserNavigatorUtils.mobile() ? "" : "<span style=\"float:left;\"><i><b>" + TGS_NetworkHTMLUtils.HTML_SPACE() + "İp Ucu-Uygula: </b>Control'e basılı tutarak Enter basın</i></span>";
        TGC_ThreadUtils.run_afterSeconds_afterGUIUpdate(t -> {
            var max = maxCharCount;
            var isBlob = maxCharCount > MAX_CHAR_SQL_STR();
            var cc = textArea.getText().length();
            if (!isBlob && cc > MAX_CHAR_SQL_STR()) {
                isBlob = true;
                max = MAX_CHAR_SQL_BLOB();
            }
            var suffix = isBlob ? SUF_BLOB() : SUF_STR();
            var per = Math.floor(100 * cc / max);
            var warningLabel = TGS_NetworkHTMLUtils.HTML_SPACE() + "Uyarı: ";
            var warningValue = TGS_StringUtils.cmn().concat(String.valueOf(cc), "/", String.valueOf(max), " karakter, %", String.valueOf(per), suffix);
            footer.setHTML(TGS_StringUtils.cmn().concat("<span style=\"float:left;\">", hint, "</span>", "<br/>", "<span style=\"float:left;\"><i><b>", warningLabel, "</b>", warningValue, "</i></span>"));
            if (getPop().isVisible()) {
                t.run_afterSeconds(delayInSec);
            } else {
                footer.setHTML(hint);
            }
        }, delayInSec);
    }

    private String SUF_STR() {
        return " (%100'den daha uzun yazılar aramalarda gözükmeyecek!)";
    }

    private String SUF_BLOB() {
        return " (%100'den daha uzun yazılarda kırpma işlemi uygulanacak!)";
    }
}
