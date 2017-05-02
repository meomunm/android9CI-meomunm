package game;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class InputManager {
    private boolean isUpPressd;
    private boolean isDownPressd;
    private boolean isLeftPressd;
    private boolean isRightPressd;
    private boolean isSpacePressd;

    public boolean isUpPressd() {
        return isUpPressd;
    }

    public void setUpPressd(boolean upPressd) {
        isUpPressd = upPressd;
    }

    public boolean isDownPressd() {
        return isDownPressd;
    }

    public void setDownPressd(boolean downPressd) {
        isDownPressd = downPressd;
    }

    public boolean isLeftPressd() {
        return isLeftPressd;
    }

    public void setLeftPressd(boolean leftPressd) {
        isLeftPressd = leftPressd;
    }

    public boolean isRightPressd() {
        return isRightPressd;
    }

    public void setRightPressd(boolean rightPressd) {
        isRightPressd = rightPressd;
    }

    public boolean isSpacePressd() {
        return isSpacePressd;
    }

    public void setSpacePressd(boolean spacePressd) {
        isSpacePressd = spacePressd;
    }
}
