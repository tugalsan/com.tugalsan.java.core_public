package com.tugalsan.java.core.gui.client.widget.menu;

import com.google.gwt.user.client.ui.*;
import java.util.*;
import com.tugalsan.java.core.log.client.*;
import com.tugalsan.java.core.gui.client.click.*;
import com.tugalsan.java.core.gui.client.dim.*;
import com.tugalsan.java.core.gui.client.pop.*;
import com.tugalsan.java.core.gui.client.widget.*;

import com.tugalsan.java.core.list.client.*;
import com.tugalsan.java.core.tuple.client.*;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU;

//WARNING: !!! CSS-STATIC-CLASS-NAME !!!
public class TGC_MenuMobile {

    final private static TGC_Log d = TGC_Log.of(TGC_MenuMobile.class);

    public TGC_MenuMobile(TGC_Dimension dim, CharSequence fullIconClassName, CharSequence label, String ok, String esc) {
        pop = new TGC_PopLblYesNoListBox(
                dim, null, label, ok, esc,
                p -> {
                    p.getPop().setVisible(false);
                    var si = p.listBox.getSelectedIndex();
                    if (si == -1) {
                        d.ce(label, "Hata: Seçim yapılmadı hatası!");
                        return;
                    }
                    if (si < subMenus.size()) {
                        subMenus.get(si).run();
                    } else {
                        si -= subMenus.size();
                        cmd.get(si).value1.run();
                    }
                },
                p -> p.getPop().setVisible(false),
                null, null
        );
        widget = TGC_ButtonUtils.createIcon(fullIconClassName, label);
        TGC_ClickUtils.add(widget, () -> {
            reinitialize();
            pop.getPop().setVisible(true);
        });
        widget.setStyleName(TGC_MenuMobile.class.getSimpleName());
    }
    final TGC_PopLblYesNoListBox pop;
    final public PushButton widget;

    private void reinitialize() {
        pop.listBox.clear();
        subMenus.stream().forEachOrdered(o -> {
            o.reinitialize();
            pop.listBox.addItem(o.label);
        });
        cmd.stream().forEachOrdered(o -> pop.listBox.addItem(o.value0));
        TGC_ListBoxUtils.selectNone(pop.listBox);
    }

    final public List<TGC_MenuMobileSub> subMenus = TGS_ListUtils.of();
    final public List<TGS_Tuple2<String, TGS_FuncMTU>> cmd = TGS_ListUtils.of();
}
