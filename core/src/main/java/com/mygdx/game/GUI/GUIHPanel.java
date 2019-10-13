package com.mygdx.game.GUI;

import com.mygdx.game.util.Vector2f;

public class GUIHPanel extends GUIComponent {

    public GUIHPanel(GUI gui, String name, GUIComponent parent, Vector2f size) {
        super(gui, name, parent, size);
    }

    @Override
    public void setChildPos() {
        float offset = 0;

        switch(horizontalAnchor) {
            case LEFT:
                offset = pos.x;
                break;
            case RIGHT:
                offset = pos.x + size.x;
                break;
            case CENTER:
                float totalWidth = 0;
                for(GUIComponent child : children) {
                    totalWidth += child.leftMargin() + child.getSize().x + child.rightMargin();
                }
                offset = (pos.x + size.x/2) - totalWidth/2;
                break;
        }

        for (GUIComponent child : children) {

            Vector2f cPos = new Vector2f(0, 0);

            switch (horizontalAnchor) {
                case LEFT: {
                    offset += child.leftMargin();
                    cPos.x = offset;
                    offset += child.getSize().x + child.rightMargin();
                }
                break;
                case RIGHT: {
                    offset -= child.rightMargin() + child.getSize().x;
                    cPos.x = offset;
                    offset -= child.leftMargin();
                }
                break;
                case CENTER: {
                    offset += child.leftMargin();
                    cPos.x = offset;
                    offset += child.getSize().x + child.rightMargin();
                }
                break;
            }

            switch (verticalAnchor) {
                case TOP: {
                    cPos.y = pos.y + size.y - child.getSize().y - child.topMargin();
                }
                break;
                case BOTTOM: {
                    cPos.y = pos.y + child.bottomMargin();
                }
                break;
                case CENTER: {
                    cPos.y = (pos.y + size.y/2) - child.getSize().y/2;
                }
                break;
            }

            child.setPos(cPos);
            child.setChildPos();
        }
    }
}
