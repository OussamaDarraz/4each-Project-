package service.gui.anemometre.ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.FocusEvent;

public  class SelectOptions<T> extends JComboBox<T> {
    private MaterialLine line = new MaterialLine(this);

    public static final int HINT_OPACITY_MASK = 0x99000000;
    public static final int LINE_OPACITY_MASK = 0x66000000;

    private Color accentColor = Color.WHITE;
    private String hint = "";

    public SelectOptions() {
        setModel(new DefaultComboBoxModel<>());
        setRenderer(new FieldRenderer<>(this));
        setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popupBox = new Popup(comboBox);
                popupBox.getAccessibleContext().setAccessibleParent(comboBox);
                return popupBox;
            }

            @Override
            protected JButton createArrowButton() {
                JButton button = new javax.swing.plaf.basic.BasicArrowButton(
                        javax.swing.plaf.basic.BasicArrowButton.SOUTH,
                        MaterialColor.TRANSPARENT,
                        MaterialColor.TRANSPARENT,
                        MaterialColor.TRANSPARENT,
                        MaterialColor.TRANSPARENT);
                button.setName("ComboBox.arrowButton");
                return button;
            }
        });
        setOpaque(false);
        setBackground(MaterialColor.DARK_WHITE);
        setBorder(BorderFactory.createLineBorder(MaterialColor.FAINT_BLACK));
    }

    /**
     * Gets the hint text. The hint text is displayed when the list inside this
     * combo box is empty or no element has been selected yet.
     *
     * @return hint text
     */
    public String getHint() {
        return hint;
    }

    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        line.update();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // paint text baground
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight() - 4);

        // paint the selected item
        g.setFont(Roboto.REGULAR.deriveFont(16f));
        g.setColor(getSelectedItem() == null ? new Color(0x9F8F8F): getForeground());
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        String text = getSelectedItem() != null ? getSelectedItem().toString() : (hint != null ? hint : "");
        g.drawString(text, 0, metrics.getAscent() + (getHeight() - metrics.getHeight()) / 2);

        // paint the Polygon <
        if (isFocusOwner()) {
            g2.setColor(accentColor);
        }
        g2.fillPolygon(new int[]{getWidth() - 5, getWidth() - 10, getWidth() - 15}, new int[]{getHeight() / 2 - 3, getHeight() / 2 + 3, getHeight() / 2 - 3}, 3);

        // paint line under text
        g2.setColor(new Color(LINE_OPACITY_MASK));
        g2.fillRect(0, getHeight() - 4, getWidth(), 1);

        // paint animated line under text
        g2.setColor(accentColor);
        g2.fillRect((int) ((getWidth() - line.getWidth()) / 2), getHeight() - 5, (int) line.getWidth(), 2);
    }

    public static class FieldRenderer<T> extends JComponent implements ListCellRenderer<T> {

        private final JComboBox<T> comboBox;
        private String text;
        private boolean mouseOver = false;
        private boolean selected = false;

        private Color accentColor = MaterialColor.DARK_BLACK;

        public FieldRenderer(JComboBox<T> comboBox) {
            this.comboBox = comboBox;
        }

        @Override
        public Component getListCellRendererComponent(JList jList, Object o, int index, boolean isSelected, boolean cellHasFocus) {
            text = o != null ? o.toString() : "Selectionner un";
            setSize(jList.getWidth(), 56);
            setPreferredSize(new Dimension(jList.getWidth(), 32));
            setOpaque(true);
            mouseOver = isSelected;
            selected = comboBox.getSelectedIndex() == index;
            setAlignmentX(Component.CENTER_ALIGNMENT);
            return this;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g.setColor(comboBox.getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setFont(Roboto.REGULAR.deriveFont(15f));
            if (selected) {
                g2.setColor(accentColor);
            } else {
                g2.setColor(comboBox.getForeground());
            }
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            g.drawString(text, 24, metrics.getAscent() + (getHeight() - metrics.getHeight()) / 2);
        }
    }

    public static class Popup extends BasicComboPopup {

        public Popup(JComboBox combo) {
            super(combo);
            setBackground(combo.getBackground());
            setOpaque(true);
            setBorderPainted(false);
        }

        @Override
        protected JScrollPane createScroller() {
            JScrollPane scrol = super.createScroller();
            scrol.setVerticalScrollBar(new ScrollBar(comboBox, Adjustable.VERTICAL));
            scrol.setBorder(new MatteBorder(16, 0, 16, 0, Color.BLACK));
            return scrol;
        }

        @Override
        protected Rectangle computePopupBounds(int px, int py, int pw, int ph) {
            return super.computePopupBounds(px, py - 2, (int) Math.max(comboBox.getPreferredSize().getWidth(), pw), ph);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
        }
    }

    public static class ScrollBar extends JScrollBar {

        public ScrollBar(final JComboBox comboBox, int orientation) {
            super(orientation);
            setPreferredSize(new Dimension(5, 100));

            setUI(new BasicScrollBarUI() {
                @Override
                protected ScrollListener createScrollListener() {
                    setUnitIncrement(56);
                    setBlockIncrement(560);
                    return super.createScrollListener();
                }

                @Override
                protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                    g.setColor(comboBox.getBackground());
                    g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton dummyButton = new JButton();
                    dummyButton.setPreferredSize(new Dimension(0, 0));
                    return dummyButton;
                }

                @Override
                protected JButton createIncreaseButton(int orientation) {
                    JButton dummyButton = new JButton();
                    dummyButton.setPreferredSize(new Dimension(0, 0));
                    return dummyButton;
                }

                @Override
                protected Dimension getMinimumThumbSize() {
                    return new Dimension(5, 50);
                }

                @Override
                protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                    if (!thumbBounds.isEmpty() && this.scrollbar.isEnabled()) {
                        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                        boolean isVertical = ScrollBar.this.getOrientation()
                                == Adjustable.VERTICAL;
                        g.setColor(MaterialColor.GREY_500);
                        g.fillRoundRect(thumbBounds.x, thumbBounds.y,
                                thumbBounds.width, thumbBounds.height,
                                isVertical ? thumbBounds.width : thumbBounds.height,
                                isVertical ? thumbBounds.width : thumbBounds.height);
                    }
                }

                @Override
                public void layoutContainer(Container scrollbarContainer) {
                    super.layoutContainer(scrollbarContainer);
                    incrButton.setBounds(0, 0, 0, 0);
                    decrButton.setBounds(0, 0, 0, 0);
                }
            });
        }

        @Override
        public Component add(Component comp) {
            if (comp != null) {
                return super.add(comp);
            }
            return null;
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paint(g);
        }
    }

    public class MaterialLine {

        private final JComponent target;
        private double width;
        private double targetWidth;
        private double widthCrement;

        public MaterialLine(JComponent target) {
            this.target = target;
        }

        public void update() {
            if (target.isFocusOwner()) {
                targetWidth = target.getWidth();
                widthCrement = +(double) target.getWidth() / 200;
            } else {
                widthCrement = -(double) target.getWidth() / 200;
                targetWidth = 0;
            }
        }

        public double getWidth() {
            return width;
        }
    }
    private static class Roboto {

        public static final Font BLACK = loadFont("Roboto-Black.ttf").deriveFont(Font.BOLD);
        public static final Font BLACK_ITALIC = loadFont("Roboto-BlackItalic.ttf").deriveFont(Font.BOLD | Font.ITALIC);
        public static final Font BOLD_ITALIC = loadFont("Roboto-BoldItalic.ttf").deriveFont(Font.BOLD | Font.ITALIC);
        public static final Font ITALIC = loadFont("Roboto-Italic.ttf").deriveFont(Font.ITALIC);
        public static final Font LIGHT_ITALIC = loadFont("Roboto-LightItalic.ttf").deriveFont(Font.ITALIC);
        public static final Font MEDIUM = loadFont("Roboto-Medium.ttf").deriveFont(Font.PLAIN);
        public static final Font MEDIUM_ITALIC = loadFont("Roboto-MediumItalic.ttf").deriveFont(Font.ITALIC);
        public static final Font REGULAR = loadFont("Roboto-Regular.ttf").deriveFont(Font.PLAIN);
        public static final Font BOLD = loadFont("Roboto-Bold.ttf").deriveFont(Font.BOLD);
        public static final Font THIN = loadFont("Roboto-Thin.ttf").deriveFont(Font.PLAIN);
        public static final Font LIGHT = loadFont("Roboto-Light.ttf").deriveFont(Font.PLAIN);
        public static final Font THIN_ITALIC = loadFont("Roboto-ThinItalic.ttf").deriveFont(Font.ITALIC);

        private static Font loadFont(String resourceName) {
            return new Font(Font.SANS_SERIF, Font.PLAIN,  10);
        }
    }
}
