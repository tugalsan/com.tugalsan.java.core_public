package com.tugalsan.java.core.gui.client.widget.menu;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import java.util.*;
import com.tugalsan.java.core.icon.client.*;
import com.tugalsan.java.core.file.html.client.*;
import com.tugalsan.java.core.list.client.*;

public class TGC_MenuPC {

//    final private static TGC_Log d = TGC_Log.of(TGC_Menu.class);

    private static String CSS_NAME_MenuMain() {
        return "TGC_MenuBar_MenuMain";
    }

    private static String CSS_NAME_MenuSub() {
        return "TGC_MenuBar_MenuSub";
    }

    private static String CSS_NAME_MenuItem() {
        return "TGC_MenuBar_MenuItem";
    }

    public TGC_MenuPC() {
        widget = new MenuBar();
        widget.addStyleName(CSS_NAME_MenuMain());
        widget.setAutoOpen(false);
        widget.setAnimationEnabled(true);
    }

    final public List<TGC_Menu> mainMenus = TGS_ListUtils.of();
    public MenuBar widget;

    public TGC_Menu addMainMenu(CharSequence iconClassName, TGS_FileHtmlText fontedText) {
        var mainMenu = new MenuBar(true);
        mainMenu.addStyleName(CSS_NAME_MenuSub());
        if (iconClassName == null) {
            widget.addItem(fontedText.toString(), true, mainMenu);
        } else {
            var text = fontedText == null ? null : fontedText.toString();
            widget.addItem(TGS_IconUtils.createSpan(iconClassName, text), true, mainMenu);
        }
        var menu = new TGC_Menu(fontedText == null ? "" : fontedText.getText(), mainMenu);
        mainMenus.add(menu);
        return menu;
    }

    //WARNING: !!! CSS-STATIC-CLASS-NAME !!!
    public class TGC_Menu {

        public TGC_Menu(CharSequence label, MenuBar widget) {
            this.label = label.toString();
            this.widget = widget;
        }
        final public String label;
        final public MenuBar widget;
        private final List<TGC_Menu> subMenus = TGS_ListUtils.of();
        private final List<TGC_MenuCommand> items = TGS_ListUtils.of();

        public TGC_Menu addSubMenu(CharSequence iconClassName, TGS_FileHtmlText fontedText) {
            var subMenu = new MenuBar(true);
            subMenu.addStyleName(CSS_NAME_MenuItem());
            var fontedHtml = fontedText == null ? "" : fontedText.toString();
            if (iconClassName == null) {
                widget.addItem(fontedHtml, true, subMenu);
            } else {
                var text = fontedHtml + " " + TGS_FileHtmlText.charRightArrow() + TGS_FileHtmlText.charRightArrow();
                widget.addItem(TGS_IconUtils.createSpan(iconClassName, text), true, subMenu);
            }
            var menu = new TGC_Menu(fontedHtml, subMenu);
            subMenus.add(menu);
            return menu;
        }

        public TGC_MenuCommand addCommand(CharSequence iconClassName, TGS_FileHtmlText fontedText, Command command) {
            var fontedHtml = fontedText == null ? "" : fontedText.toString();
            widget.addItem(TGS_IconUtils.createSpan(iconClassName, fontedHtml), true, command);
            var cmd = new TGC_MenuCommand(fontedHtml, command);
            items.add(cmd);
            return cmd;
        }

        public class TGC_MenuCommand {

            public TGC_MenuCommand(CharSequence label, Command cmd) {
                this.label = label.toString();
                this.cmd = cmd;
            }
            final public String label;
            final public Command cmd;
        }
    }
}
