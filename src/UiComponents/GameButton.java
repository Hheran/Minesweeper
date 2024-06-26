package UiComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameButton extends JButton implements MouseListener {
    private final static Color BACKGROUND_COLOR;
    static {
        BACKGROUND_COLOR = new Color(0x400E03);
    }
    private final int borderRadius;
    private  String buttonText;
    private int difficulty;
    private ImageIcon buttonImage, normalImage, hoveredImage;
    private Point imageLocation;
    private boolean isImageButton = false;

    public GameButton(ImageIcon buttonImage, ImageIcon hoveredImage, Point imageLocation, Dimension size, int borderRadius) {
        this.buttonImage = buttonImage;
        this.normalImage = buttonImage;
        this.hoveredImage = hoveredImage;
        this.imageLocation = imageLocation;
        this.setPreferredSize(size);
        this.borderRadius = borderRadius;
        this.addMouseListener(this);
        isImageButton = true;
    }
    public GameButton(String buttonText, Font buttonTextFont, Point buttonTextLocation, Dimension size, int borderRadius, int difficulty) {
        this.buttonText = buttonText;
        this.borderRadius = borderRadius;
        this.difficulty = difficulty;
        this.setFont(buttonTextFont);
        this.setPreferredSize(size);

        this.setBackground(BACKGROUND_COLOR);
        this.setBorder(new EmptyBorder(buttonTextLocation.y, buttonTextLocation.x, 0, 0));

        this.addMouseListener(this);
        this.setOpaque(false);
    }
    public GameButton(String buttonText, Font buttonTextFont, Point buttonTextLocation, Dimension size, int borderRadius, Color backgroundColor) {
         this(buttonText, buttonTextFont, buttonTextLocation, size, borderRadius, -1);
         this.setBackground(backgroundColor);
     }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setPaint(this.getBackground());
        graphics2D.fillRoundRect(0,0, this.getWidth(), this.getHeight(), borderRadius, borderRadius);

        graphics2D.setPaint(Color.WHITE);

        if (isImageButton)
            graphics2D.drawImage(buttonImage.getImage(), imageLocation.x, imageLocation.y, null);
        else
            graphics2D.drawString(buttonText, getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top);

    }
    @Override
    protected void paintBorder(Graphics g) {
        // Do nothing to prevent painting the default border
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!isImageButton)
            this.setBackground(this.getBackground().darker());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!isImageButton)
            this.setBackground(this.getBackground().brighter());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isImageButton) {
            buttonImage = hoveredImage;
            this.repaint();
        }
        else
            this.setBackground(this.getBackground().brighter());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (isImageButton) {
            buttonImage = normalImage;
            this.repaint();
        }
        else
            this.setBackground(this.getBackground().darker());
    }
}
