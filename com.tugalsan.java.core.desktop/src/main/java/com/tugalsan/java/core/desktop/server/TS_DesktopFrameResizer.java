package com.tugalsan.java.core.desktop.server;

import module java.desktop;
import java.awt.event.MouseEvent;

public class TS_DesktopFrameResizer implements MouseInputListener {

    private TS_DesktopFrameResizer(JFrame frame) {
        this.frame = frame;
        this.contentPane = (JComponent) frame.getContentPane();
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
    }
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private JFrame frame;
    final private JComponent contentPane;
    private boolean pressed = false;
    private boolean started = false;
    private boolean onedge = false;
    private int edge;
    private int iwx, iwy;
    private int ix, iy;
    private int iw, ih;

    public Rectangle fixIt_getRectangleWithoutMenuBar() {
        started = true;
        return TS_DesktopWindowAndFrameUtils.getUnDecoratedRectangleWithoutMenubar(frame);
    }

    int tx, ty, tw, th;

    public void initCaching() {
        tx = frame.getX();
        ty = frame.getY();
        tw = frame.getWidth();
        th = frame.getHeight();
    }

    public static TS_DesktopFrameResizer of(JFrame frame) {
        return new TS_DesktopFrameResizer(frame);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        pressed = true;
        iwx = arg0.getX();
        iwy = arg0.getY();
        ix = arg0.getXOnScreen();
        iy = arg0.getYOnScreen();
        iw = frame.getWidth();
        ih = frame.getHeight();
        if (iwx < 5) {
            onedge = true;
            edge = 1;
        } else if (iwy < 5) {
            onedge = true;
            edge = 4;
        } else if (iwy > frame.getHeight() - 5) {
            onedge = true;
            edge = 2;
        } else if (iwx > frame.getWidth() - 5) {
            onedge = true;
            edge = 3;
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        pressed = false;
        onedge = false;
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        initCaching();
        if (started) {
            return;
        }
        if (pressed) {
            if (!onedge) {
                int x = arg0.getXOnScreen() - iwx, y = arg0.getYOnScreen() - iwy;
                setLocation(x, y);
            } else {
                switch (edge) {
                    case 3 ->
                        setSize(arg0.getXOnScreen() - (iw - iwx) - getX(), getHeight());
                    case 2 ->
                        setSize(getWidth(), arg0.getYOnScreen() - (ih - iwy) - getY());
                    case 1 -> {
                        setLocation(arg0.getXOnScreen(), getY());
                        setSize(iw + ix - arg0.getXOnScreen(), getHeight());
                    }
                    case 4 -> {
                        setLocation(getY(), arg0.getYOnScreen());
                        setSize(getWidth(), ih + iy - arg0.getYOnScreen());
                    }
                    default -> {
                    }
                }
            }
        }
        if (getX() < 0) {
            setLocation(0, getY());
        }
        if (getY() < 0) {
            setLocation(getX(), 0);
        }
        if (getX() + getWidth() > screenSize.width) {
            setLocation(screenSize.width - getWidth(), getY());
        }
        if (getY() + getHeight() > screenSize.height) {
            setLocation(getX(), screenSize.height - getHeight());
        }
        if (getWidth() > screenSize.width) {
            setSize(screenSize.width, getHeight());
        }
        if (getHeight() > screenSize.height) {
            setSize(getWidth(), screenSize.height);
        }
        sendSizeChange();
        sendLocationChange();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (started) {
            return;
        }
        if (e.getX() < 5) {
            contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        } else if (e.getY() < 5) {
            contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        } else if (e.getY() > frame.getHeight() - 5) {
            contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        } else if (e.getX() > frame.getWidth() - 5) {
            contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        } else {
            contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    public void setSize(int w, int h) {
        tw = w;
        th = h;
    }

    public void setLocation(int x, int y) {
        tx = x;
        ty = y;
    }

    public int getWidth() {
        return tw;
    }

    public int getHeight() {
        return th;
    }

    public int getX() {
        return tx;
    }

    public int getY() {
        return ty;
    }

    public void sendSizeChange() {
        frame.setSize(tw, th);
    }

    public void sendLocationChange() {
        frame.setLocation(tx, ty);
    }
}
