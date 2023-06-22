package service.gui.tools;

import java.awt.*;

public abstract class Size {
    private static int width;
    private static int height;

    static {
        // Set dimension of the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width * 9/10;
        height = screenSize.height * 9/10;
    }

    public static int winWidth() {
        return width;
    }

    public static int winHeight() {
        return height;
    }

    public static int widthPercent(int percent) {
        int result = 100;
        if (0 <= percent && percent <= 100) {
            float per = (float) percent/100;
            result = (int) (width * per);
        }
        return result;
    }

    public static int heightPercent(int percent) {
        int result = 100;
        if (0 <= percent && percent <= 100) {
            float per = (float) percent/100;
            result = (int) (height * per);
        }
        return result;
    }
}
