package com.mygdx.game.GUI;

import com.mygdx.game.util.Vector2f;

public class GUIVPanel extends GUIComponent {

    public GUIVPanel(GUI gui, String name, GUIComponent parent, Vector2f size) {
        super(gui, name, parent, size);
    }

    @Override
    public void setChildPos() {
        float offset = 0;

        switch(verticalAnchor){
            case TOP:
                offset = pos.y + size.y;
                break;
            case BOTTOM:
                offset = pos.y;
                break;
            case CENTER:
                float totalHeight = 0;
                for(GUIComponent child : children) {
                    totalHeight += child.bottomMargin() + child.getSize().y + child.topMargin();
                }
                offset = (pos.y + size.y/2) - totalHeight/2;
                break;
        }

        for(GUIComponent child : children) {
            Vector2f cPos = new Vector2f(0, 0);

            switch (verticalAnchor) {
                case TOP: {
                    offset -= child.topMargin() + child.getSize().y;
                    cPos.y = offset;
                    offset -= child.bottomMargin();
                }
                break;
                case BOTTOM: {
                    offset += child.bottomMargin();
                    cPos.y = offset;
                    offset += child.getSize().y + child.topMargin();
                }
                break;
                case CENTER: {
                    offset -= child.topMargin() + child.getSize().y;
                    cPos.y = offset;
                    offset -= child.bottomMargin();
                }
                break;
            }

            switch(horizontalAnchor) {
                case LEFT: {
                    cPos.x = pos.x + child.leftMargin();
                }
                break;
                case RIGHT: {
                    cPos.x = pos.x + size.x - child.getSize().x - child.rightMargin();
                }
                break;
                case CENTER: {
                    cPos.x = (pos.x + size.x/2) - child.getSize().y/2;
                }
                break;
            }

            child.setPos(cPos);
            child.setChildPos();
        }
    }

    public void setAnchor(VerticalAnchor anchor) {
        verticalAnchor = anchor;
    }
}
