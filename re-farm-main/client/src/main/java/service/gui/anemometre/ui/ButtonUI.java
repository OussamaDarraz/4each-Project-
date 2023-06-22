package service.gui.anemometre.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ButtonUI extends JButton {
    public ButtonUI(String text) {
        super(text);
        setOpaque(false);
        setBackground(null);
        setFocusPainted(true);
        setForeground(MaterialColor.BLACK);
        RoundedBorder blueLineBorder = new RoundedBorder(MaterialColor.DARK_BLACK, 1);
        setSize(140, 50);
        setPreferredSize(getSize());
        Border emptyBorder = BorderFactory.createEmptyBorder(getBorder().getBorderInsets(this).top, this.getBorder().getBorderInsets(this).left, this.getBorder().getBorderInsets(this).bottom, this.getBorder().getBorderInsets(this).right);
        setBorder(BorderFactory.createCompoundBorder(blueLineBorder, emptyBorder));
        setContentAreaFilled(false);
    }

    private static class RoundedBorder implements Border {

        private int radius = 10;
        private Color color;

        private RoundedBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
