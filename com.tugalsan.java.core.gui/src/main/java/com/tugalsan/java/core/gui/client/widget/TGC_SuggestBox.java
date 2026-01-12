package com.tugalsan.java.core.gui.client.widget;

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import java.util.Collection;

public class TGC_SuggestBox extends SuggestBox {

    public TGC_SuggestBox(/*List<String> items*/) {
        super(
                //        new SuggestOracle() {
                //            @Override
                //            public void requestSuggestions(Request request, Callback callback) {
                //                var response = new Response();
                //                response.setSuggestions(TGS_StreamUtils.toLst(items.stream().map(item -> new TGC_SuggestBoxSuggestion(item))));
                //                callback.onSuggestionsReady(request, response);
                //            }
                //        }
                new MultiWordSuggestOracle(),
                new TextBox(), new DefaultSuggestionDisplay() {
            @Override
            protected void showSuggestions(SuggestBox suggestBox, Collection<? extends Suggestion> suggestions, boolean isDisplayStringHTML, boolean isAutoSelectEnabled, SuggestionCallback callback) {
                if (suggestBox.getText().length() > 1) {
                    super.showSuggestions(suggestBox, suggestions, isDisplayStringHTML, isAutoSelectEnabled, callback);
                }
            }
        });
    }

    public void clear() {
        ((MultiWordSuggestOracle) getSuggestOracle()).clear();
    }

    public void addAll(Collection<String> items) {
        ((MultiWordSuggestOracle) getSuggestOracle()).addAll(items);
    }

    public void add(String item) {
        ((MultiWordSuggestOracle) getSuggestOracle()).add(item);
    }
    
    public ValueBoxBase<String> getFocusWidget(){
        return getValueBox();
    }

//    private static class TGC_SuggestBoxSuggestion implements Suggestion {
//
//        private final String text;
//
//        public TGC_SuggestBoxSuggestion(String text) {
//            this.text = text;
//        }
//
//        @Override
//        public String getDisplayString() {
//            return text;
//        }
//
//        @Override
//        public String getReplacementString() {
//            return text;
//        }
//    }
}
