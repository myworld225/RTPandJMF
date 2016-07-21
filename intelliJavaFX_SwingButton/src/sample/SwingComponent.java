package sample;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javafx.application.Platform;

/**
 * Created by user on 2016-07-21.
 *
 * Main 에서 사용 할 SwingNode 에 들어갈 스윙 노드 생성 클래스
 * SwingComponent는 ActionListener를 구현한 하나의 JButton으로 이루어져있다.
 */
public class SwingComponent extends JPanel implements ActionListener {

    public static JButton b1;


    public SwingComponent() {
        b1 = new JButton("<html><center><b><u>E</u>nable</b><br>"
                + "<font color=#ffffdd>FX button</font>");
        b1.addActionListener(this);
        b1.setActionCommand("disable");
        b1.setToolTipText("Click this button to disable the middle button.");
        b1.setMnemonic(KeyEvent.VK_D);


        //Add Components to this container, using the default FlowLayout.
        add(b1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Platform.runLater(() -> {
            Main.fxButton.setDisable(false);
        });
        b1.setEnabled(false);
    }

}
