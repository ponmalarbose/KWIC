import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.beans.*;
import java.io.Serializable;

public interface URLListener extends EventListener {
void handleURL(URLEventObject urlevent);
}