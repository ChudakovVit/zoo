package gui;

import javafx.scene.Node;

public class ViewController {

    private Node view;

    public Node getView() {
        return view;
    }

    public void setView (Node view) {
        this.view = view;
    }

    public void show() {
        GuiLauncher.getNavigation().show(this);
    }
}