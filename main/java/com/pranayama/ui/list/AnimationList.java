package com.pranayama.ui.list;

import com.pranayama.util.ImgUtils;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AnimationList {

    private String ImagePath;
    private String Title;
    private ImageIcon image;

    public AnimationList(String Title) {
        this.Title = Title;
    }

    public AnimationList(String Title, String ImagePath) {
        this.Title = Title;
        this.ImagePath = ImagePath;
    }

    public String getTitle() {
        return Title;
    }

    public ImageIcon getImage() {
        if (image == null) { 
            image = new ImageIcon(ImagePath);
            Image img = image.getImage();
            Image newImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon newImc = new ImageIcon(newImg);
            image = newImc;
        }
        return image;
    }

    @Override
    public String toString() {
        return Title;
    }
}
