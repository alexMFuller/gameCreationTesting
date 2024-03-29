package jade;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWErrorCallback;


public class mouseListener {
    private static mouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;

    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private mouseListener(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY= 0.0;
    }

    public static mouseListener get(){
        if (instance == null){
            instance = new mouseListener();

        }
        return instance;
    }

    public static void mousePosCallback(long window, double xpos, double ypos){
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;



    }

    public static void mouseButtonCallback(long window, int button, int action, int mods){

        if (action== GLFW_PRESS){
            if (button < get().mouseButtonPressed.length)
            get().mouseButtonPressed[button] = true;
        }


        else if (action == GLFW_RELEASE){
            if (button < get().mouseButtonPressed.length)
            get().mouseButtonPressed[button] = false;
            get().isDragging = false;
        }



    }
    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame(){
        get().scrollY = 0;
        get().scrollX  = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }
    //Various Getters
    public static float getX(){
        return (float) get().xPos;
    }
    public static float getY(){
        return (float) get().yPos;
    }
    public static float getDx(){
        return (float) (get().lastX - get().xPos);
    }
    public static float getDy(){
        return (float) (get().lastY - get().yPos);
    }
    public static float getScrollX(){
        return (float) get().scrollX;
    }
    public static float getScrollY(){
        return (float) get().scrollY;
    }
    public static boolean isDragging(){
        return get().isDragging;
    }



}
