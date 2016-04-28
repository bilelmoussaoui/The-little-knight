package View;

import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class MainWindow {
	public GraphicsDevice device;
	public static boolean gamePaused = false;
	public static boolean newGame = false;
	private JFrame mainWindow;
    private Map levelMap = new Map();

	public MainWindow(){
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		initialize();
	}

	public static void main(String args[]){
        new MainWindow();
    }

	public void initialize(){
		mainWindow = new JFrame();
        mainWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}
            @Override
            public void keyReleased(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(!MainWindow.gamePaused) {
						MainWindow.gamePaused = true;
						showMenuWindow();
					} else {
						MainWindow.gamePaused = false;
						showLevelWindow();
					}
                }
            }

        });
		mainWindow.setTitle("The Little Knight");
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWindow.setUndecorated(true);
		mainWindow.setResizable(false);
        showMenuWindow();
        mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setLocationByPlatform(true);
		device.setFullScreenWindow(mainWindow);
		mainWindow.setVisible(true);
	}

    public void showLevelWindow(){
        LevelWindow Window = new LevelWindow(levelMap);
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(Window.getJPanel());
        mainWindow.requestFocusInWindow();
        mainWindow.revalidate();
    }

	public  void closeWindow(){
        mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
	}

	public void showMenuWindow() {
		MenuWindow Window = new MenuWindow(this);
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.revalidate();
	}

	public void showLoadWindow(){
		LoadWindow Window = new LoadWindow();
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(Window.getJPanel());
		mainWindow.revalidate();
	}

    public void draw(int[][] map, Game game){
        levelMap.setGame(game);
        levelMap.setMapMatrix(map);
	}

    public void setKeyListener(KeyListener keyboard){
        mainWindow.addKeyListener(keyboard);
    }

}