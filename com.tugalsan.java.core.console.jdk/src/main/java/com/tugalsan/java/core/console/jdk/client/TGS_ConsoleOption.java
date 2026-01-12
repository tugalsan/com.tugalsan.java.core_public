package com.tugalsan.java.core.console.jdk.client;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In2;
import com.tugalsan.java.core.charset.client.TGS_CharSetCast;
import com.tugalsan.java.core.charset.client.TGS_CharSetLocaleTypes;
import com.tugalsan.java.core.list.client.TGS_ListUtils;
import java.util.List;

public class TGS_ConsoleOption {

    private TGS_ConsoleOption(TGS_CharSetLocaleTypes language, TGS_FuncMTU_In2<CharSequence, List<CharSequence>> run, List<String> alias) {
        this.language = language;
        this.run = run;
        this.alias = alias;
    }
    final public TGS_CharSetLocaleTypes language;
    final public TGS_FuncMTU_In2<CharSequence, List<CharSequence>> run;
    final public List<String> alias;

    public static TGS_ConsoleOption of(TGS_CharSetLocaleTypes language, TGS_FuncMTU_In2<CharSequence, List<CharSequence>> run, List<String> alias) {
        return new TGS_ConsoleOption(language, run, alias);
    }

    public static TGS_ConsoleOption of(TGS_CharSetLocaleTypes language, TGS_FuncMTU_In2<CharSequence, List<CharSequence>> run, String... alias) {
        return of(language, run, TGS_ListUtils.of(alias));
    }

    public boolean is(CharSequence cmdName) {
        return alias.stream().anyMatch(a -> TGS_CharSetCast.typed(language).equalsIgnoreCase(a, cmdName));
    }

    @Override
    public String toString() {
        return TGS_ConsoleOption.class.getSimpleName() + "{" + "alias=" + alias + '}';
    }
}
