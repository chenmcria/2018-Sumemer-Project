package ui;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedCornerBorder implements Border {
    private int cornerSize;

    public RoundedCornerBorder(int cornerSize) {

        this.cornerSize = cornerSize;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x,y,width - 1,height - 1, cornerSize, cornerSize);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.cornerSize + 1, this.cornerSize + 1, this.cornerSize + 2, this.cornerSize);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}