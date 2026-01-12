package com.tugalsan.java.core.desktop.server;

import module com.tugalsan.java.core.shape;
import module com.tugalsan.java.core.function;
import module java.desktop;
import java.util.*;
import java.util.stream.*;

public class TS_DesktopDesktopPaneUtils {

    private TS_DesktopDesktopPaneUtils() {

    }

    public static void remove(JDesktopPane desktopPane, Component comp) {
        desktopPane.remove(comp);
    }

    public static void tiltWindows(JDesktopPane desktopPane) {
        var visibleFrames = Arrays.stream(desktopPane.getAllFrames())
                .filter(JInternalFrame::isVisible)
                .collect(Collectors.toCollection(ArrayList::new));
        if (visibleFrames.isEmpty()) {
            return;
        }
        // Determine the necessary grid size
        var count = visibleFrames.size();
        var sqrt = (int) Math.sqrt(count);
        var wrap = new Object() {
            int rows = sqrt;
            int cols = sqrt;
        };
        if (wrap.rows * wrap.cols < count) {
            wrap.cols++;
            if (wrap.rows * wrap.cols < count) {
                wrap.rows++;
            }
        }
        // Define some initial values for size & location.
        var size = desktopPane.getSize();
        TGS_ShapeRectangle<Integer> s = TGS_ShapeRectangle.of(0, 0, size.width / wrap.cols, size.height / wrap.rows);
        // Iterate over the frames, deiconifying any iconified frames and then
        // relocating & resizing each.
        IntStream.range(0, wrap.rows).forEachOrdered(i -> {
            for (var j = 0; j < wrap.cols && ((i * wrap.cols) + j < count); j++) {
                var f = visibleFrames.get((i * wrap.cols) + j);
                if (!f.isClosed() && f.isIcon()) {
                    TGS_FuncMTCUtils.run(() -> f.setIcon(false), e -> TGS_FuncMTU.empty.run());
                }
                desktopPane.getDesktopManager().resizeFrame(f, s.x, s.y, s.width, s.height);
                s.x += s.width;
            }
            s.y += s.height; // start the next row
            s.x = 0;
        });
    }

    public static void paintComponent(JDesktopPane pane, Graphics g, Image imgBack) {
        if (imgBack != null) {
            var x = (pane.getWidth() - imgBack.getWidth(null)) / 2;
            var y = (pane.getHeight() - imgBack.getHeight(null)) / 2;
            g.drawImage(imgBack, x, y, pane);
        }
    }

    //Everyttime internal_frame moves, keep it on the desktop_frame
    public static void keepInternalFramesInsideThePane(JDesktopPane pane) {
        pane.setDesktopManager(new DefaultDesktopManager() {
            @Override
            public void dragFrame(JComponent frame, int x, int y) {
                if (frame instanceof JInternalFrame internalFrame) {
                    var deskSize = internalFrame.getDesktopPane().getSize();
                    if (x < 0) {
                        x = 0;
                    } else {
                        if (x + internalFrame.getWidth() > deskSize.width) {
                            x = deskSize.width - internalFrame.getWidth();
                        }
                    }
                    if (y < 0) {
                        y = 0;
                    } else {
                        if (y + internalFrame.getHeight() > deskSize.height) {
                            y = deskSize.height - internalFrame.getHeight();
                        }
                    }
                }
                super.dragFrame(frame, x, y);
            }
        });
    }
}
