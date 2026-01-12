package com.tugalsan.java.core.console.jdk.server;

import module com.tugalsan.java.core.charset;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.math;
import java.util.stream.*;

public class TS_ConsoleProgressBar {

    public static enum Style {
        PERCENTAGE, STEP
    }

    private int calculatePercentageFor(int stepPossible) {
        return TGS_MathUtils.percentageValueInt(stepPossible, stepSize);
    }

    private String calculateStyleFor(int stepPossible) {
        return style == Style.STEP
                ? String.valueOf(stepPossible) + "/" + String.valueOf(stepSize)
                : String.valueOf(calculatePercentageFor(stepPossible)) + "%";
    }

    private String formatStyle() {
        formmattedStyleLabel.setLength(0);
        formmattedStyleLabel.append(calculateStyleFor(stepCurrent));
        while (STYLE_LABEL_PREFIX.length() + STYLE_LABEL_SUFFIX.length() + formmattedStyleLabel.length() < styleSize) {
            formmattedStyleLabel.insert(0, ' ');
        }
        formmattedStyleLabel.insert(0, STYLE_LABEL_PREFIX);
        formmattedStyleLabel.append(STYLE_LABEL_SUFFIX);
        return formmattedStyleLabel.toString();
    }
    final StringBuilder formmattedStyleLabel = new StringBuilder();

    private TS_ConsoleProgressBar(int stepSize, Style style, int labelSize) {
        this.stepCurrent = 0;
        this.stepSize = stepSize;
        this.labelSize = labelSize;
        this.style = style;
        this.styleSize = STYLE_LABEL_PREFIX.length() + calculateStyleFor(stepSize).length() + STYLE_LABEL_SUFFIX.length();
        this.labelBuffer = new StringBuilder();
        Stream.generate(() -> " ")
                .limit(labelSize)
                .forEach(labelBuffer::append);
        this.lineBuffer = new StringBuilder();
        Stream.generate(() -> TGS_CharSet.cmn().UTF8_INCOMPLETE())
                .limit(stepSize)
                .forEach(lineBuffer::append);
        Stream.generate(() -> " ")
                .limit(stepSize)
                .forEach(lineBuffer::append);
        lineBuffer.insert(0, "\r");
    }
    final private int stepSize, styleSize, labelSize;
    final public Style style;
    private int stepCurrent;
    final private StringBuilder labelBuffer;
    final private StringBuilder lineBuffer;
    final static private String STYLE_LABEL_PREFIX = " [";
    final static private String STYLE_LABEL_SUFFIX = "] ";

    public int size() {
        return stepSize;
    }

    public static TS_ConsoleProgressBar of(int stepSize, Style style, int labelSize) {
        return new TS_ConsoleProgressBar(stepSize, style, labelSize);
    }

    public int getCurrent() {
        return stepCurrent;
    }

    public int getPercentageValue() {
        return calculatePercentageFor(stepCurrent);
    }

    @Deprecated //USE getCurrent
    public int getLabel() {
        return stepCurrent;
    }

    public TS_ConsoleProgressBar setCurrent(int stepNew, String labelNew) {
        stepCurrent = TGS_FuncMTUEffectivelyFinal.ofInt()
                .anoint(val -> stepNew)
                .anointIf(val -> stepNew < 0, val -> 0)
                .anointIf(val -> stepNew > stepSize, val -> stepSize)
                .coronate();
        labelBuffer.setLength(0);
        if (labelNew != null) {
            labelBuffer.append(labelNew);
            labelBuffer.setLength(labelSize);
            if (labelNew.length() > labelBuffer.length() && labelBuffer.length() > 3) {
                labelBuffer.setCharAt(labelSize - 1, '.');
                labelBuffer.setCharAt(labelSize - 2, '.');
                labelBuffer.setCharAt(labelSize - 3, '.');
            }
        }
        IntStream.rangeClosed(1, stepSize).forEach(stepIndex -> {
//            d.ci("setCurrent", stepIndex, stepCurrent, stepIndex > stepCurrent ? TGS_CharSetUTF8.UTF8_INCOMPLETE() : TGS_CharSetUTF8.UTF8_COMPLETE());
            lineBuffer.replace(stepIndex, stepIndex + 1, stepIndex > stepCurrent ? TGS_CharSet.cmn().UTF8_INCOMPLETE() : TGS_CharSet.cmn().UTF8_COMPLETE());
        });
        lineBuffer.replace(1 + stepSize, 1 + stepSize + styleSize, formatStyle());
        lineBuffer.setLength(1 + stepSize + styleSize);
        lineBuffer.append(labelBuffer);
        return this;
    }

    public TS_ConsoleProgressBar showCurrent() {
        System.out.print(lineBuffer);
        return this;
    }

    public void forEach(TGS_FuncMTU_In1<TS_ConsoleProgressBar> progress) {
        IntStream.rangeClosed(0, stepSize).forEach(stepNumber -> {
            progress.run(setCurrent(stepNumber, null));
        });
    }
}
